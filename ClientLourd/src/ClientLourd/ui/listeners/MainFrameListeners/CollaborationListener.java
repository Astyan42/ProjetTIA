package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Collaboration.CollaborationController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anthony on 22/04/2016.
 */
public class CollaborationListener extends Ressource implements ActionListener {
    public String object;
    public String type;

    /**
     * initailise le listener
     * @param object
     */
    public CollaborationListener(String object,String type) {
        this.object=object;
        this.type=type;
    }

    /**
     * invoque une fenetre de collaboration relié a l'objet
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new CollaborationController(object,type);
    }
}
