package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Anthony on 29/04/2016.
 */
public class RemoveListener extends Ressource implements ActionListener {
    private String type;
    private int id;
    public RemoveListener(String type, int id) {
        this.type=type;
        this.id=id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Socket client= null;
        try {
            client = new Socket(SERVER_ADRESS,PORT_UPDATE);
            ArrayList<String> insert= new ArrayList<String>();
            insert.add(type);insert.add("remove");insert.add(pseudo);insert.add(String.valueOf(id));
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            oos.writeObject(insert);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Boolean result = ois.readBoolean();
            if(!result) {
                evtManager.TriggerFolderChangeEvent(ProjectHere);
            }
            else{
                JOptionPane.showMessageDialog(mainFrame,result+" création impossible",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
