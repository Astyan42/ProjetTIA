package SocketLayer.Sockets;

import DAL.FileRepository;
import Services.FileContextService;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint("/Editeur")
public class EditeurWebSocket {
    private Session session;
    private FileContextService fcs;
    private ArrayList<Session> sessions = new ArrayList<>();


    @OnOpen
    public void onOpen(Session session){
        System.out.println("nouvelle connexion WS editeur");
        this.session = session;
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String[] vars = message.split("-");
        System.out.println(message+" "+vars.length);
        if(vars.length==1){
            fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(vars[0]).id));
            sendMessage(fcs.getFileContent());
            return;
        }
        int pos = Integer.parseInt(vars[0]);
        char c = vars[1].charAt(0);

        keyboardEvent(pos,c);


    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
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
            case 127:
                fcs.deleteCharacter(pos,false);
                break;
            case 8 :
                fcs.deleteCharacter(pos,true);
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

}
