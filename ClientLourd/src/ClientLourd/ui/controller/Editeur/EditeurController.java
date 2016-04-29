package ClientLourd.ui.controller.Editeur;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Chat.ChatController;
import ClientLourd.ui.view.Com;
import ClientLourd.ui.view.Editeur;

import javax.swing.*;
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
    private JEditorPane editorPane1;

    public EditeurController(String name) {
        ArrayList<Com> commentaires=new ArrayList<>();
        String content="";
        Socket client= null;
        try {
            client = new Socket(SERVER_ADRESS,PORT_EDITEUR);
            ArrayList<String> insert= new ArrayList<String>();
            insert.add(name);
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(insert);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
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
        
    }
}
