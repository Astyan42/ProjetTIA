package SocketLayer;

import DAL.FileRepository;
import Services.FileContextService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewEditeur implements Runnable {
    private Socket socket;
    private FileContextService fcs;

    public NewEditeur(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> Insert = (ArrayList<String>) ois.readObject();
            String fileName =Insert.get(0);
            fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(fileName).id));
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(fcs.getFileContent());
            oos.flush();
            while (!socket.isClosed()){
                ArrayList<String> ajout = (ArrayList<String>) ois.readObject();
                int pos = Integer.parseInt(ajout.get(0));
                char carac = ajout.get(1).charAt(0);
                fcs.insertCharacter(pos,carac);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
