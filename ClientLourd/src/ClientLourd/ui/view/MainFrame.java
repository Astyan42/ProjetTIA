package ClientLourd.ui.view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 500;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton nouveauProjectButton;
    private JButton nouveauFicButtun;
    private JList fileList;
    private JList folderList;
    private JButton collaborationButton;
    private JButton editerButton;
    private JButton retourButton;
    private JLabel filDArianneLabel;

    public MainFrame() {
        setContentPane(panel1);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JLabel getFilDArianneLabel() {
        return filDArianneLabel;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JButton getEditerButton() {
        return editerButton;
    }

    public JButton getCollaborationButton() {
        return collaborationButton;
    }

    public JList getFolderList() {
        return folderList;
    }

    public JList getFileList() {
        return fileList;
    }

    public JButton getNouveauProjectButton() {
        return nouveauProjectButton;
    }

    public JButton getNouveauFicButtun() {
        return nouveauFicButtun;
    }

}
