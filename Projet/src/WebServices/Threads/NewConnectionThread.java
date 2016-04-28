package WebServices.Threads;

import WebServices.BddRequest.Connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class NewConnectionThread implements Runnable {
    public String pseudo;
    public String password;
    public ArrayList<String> auhthentification;
    private Socket socket;

    public static Connect connect = new Connect();
    public NewConnectionThread(Socket socket)  {
        this.socket=socket;
    }

    @Override
    public void run()  {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> auth = (ArrayList<String>) ois.readObject();
            System.out.println(auth);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(connect.connectionValidate(auth.get(0), auth.get(1)));
            oos.flush();
            pseudo = auth.get(0);
            password = auth.get(1);
            auhthentification = auth;
            if (connect.connectionValidate(pseudo,password)){
                while(true){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
