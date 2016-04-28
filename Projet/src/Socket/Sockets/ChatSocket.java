package Socket.Sockets;

import WebServices.Ressources.ThreadSocketRessources;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSocket extends ThreadSocketRessources implements Runnable {
    public void run(){
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT_CHAT)) {

            System.out.println("I'm waiting here: Chat \n"+serverSocket.getLocalSocketAddress());

            while (true) {

                Socket socket = serverSocket.accept();

                count++;
                System.out.println("#" + count + " Chat from "
                        + socket.getInetAddress() + ":"
                        + socket.getPort());

                //new Thread(new Socket.NewChat(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
