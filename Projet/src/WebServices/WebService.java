package WebServices;

import WebServices.Chat.Chat;
import WebServices.Ressources.GlobalRessources;
import WebServices.Threads.Sockets.*;

import java.io.IOException;

public class WebService extends GlobalRessources {
    /**
     * Execute toutes les sockets
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        chat = new Chat();
        new Thread(new ConnectionSocket()).start();
        new Thread(new XmlUpdateSocket()).start();
        new Thread(new ChatSocket()).start();
        new Thread(new EditeurSocket()).start();
        new Thread(new UpdateSocket()).start();
    }
}
