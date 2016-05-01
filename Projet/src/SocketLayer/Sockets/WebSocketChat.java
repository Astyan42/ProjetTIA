package SocketLayer.Sockets;

import Services.ChatService;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;


@ServerEndpoint("/Chat/{file}")
public class WebSocketChat {
    private Session session;
    private ChatService chatService;
    private ArrayList<Session> sessions = new ArrayList<>();
    private ArrayList<String>connected = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("file") final String file){
        System.out.println("nouvelle connexion WS editeur");
        chatService= ChatService.getInstance(file);
        this.session = session;
        sessions.add(session);
        sendMessage(chatService.getMessages());
    }

    @OnMessage
    public void onMessage(String message){
        String[] split = message.split(" ");
        String pseudo = split[0];
        if(!connected.contains(pseudo))connected.add(pseudo);
        message.substring(0,pseudo.length()+1);
        chatService.addMessage(pseudo,message);
        sendMessage(chatService.getMessages());
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
