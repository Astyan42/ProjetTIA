package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Chat.ChatController;
import ClientLourd.ui.controller.Editeur.EditeurController;
import ClientLourd.ui.view.Com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EditerListener extends Ressource implements ActionListener {
    private String fic;
    public EditerListener(String fic) {
        this.fic=fic;
    }

    /**
     * invoque la fenetre Editeur
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new EditeurController(fic);
        //new ChatController(fic);
    }
}

