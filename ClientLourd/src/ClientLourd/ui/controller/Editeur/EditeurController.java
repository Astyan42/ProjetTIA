package ClientLourd.ui.controller.Editeur;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Chat.ChatController;
import ClientLourd.ui.view.Com;
import ClientLourd.ui.view.Editeur;

import javax.swing.*;
import javax.websocket.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@ClientEndpoint
public class EditeurController extends Ressource implements Serializable {
    private final Editeur editeur;
    private JPanel Comss;
    private JPanel Coms;
    private JButton envoyerButton;
    private JTextArea textArea1;
    private JTabbedPane Commentaires;
    private JPanel panel1;
    private JTextArea editorPane1;

    private Socket client= null;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private int pos=0;
    // webSocket
    private final String uri="ws://localhost:7070/Editeur";
    private Session session;
    

    public EditeurController(String name) {
        ArrayList<Com> commentaires=new ArrayList<>();
        String content="";
        try {
            WebSocketContainer container= ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(this , new URI(uri));

        } catch (DeploymentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        editeur = new Editeur(name,content,commentaires);
        initComponent();
        initController();
        sendMessage(name);
        initListener();
        new ChatController();
    }

    private void initComponent() {
        editorPane1 = editeur.getEditorPane1();
        panel1 = editeur.getPanel1();
        Commentaires = editeur.getCommentaires();
        textArea1 = editeur.getTextArea1();
        envoyerButton = editeur.getEnvoyerButton();
        Coms= editeur.getComs();
        Comss = editeur.getComss();
    }

    private void initController() {
    }
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
    }

    @OnMessage
    public void onMessage(String message, Session session){
        if (!message.equals(null)){
            System.out.println("Message Received :\n" + message);
            editorPane1.setText(message);
            editorPane1.setCaretPosition(pos+1);
        }
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void initListener() {
        editorPane1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                pos = editorPane1.getCaretPosition();
                String array = pos+"-"+c;
                if(c!=Character.MIN_VALUE){
                    sendMessage(array);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
