package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.XmlArboController;
import ClientLourd.ui.interfaces.IFolderChangeListener;
import ClientLourd.ui.listeners.MainFrameListeners.CollaborationListener;
import ClientLourd.ui.listeners.MainFrameListeners.RemoveListener;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class FolderListController extends Ressource implements IFolderChangeListener {
    private JList folderList;

    /**
     * initialisiation du controller de FolderList
     * @param folderList JList de la MainFrame
     */
    public FolderListController(JList folderList) {
        this.folderList = folderList;
        initListeners();
    }

    /**
     * initialisation des Listeners
     */
    private void initListeners() {
        folderList.addMouseListener(new FolderDoubleClick());
    }

    /**
     * met a jour la liste au changement d'un dossier
     * @param folderURL
     */
    @Override
    public void onFolderChange(String folderURL) {
        folderList.setListData(xmlDoc.getFolders(folderURL).toArray());
    }


    private class FolderDoubleClick implements java.awt.event.MouseListener {
        @Override
        /**
         * controlle du click gauche de la souris
         */
        public void mouseClicked(MouseEvent e) {
            JList theList = (JList) e.getSource();

            if (e.getClickCount() >= 2 && e.getButton()==MouseEvent.BUTTON1) {
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    evtManager.TriggerFolderChangeEvent(o.toString());

                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * controlle du click droit de la souris
         * @param e
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton()==MouseEvent.BUTTON3){
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    theList.setSelectedIndex(index);
                    JPopupMenu editMenu=new JPopupMenu();
                    JMenuItem openItem = new JMenuItem("Ouvrir");
                    openItem.setActionCommand("Ouvrir");

                    openItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Object o = theList.getModel().getElementAt(index);
                            evtManager.TriggerFolderChangeEvent(o.toString());
                        }
                    });
                    int id= Integer.parseInt(xmlDoc.getId(o.toString(),"projet"));
                    JMenuItem collabItem = new JMenuItem("Collaboration");
                    collabItem.setActionCommand("Collaboration");
                    collabItem.addActionListener(new CollaborationListener(o.toString(),"projet"));

                    JMenuItem delItem = new JMenuItem("Supprimer");
                    delItem.setActionCommand("Supprimer");
                    delItem.addActionListener(new RemoveListener("projet",id));
                    editMenu.add(openItem);
                    editMenu.add(collabItem);
                    editMenu.addSeparator();
                    editMenu.add(delItem);
                    editMenu.show(folderList,e.getX(),e.getY());
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
