package ClientLourd.ui.controller.Collaboration;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.interfaces.IColListChangeListener;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ColListController extends Ressource implements IColListChangeListener{
    private final String id;
    private final String type;
    private JList colList;

    public ColListController(JList colList, String id, String type) {
        this.colList=colList;
        this.id=id;
        this.type=type;
    }

    @Override
    public void onColListChange() {
        try {
            Socket client = new Socket(SERVER_ADRESS,PORT_UPDATE);
            ArrayList<String> insert= new ArrayList<String>();
            insert.add("collab");insert.add("show");insert.add(pseudo);insert.add(type);insert.add(id);
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(insert);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            ArrayList<String> result = (ArrayList<String>) ois.readObject();
            colList.setListData(result.toArray());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
