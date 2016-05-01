package SocketLayer.Sockets;

import DAL.FileRepository;
import Services.FileContextService;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/Editeur/{file}")
public class EditeurWebSocket {
    private Session session;
    private FileContextService fcs;
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());


    @OnOpen
    public void onOpen(Session session , @PathParam("file") final String file){
        System.out.println("nouvelle connexion WS editeur");
        this.session = session;
        sessions.add(session);
        fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(file).id));
        sendMessage(fcs.getFileContent());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String[] vars = message.split("-");
        System.out.println(message+" "+vars.length);
        int pos = Integer.parseInt(vars[0]);
        char c = vars[1].charAt(0);
        if (vars[2]=="action") keyboardAction(pos,c);
        else keyboardEvent(pos,c);
        synchronized (sessions){
            for (Session ses : sessions){
                if (ses.isOpen())sendSessionMessage(fcs.getFileContent(),ses);
            }
        }

    }

    public void sendSessionMessage(String message,Session s){
        try {
            s.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try {
            if (message==null){
                System.out.println("LE MESSAGE EST NUL ABRUTI");
            }else{
                System.out.println(message);
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void keyboardEvent(int pos, char carac) {
        switch (carac){
            case 9:
                fcs.insertCharacter(pos,'\t');
                break;
            case 13 :
                fcs.insertCharacter(pos,'\n');
                break;
            case 32:
                fcs.insertCharacter(pos,' ');
                break;
            default:
                if (Character.isLetterOrDigit(carac))
                    fcs.insertCharacter(pos, carac);
                break;
        }
    }

    private void keyboardAction(int pos, char carac) {
        switch (carac) {
            case 9:
                fcs.insertCharacter(pos, '\t');
                break;
            case 13:
                fcs.insertCharacter(pos, '\n');
                break;
            case 127:
                fcs.deleteCharacter(pos, false);
                break;
            case 8:
                fcs.deleteCharacter(pos, true);
                break;
            case 32:
                fcs.insertCharacter(pos, ' ');
                break;
        }
    }

    @OnClose
    public void onClose(final Session session){
        sessions.remove(session);
    }

}
