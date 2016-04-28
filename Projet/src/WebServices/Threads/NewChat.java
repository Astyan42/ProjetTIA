package WebServices.Threads;

import WebServices.Ressources.GlobalRessources;

import java.net.Socket;

public class NewChat extends GlobalRessources implements Runnable {
    private Socket socket;

    public NewChat(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
