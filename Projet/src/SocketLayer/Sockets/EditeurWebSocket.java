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
    private static EditeurWebSocket _instance;

    public EditeurWebSocket() {
    }

    public static EditeurWebSocket getInstance() {
        if(_instance == null){
            _instance = new EditeurWebSocket();
        }
        return _instance;
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println("nouvelle connexion WS editeur");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message Received :" + message);
        String[] vars = message.split("-");
        if(vars.length==1){
            fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(vars[0]).id));
            sendMessage(fcs.getFileContent());
            return;
        }
        int pos = Integer.parseInt(vars[0]);
        char c = (char) Integer.parseInt(vars[1]);

        keyboardEvent(pos,c);

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()){
                sendMessage(fcs.getFileContent());
            }
        }
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
