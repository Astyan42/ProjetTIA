package SocketLayer.Sockets;

import DAL.FileRepository;
import Services.FileContextService;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/Editeur")
public class EditeurWebSocket {
    private Session session;
    private FileContextService fcs;

    @OnOpen
    public void onOpen(Session session,String fileName){
        this.session = session;;
        fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(fileName).id));
    }

    @OnMessage
    public void onMessage(String message) {
        int pos = 0;
        char c = 'a';

        keyboardEvent(pos,c);

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()){
                sendMessage(fcs.getFileContent());
            }
        }
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException ex) {
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
