package ClientLourd.ui.controller.Chat;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.view.Chat;

import javax.swing.*;
import javax.websocket.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class ChatController extends Ressource {
    private Chat chatFrame;
    private JList utilisateur;
    private JTextPane chat;
    private JTextArea newMessage;
    private JButton envoyer;
    private JPanel chatPanel;
    // webSocket
    private final String uri="ws://localhost:7070/Chat/";
    private Session session;

    public ChatController(String filename) {
        try {
            WebSocketContainer container= ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(this , new URI(uri+filename));

        } catch (DeploymentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        initComponent();
        initListeners();
    }
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        chat.setText(chat.getText()+message);
    }
    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void initListeners() {
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(pseudo+" "+newMessage.getText());
                newMessage.setText("");
            }
        });
    }

    private void initComponent() {
        chatFrame = new Chat();
        utilisateur = chatFrame.getUtilisateur();
        chat = chatFrame.getChat();
        newMessage = chatFrame.getNewMessage();
        envoyer = chatFrame.getEnvoyer();
        chatPanel = chatFrame.getChatPanel();
    }
}
