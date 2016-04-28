package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anthony on 22/04/2016.
 */
public class RetourListener extends Ressource implements ActionListener {
    /**
     * gestion du click du bouton retour
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] arianneTab = arianne.getText().split("/");
            String url = arianneTab[arianneTab.length-1];
            evtManager.TriggerFolderChangeEvent(xmlDoc.getParent(url));
    }
}
