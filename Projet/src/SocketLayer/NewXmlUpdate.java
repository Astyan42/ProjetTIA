package SocketLayer;

import Services.ArborescenceService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewXmlUpdate implements Runnable {
    private Socket socket;

    public NewXmlUpdate(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String pseudo = (String) ois.readObject();
            String XML = ArborescenceService.getInstance().getArbo(pseudo);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(XML);
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
