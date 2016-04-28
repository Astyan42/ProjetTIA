package SocketLayer;

import java.net.Socket;

public class NewEditeur implements Runnable {
    private Socket socket;

    public NewEditeur(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {

    }
}
