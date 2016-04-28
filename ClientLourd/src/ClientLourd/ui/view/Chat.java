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
    }
}
