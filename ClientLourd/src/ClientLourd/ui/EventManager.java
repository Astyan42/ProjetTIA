package ClientLourd.ui;

import ClientLourd.ui.controller.XmlArboController;
import ClientLourd.ui.interfaces.IColListChangeListener;
import ClientLourd.ui.interfaces.IFolderChangeListener;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class EventManager extends Ressource {
    private ArrayList<IFolderChangeListener> FolderChangeListeners;
    private ArrayList<IColListChangeListener> ColListChangeListeners;

    /**
     * initialisation de l'eventManager
     */
    public EventManager() {
        FolderChangeListeners=new ArrayList<IFolderChangeListener>();
        ColListChangeListeners=new ArrayList<>();
    }

    /**
     * inscrit des classe a l'evenement FolderChange
     * @param ifcl
     */
    public void AddFolderChangeListener(IFolderChangeListener ifcl){
        FolderChangeListeners.add(ifcl);
    }

    /**
     * lance l'evennement onFolderChange sur toute les interfaces inscrites
     * @param folderURL
     */
    public void TriggerFolderChangeEvent(String folderURL){
        getXmlDoc();
        for (IFolderChangeListener listener:FolderChangeListeners) {
            listener.onFolderChange(folderURL);
        }
    }

    /**
     * inscrit des classe a l'evenement ColListChange
     * @param ifcl
     */
    public void AddColListChangeListener(IColListChangeListener ifcl){
        ColListChangeListeners.add(ifcl);
    }

    /**
     * lance l'evennement onColListChange sur toute les interfaces inscrites
     */
    public void TriggerColListChangeEvent(){
        for (IColListChangeListener listener:ColListChangeListeners) {
            listener.onColListChange();
        }
    }

    /**
     * lance la recuperation du XmlDoc
     */
    public void getXmlDoc(){
        Socket client= null;
        try {
            client = new Socket(SERVER_ADRESS,PORT_XML_UP);

            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            oos.writeObject(pseudo);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

            String XMLString = (String) ois.readObject();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("arbo.xml")));
// normalement si le fichier n'existe pas, il est crée à la racine du projet
            writer.write(XMLString);

            writer.close();
            xmlDoc= new XmlArboController("arbo.xml");

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
