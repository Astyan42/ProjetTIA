package ClientLourd.ui.controller.Editeur;

import ClientLourd.ui.controller.Chat.ChatController;
import ClientLourd.ui.view.Com;
import ClientLourd.ui.view.Editeur;

import java.util.ArrayList;

public class EditeurController {
    public EditeurController(String s, String content, ArrayList<Com> commentaires) {
        new Editeur(s,content,commentaires);
        new ChatController();
    }
}
