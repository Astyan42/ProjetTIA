package SocketLayer.Sockets;
import WebServices.Ressources.ThreadSocketRessources;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EditeurSocket extends ThreadSocketRessources implements Runnable {
    public void run(){
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT_EDITEUR)) {

            System.out.println("I'm waiting here: Editeur \n"+serverSocket.getLocalSocketAddress());
            while(true) {
                Socket socket = serverSocket.accept();
                count++;
                System.out.println("#" + count + " Editeur from "
                        + socket.getInetAddress() + ":"
                        + socket.getPort());
                new Thread(new SocketLayer.NewEditeur(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
