package ClientLourd.ui.controller.MainFrame;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.interfaces.IFolderChangeListener;

import javax.swing.*;

public class ArianneController extends Ressource implements IFolderChangeListener {
    private JLabel arrianne;

    /**
     * initialise le fil d'arianne de la MainFrame
     * @param a
     */
    public ArianneController(JLabel a) {
        arrianne=a;
        arrianne.setText("racine/");
    }

    /**
     * met a jour le fil d'ariane au changement de dossier
     * @param folderURL
     */
    @Override
    public void onFolderChange(String folderURL) {
        arrianne.setText(xmlDoc.getArianne(folderURL));
    }
}
