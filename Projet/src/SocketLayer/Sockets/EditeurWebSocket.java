package SocketLayer.Sockets;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anthony on 29/04/2016.
 */


@ServerEndpoint("/Editeur")
public class EditeurWebSocket {
    @OnMessage
    public void onMessage(Session session, String message) {
        String[] messages = message.split("-");
        int pos = Integer.parseInt(messages[0]);
        char c = messages[1].charAt(0);
    }
}
