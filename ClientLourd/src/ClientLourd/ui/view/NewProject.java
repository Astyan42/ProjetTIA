package ClientLourd.ui.view;

import javax.swing.*;
import javax.swing.text.StringContent;

public class NewProject extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;
    private JButton validerButton;
    private JButton annulerButton;
    private JTextField textField1;
    private JPanel newproj;
    private String arianne;


    public NewProject(String a) {
        arianne=a;
        setTitle("URL: "+arianne);
        setContentPane(newproj);
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

    public JPanel getNewproj() {
        return newproj;
    }
}


