package SocketLayer;

import WebServices.BddRequest.Arborescence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewXmlUpdate implements Runnable {
    private Socket socket;
    public static Arborescence arbo = new Arborescence();

    public NewXmlUpdate(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String pseudo = (String) ois.readObject();
            String XML =arbo.getArbo(pseudo);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(XML);
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
