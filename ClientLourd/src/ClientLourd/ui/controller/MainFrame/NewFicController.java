package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.view.NewFic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewFicController  extends Ressource {
    private NewFic newFic ;
    private JButton validerButton;
    private JButton annulerButton;
    private JTextField textField1;
    private JPanel newfic;
    public NewFicController() {
        newFic = new NewFic(arianne.getText());
        initComponent();
        initListener();
    }

    private void initComponent() {
        validerButton=newFic.getValiderButton();
        annulerButton=newFic.getAnnulerButton();
        textField1=newFic.getTextField1();
        newfic=newFic.getNewfic();
    }

    private void initListener() {
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket client= null;
                try {
                    client = new Socket(SERVER_ADRESS,PORT_UPDATE);
                    ArrayList<String> insert= new ArrayList<String>();
                    insert.add("fichier");insert.add("add");insert.add(pseudo);insert.add(idProjectHere);insert.add(textField1.getText());
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                    oos.writeObject(insert);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    String result = (String) ois.readObject();
                    if(!result.equals(null)) {
                        newFic.dispose();
                        evtManager.TriggerFolderChangeEvent(ProjectHere);
                    }
                    else{
                        JOptionPane.showMessageDialog(newFic,result,
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFic.dispose();
            }
        });
    }
}
