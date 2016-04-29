package ClientLourd.ui.controller.Editeur;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Chat.ChatController;
import ClientLourd.ui.view.Com;
import ClientLourd.ui.view.Editeur;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class EditeurController extends Ressource {
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
    public EditeurController(String name) {
        ArrayList<Com> commentaires=new ArrayList<>();
        String content="";

        try {
            client = new Socket(SERVER_ADRESS,PORT_EDITEUR);
            ArrayList<String> insert= new ArrayList<String>();
            insert.add(name);
            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(insert);
            oos.flush();
            ois = new ObjectInputStream(client.getInputStream());
            content = (String) ois.readObject();

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        editeur = new Editeur(name,content,commentaires);
        initComponent();
        initController();
        initListener();
        new ChatController();
    }

    private void initComponent() {
        editorPane1 =editeur.getEditorPane1();
        panel1 =editeur.getPanel1();
        Commentaires =editeur.getCommentaires();
        textArea1 =editeur.getTextArea1();
        envoyerButton =editeur.getEnvoyerButton();
        Coms=editeur.getComs();
        Comss =editeur.getComss();
    }

    private void initController() {
    }

    private void initListener() {
        editorPane1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int pos = editorPane1.getCaretPosition();
                char c = e.getKeyChar();

                try {
                    oos.writeInt(pos);
                    oos.flush();
                    oos.writeChar(c);
                    oos.flush();
                    Boolean content = ois.readBoolean();
                    String query="";
                    if (content){
                        // demande la mise a jour du fichier au web service
                        query="update";
                    }
                    oos.writeObject(query);
                    oos.flush();
                    if (query.equals("update")){
                        String newContent =(String) ois.readObject();
                        editorPane1.setText(newContent);
                        e.setKeyCode(127);
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
