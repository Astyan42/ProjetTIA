package SocketLayer.Sockets;


import WebServices.Ressources.ThreadSocketRessources;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class XmlUpdateSocket extends ThreadSocketRessources implements Runnable {
    public void run() {
        int count=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT_XML_UP)) {

            System.out.println("I'm waiting here: XML \n"+serverSocket.getLocalSocketAddress());

            while (true) {

                Socket socket = serverSocket.accept();

                count++;
                System.out.println("#" + count + " xml Update from "
                        + socket.getInetAddress() + ":"
                        + socket.getPort());

                new Thread(new SocketLayer.NewXmlUpdate(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
