package ClientLourd.ui;

import ClientLourd.ui.controller.XmlArboController;

import javax.swing.*;

public class Ressource {
    public static EventManager evtManager = new EventManager();
    public static XmlArboController xmlDoc;
    public static JLabel arianne;
    public static String pseudo;
    public static String idProjectHere;
    public static String ProjectHere;

    public final String SERVER_ADRESS = "127.0.0.1";
    public final int PORT_CONNECT= 40400;
    public final int PORT_XML_UP= 52000;
    public final int PORT_CHAT = 44444;
    public final int PORT_EDITEUR = 48200;
    public final int PORT_UPDATE = 47000;
}
