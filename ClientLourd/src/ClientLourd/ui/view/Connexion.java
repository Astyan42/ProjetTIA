package ClientLourd.ui.view;

import javax.swing.*;

public class Connexion extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton connectionButton;
    private JPanel connect;

    public Connexion() {
        setTitle("Connexion");
        setContentPane(connect);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JButton getConnectionButton() {
        return connectionButton;
    }

    public JPanel getConnect() {
        return connect;
    }
}
