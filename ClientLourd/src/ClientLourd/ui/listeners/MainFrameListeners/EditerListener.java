package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.Editeur.EditeurController;
import ClientLourd.ui.view.Com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EditerListener extends Ressource implements ActionListener {
    /**
     * invoque la fenetre Editeur
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String Content="ceci est le contenu du fichier en string";
        ArrayList<Com> commentaires= new ArrayList<>();
        commentaires.add(new Com("com","pseudo ","date"));
        commentaires.add(new Com("com","pseudo ","date"));
        commentaires.add(new Com("com","pseudo ","date"));
        commentaires.add(new Com("com","pseudo ","date"));
        commentaires.add(new Com("com","pseudo ","date"));
        commentaires.add(new Com("com","pseudo ","date"));

        new EditeurController("file.txt",Content,commentaires);
    }
}

