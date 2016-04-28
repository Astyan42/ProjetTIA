package ClientLourd.ui.listeners.MainFrameListeners;

import ClientLourd.ui.Ressource;
import ClientLourd.ui.controller.MainFrame.NewProjectController;
import ClientLourd.ui.view.NewProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anthony on 22/04/2016.
 */
public class NewProjectlistener extends Ressource implements ActionListener {

    /**
     * Invoque une fenetre de nouveau projet
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new NewProjectController();
    }
}
