package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.view.NewProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewProjectController extends Ressource {
    private NewProject newProject;
    private JButton validerButton;
    private JButton annulerButton;
    private JTextField textField1;
    private JPanel newproj;


    public NewProjectController() {
        newProject = new NewProject(arianne.getText());
        initComponent();
        initListener();
    }

    private void initListener() {
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket client= null;
                try {
                    client = new Socket(SERVER_ADRESS,PORT_UPDATE);
                    ArrayList<String> insert= new ArrayList<String>();
                    insert.add("projet");insert.add("add");insert.add(pseudo);insert.add(idProjectHere);insert.add(textField1.getText());
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                    oos.writeObject(insert);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    String result = (String) ois.readObject();
                    if(!result.equals(null)) {
                        newProject.dispose();
                        evtManager.TriggerFolderChangeEvent(ProjectHere);
                    }
                    else{
                        JOptionPane.showMessageDialog(newProject,result+" création impossible",
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
                newProject.dispose();
            }
        });
    }

    private void initComponent() {
        validerButton=newProject.getValiderButton();
        annulerButton=newProject.getAnnulerButton();
        textField1=newProject.getTextField1();
        newproj=newProject.getNewproj();
    }


}
