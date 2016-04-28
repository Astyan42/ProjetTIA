package ClientLourd.ui.view;

import javax.swing.*;

public class Com extends JPanel{
    private JTextArea text;
    private JLabel pseudo;
    private JLabel date;
    private JPanel com;

    public Com(String comText, String comPseudo, String comDate) {
        setVisible(true);
        text.setText(comText);
        pseudo.setText(comPseudo);
        date.setText(comDate);
        add(com);
    }
}
