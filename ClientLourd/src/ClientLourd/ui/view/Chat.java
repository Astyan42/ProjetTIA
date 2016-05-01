package ClientLourd.ui.view;

import javax.swing.*;

public class Chat extends JFrame{
    private JList utilisateur;
    private JTextPane chat;
    private JTextArea newMessage;
    private JButton envoyer;
    private JPanel chatPanel;

    public Chat() {
        setContentPane(chatPanel);
        setVisible(true);
        setSize(500, 300);
        setLocationRelativeTo(null);
        utilisateur.setSize(80,80);
    }

    public JList getUtilisateur() {
        return utilisateur;
    }

    public JTextPane getChat() {
        return chat;
    }

    public JTextArea getNewMessage() {
        return newMessage;
    }

    public JButton getEnvoyer() {
        return envoyer;
    }

    public JPanel getChatPanel() {
        return chatPanel;
    }
}
