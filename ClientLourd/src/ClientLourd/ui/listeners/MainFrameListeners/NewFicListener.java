package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.MainFrame.NewFicController;
import ClientLourd.ui.view.NewFic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anthony on 22/04/2016.
 */
public class NewFicListener extends Ressource implements ActionListener {

    /**
     * invoque une fenetre de nouveau fichier
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
            new NewFicController();
        }
}

