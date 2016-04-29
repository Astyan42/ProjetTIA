package ClientLourd.ui.view;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Editeur extends  JFrame{
    private JTextArea editorPane1;
    private JPanel panel1;
    private JTabbedPane Commentaires;
    private JTextArea textArea1;
    private JButton envoyerButton;
    private JPanel Coms;
    private JPanel Comss;

    public JTextArea getEditorPane1() {
        return editorPane1;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTabbedPane getCommentaires() {
        return Commentaires;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getEnvoyerButton() {
        return envoyerButton;
    }

    public JPanel getComs() {
        return Coms;
    }

    public JPanel getComss() {
        return Comss;
    }

    public Editeur(String fileName, String content, ArrayList<Com> commentaires) {
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(fileName);
        setContentPane(panel1);
        editorPane1.setText(content);

        Coms.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        for (int i =0 ; i<commentaires.size(); i++){
            c.anchor = GridBagConstraints.PAGE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx=1;
            c.ipady = 0;       //reset to default
            c.weighty = 1.0;   //request any extra vertical space
            c.insets = new Insets(10,0,0,0);  //top padding
            c.gridwidth = 1;
            c.gridy=i;
            Coms.add(commentaires.get(i),c,i);
            Component comi = Coms.getComponent(i);
            comi.setSize(400,400);

            comi.setVisible(true);
        }
        Coms.setVisible(true);
        Coms.revalidate();
        validate();
    }
}

