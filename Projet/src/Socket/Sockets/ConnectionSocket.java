package Socket.Sockets;

import WebServices.Ressources.ThreadSocketRessources;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionSocket extends ThreadSocketRessources implements Runnable {

    public void run() {
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT_CONNECT)) {

            System.out.println("I'm waiting here: Connect \n"+serverSocket.getLocalSocketAddress());

            while (true) {

                Socket socket = serverSocket.accept();

                count++;
                System.out.println("#" + count + " Connect from "
                        + socket.getInetAddress() + ":"
                        + socket.getPort());
                new Thread(new NewConnectionThread(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
