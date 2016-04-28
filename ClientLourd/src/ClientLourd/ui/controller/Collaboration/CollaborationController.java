package ClientLourd.ui.controller.Collaboration;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Collaboration.ColListController;
import ClientLourd.ui.view.Collaboration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class CollaborationController extends Ressource{
    private String object;
    private String id;
    private String type;

    private Collaboration col;
    private JList colList;
    private JButton supprimerCollaborateurButton;
    private JButton modicationDroitsButton;
    private JTextField newCollabo;
    private JButton ajoutButton;
    private JPanel Collab;
    private JCheckBox ecritureCheckBox;
    private JCheckBox adminCheckBox;

    private ColListController colListController;

    /**
     * initilisation du Controller de frame Collaboration avec l'object
     * @param object
     */
    public CollaborationController(String object,String type) {
        this.type=type;
        this.object=object;
        this.id=xmlDoc.getId(object,type);
        System.out.println(id);
        initComponent();
        initController();
        initListener();
        evtManager.TriggerColListChangeEvent();
    }

    /**
     * initialisation du contenu
     */
    private void initComponent() {
        col=new Collaboration(object);
        colList=col.getColList();
        supprimerCollaborateurButton=col.getSupprimerCollaborateurButton();
        modicationDroitsButton=col.getModicationDroitsButton();
        newCollabo=col.getNewCollabo();
        ajoutButton=col.getAjoutButton();
        Collab=col.getCollab();
        ecritureCheckBox=col.getEcritureCheckBox();
        adminCheckBox=col.getAdminCheckBox();
        adminCheckBox.setSelected(false);
        ecritureCheckBox.setSelected(true);
    }

    /**
     * initialisation des controller
     */
    private void initController() {
        colListController = new ColListController(colList,id,type);
    }

    /**
     * initialisation des listeners
     */
    private void initListener() {
        evtManager.AddColListChangeListener(colListController);
        ajoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket client = new Socket(SERVER_ADRESS,PORT_UPDATE);
                    ArrayList<String> insert= new ArrayList<String>();
                    insert.add("collab");insert.add("add");insert.add(pseudo);insert.add(type);insert.add(id);insert.add(newCollabo.getText());
                    if(adminCheckBox.isSelected())insert.add("1");
                    else insert.add("0");
                    if(ecritureCheckBox.isSelected())insert.add("1");
                    else insert.add("0");
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    oos.writeObject(insert);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    ArrayList<String> result = (ArrayList<String>) ois.readObject();
                    if (result.size()>0){
                        JOptionPane.showMessageDialog(col,
                                result,
                                "error: ",
                                JOptionPane.ERROR_MESSAGE);
                    }else evtManager.TriggerColListChangeEvent();
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        supprimerCollaborateurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colList.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(col,
                            "aucun collaborateur selectionné",
                            "error: ",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        Socket client = new Socket(SERVER_ADRESS,PORT_UPDATE);
                        ArrayList<String> insert= new ArrayList<String>();
                        insert.add("collab");insert.add("remove");insert.add(pseudo);insert.add(type);insert.add(id);insert.add((String)colList.getSelectedValue());
                        if(adminCheckBox.isSelected())insert.add("1");
                        else insert.add("0");
                        if(ecritureCheckBox.isSelected())insert.add("1");
                        else insert.add("0");
                        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                        oos.writeObject(insert);
                        oos.flush();
                        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                        ArrayList<String> result = (ArrayList<String>) ois.readObject();
                        if (result.size()>0){
                            JOptionPane.showMessageDialog(col,
                                    result,
                                    "error: ",
                                    JOptionPane.ERROR_MESSAGE);
                        }else evtManager.TriggerColListChangeEvent();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        modicationDroitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colList.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(col,
                            "aucun collaborateur selectionné",
                            "error: ",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        Socket client = new Socket(SERVER_ADRESS,PORT_UPDATE);
                        ArrayList<String> insert= new ArrayList<String>();
                        insert.add("collab");insert.add("modif");insert.add(pseudo);insert.add(type);insert.add(id);insert.add((String)colList.getSelectedValue());
                        if(adminCheckBox.isSelected())insert.add("1");
                        else insert.add("0");
                        if(ecritureCheckBox.isSelected())insert.add("1");
                        else insert.add("0");
                        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                        oos.writeObject(insert);
                        oos.flush();
                        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                        ArrayList<String> result = (ArrayList<String>) ois.readObject();
                        if (result.size()>0){
                            JOptionPane.showMessageDialog(col,
                                    result,
                                    "error: ",
                                    JOptionPane.ERROR_MESSAGE);
                        }else evtManager.TriggerColListChangeEvent();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
