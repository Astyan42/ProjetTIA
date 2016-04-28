package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.EventManager;
import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.MainFrame.ArianneController;
import ClientLourd.ui.controller.MainFrame.FileListController;
import ClientLourd.ui.controller.MainFrame.FolderListController;
import ClientLourd.ui.interfaces.IFolderChangeListener;
import ClientLourd.ui.listeners.MainFrameListeners.*;
import ClientLourd.ui.view.*;

import javax.swing.*;


public class MainFrameController extends Ressource implements IFolderChangeListener{
    private MainFrame mainFrame;
    private JButton nouveauProjectButton;
    private JButton nouveauFicButtun;
    private JButton collaborationButton;
    private JButton editerButton;
    private JButton retourButton;

    private JList fileList;
    private JList folderList;

    private ArianneController arianneController;
    private FileListController fileListController;
    private FolderListController folderListController;

    /**
     * initialisation de la MainFrame
     */
    public MainFrameController() {
        initComponent();
        initController();
        initListener();
        evtManager.TriggerFolderChangeEvent("/");
        mainFrame.setVisible(true);
    }

    @Override
    public void onFolderChange(String folderURL) {
        if (folderURL.equals("/")) {
            collaborationButton.setEnabled(false);
            retourButton.setEnabled(false);
        }else {
            collaborationButton.setEnabled(true);
            retourButton.setEnabled(true);
        }
    }

    /**
     * initialisation de tout les contenu de la MainFrame
     */
    private void initComponent() {
        mainFrame= new MainFrame();
        arianne = mainFrame.getFilDArianneLabel();
        nouveauFicButtun=mainFrame.getNouveauFicButtun();
        nouveauProjectButton=mainFrame.getNouveauProjectButton();
        editerButton=mainFrame.getEditerButton();
        retourButton=mainFrame.getRetourButton();
        fileList =mainFrame.getFileList();
        folderList =mainFrame.getFolderList();
        collaborationButton = mainFrame.getCollaborationButton();
        evtManager= new EventManager();
    }

    /**
     * initiatialisation des Controlers
     */
    private void initController(){
        arianneController = new ArianneController(arianne);
        fileListController = new FileListController(fileList);
        folderListController = new FolderListController(folderList);
    }

    /**
     * initialisation des Listeners
     */
    private void initListener() {
        nouveauProjectButton.addActionListener(new NewProjectlistener());
        nouveauFicButtun.addActionListener(new NewFicListener());
        collaborationButton.addActionListener(new CollaborationListener(ProjectHere,"projet"));
        editerButton.addActionListener(new EditerListener());
        retourButton.addActionListener(new RetourListener());

        evtManager.AddFolderChangeListener(arianneController);
        evtManager.AddFolderChangeListener(fileListController);
        evtManager.AddFolderChangeListener(folderListController);
        evtManager.AddFolderChangeListener(this);
    }



}
