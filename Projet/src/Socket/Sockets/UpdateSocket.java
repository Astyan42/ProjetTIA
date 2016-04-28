package Socket.Sockets;

import WebServices.Ressources.ThreadSocketRessources;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UpdateSocket extends ThreadSocketRessources implements Runnable {
    public void run() {
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT_UPDATE)) {

            System.out.println("I'm waiting here: Update \n"+serverSocket.getLocalSocketAddress());

            while (true) {

                Socket socket = serverSocket.accept();

                count++;
                System.out.println("#" + count + " Update from "
                        + socket.getInetAddress() + ":"
                        + socket.getPort());

                new Thread(new Socket.NewUpdate(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}