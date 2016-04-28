package ClientLourd.ui.view;

import javax.swing.*;

public class Collaboration extends JFrame{
    private JList colList;
    private JButton supprimerCollaborateurButton;
    private JButton modicationDroitsButton;
    private JTextField newCollabo;
    private JButton ajoutButton;
    private JPanel Collab;
    private JCheckBox lectureCheckBox;
    private JCheckBox ecritureCheckBox;
    private JCheckBox adminCheckBox;
    private String arianne;

    public Collaboration(String a) {
        setContentPane(Collab);
        arianne=a;
        setTitle("URL: "+arianne);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JList getColList() {
        return colList;
    }

    public JButton getSupprimerCollaborateurButton() {
        return supprimerCollaborateurButton;
    }

    public JButton getModicationDroitsButton() {
        return modicationDroitsButton;
    }

    public JTextField getNewCollabo() {
        return newCollabo;
    }

    public JButton getAjoutButton() {
        return ajoutButton;
    }

    public JPanel getCollab() {
        return Collab;
    }

    public JCheckBox getLectureCheckBox() {
        return lectureCheckBox;
    }

    public JCheckBox getEcritureCheckBox() {
        return ecritureCheckBox;
    }

    public JCheckBox getAdminCheckBox() {
        return adminCheckBox;
    }
}

