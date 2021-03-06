package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.XmlArboController;
import ClientLourd.ui.interfaces.IFolderChangeListener;
import ClientLourd.ui.listeners.MainFrameListeners.CollaborationListener;
import ClientLourd.ui.listeners.MainFrameListeners.EditerListener;
import ClientLourd.ui.listeners.MainFrameListeners.RemoveListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class FileListController extends Ressource implements IFolderChangeListener {
    private JList fileList;

    /**
     * initialisation du Controller FileList de la MainFrame
     * @param fileList
     */
    public FileListController(JList fileList) {
        this.fileList = fileList;
        initListeners();
    }

    /**
     * initialisation des listeners
     */
    private void initListeners() {
        fileList.addMouseListener(new FileMouseListener());
    }

    private class FileMouseListener implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JList theList = (JList) e.getSource();
            if (e.getClickCount() == 2 && e.getButton()==MouseEvent.BUTTON1) {
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton()==MouseEvent.BUTTON3){
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    theList.setSelectedIndex(index);
                    int id= Integer.parseInt(xmlDoc.getId(o.toString(),"fichier"));
                    JPopupMenu editMenu = new JPopupMenu();
                    JMenuItem editItem = new JMenuItem("Editer");
                    editItem.setActionCommand("Editer");
                    editItem.addActionListener(new EditerListener(o.toString()));
                    JMenuItem collabItem = new JMenuItem("Droits");
                    collabItem.setActionCommand("Droits");
                    collabItem.addActionListener(new CollaborationListener(o.toString(),"fichier"));

                    JMenuItem delItem = new JMenuItem("Supprimer");
                    delItem.setActionCommand("Supprimer");
                    delItem.addActionListener(new RemoveListener("fichier",id));
                    editMenu.add(editItem);
                    editMenu.add(collabItem);
                    editMenu.addSeparator();
                    editMenu.add(delItem);
                    editMenu.show(fileList, e.getX(), e.getY());
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
    @Override
    public void onFolderChange(String folderURL) {
        fileList.setListData(xmlDoc.getFiles(folderURL).toArray());
    }
}
