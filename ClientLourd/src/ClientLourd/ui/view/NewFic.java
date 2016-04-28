package ClientLourd.ui.view;

import javax.swing.*;

public class NewFic extends JFrame{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;
    private JButton validerButton;
    private JButton annulerButton;
    private JTextField textField1;
    private JPanel newfic;
    private String arianne;

    public NewFic(String a) {
        arianne=a;
        setTitle("URL: "+arianne);
        setContentPane(newfic);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPanel getNewfic() {
        return newfic;
    }
}
