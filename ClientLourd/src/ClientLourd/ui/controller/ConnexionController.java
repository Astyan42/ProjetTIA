package ClientLourd.ui.controller;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.MainFrame.MainFrameController;
import ClientLourd.ui.view.Connexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ConnexionController extends Ressource {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton connectionButton;
    private JPanel connect;
    private Connexion connectWindow;


    /**
     * initialisation du Controller de Connexion
     */
    public ConnexionController()  {
        initComponent();
        initListeners();
    }

    /**
     * initialisation des listeners
     */
    private void initListeners() {
        connectionButton.addActionListener(new ConnexionListener());
    }

    /**
     * initialisation du contenu
     */
    private void initComponent() {
        connectWindow= new Connexion();
        connect = connectWindow.getConnect();
        connectionButton = connectWindow.getConnectionButton();
        passwordField1 = connectWindow.getPasswordField1();
        textField1 = connectWindow.getTextField1();


        textField1.setText("pseudo");
        passwordField1.setText("password");
    }


    private class ConnexionListener extends Ressource implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            ArrayList<String>auth = new ArrayList<>();
            pseudo = textField1.getText();
            char[] password = passwordField1.getPassword();
            auth.add(pseudo);
            auth.add(String.valueOf(password));
            try {
                Socket client= new Socket(SERVER_ADRESS,PORT_CONNECT);
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(auth);
                oos.flush();
                try {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    boolean result = (boolean)ois.readObject();
                    if (result){
                        System.out.println("connection reussi");
                        connectWindow.dispose();

                        new MainFrameController();
                    }else{
                        JOptionPane.showMessageDialog(connectWindow,
                                "Pseudo et/ou Mot de Passe incorrect",
                                "Connexion error",
                                JOptionPane.ERROR_MESSAGE);
                        passwordField1.setText("");
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
