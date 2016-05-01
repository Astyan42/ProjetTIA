package SocketLayer.Sockets;

import Services.ChatService;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;


@ServerEndpoint("/Chat")
public class WebSocketChat {
    private Session session;
    private ChatService chatService;
    private ArrayList<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session){
        System.out.println("nouvelle connexion WS editeur");
        this.session = session;
        sessions.add(session);
    }



}
