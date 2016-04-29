package SocketLayer;

import DAL.FileRepository;
import Services.FileContextService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class NewEditeur implements Runnable {
    private Socket socket;
    private FileContextService fcs;

    public NewEditeur(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream ois =new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos =new ObjectOutputStream(socket.getOutputStream());
            ArrayList<String> Insert = (ArrayList<String>) ois.readObject();
            String fileName =Insert.get(0);
            fcs = FileContextService.getInstance(String.valueOf(FileRepository.getInstance().getFileByName(fileName).id));
            oos.writeObject(fcs.getFileContent());
            oos.flush();
            while (!socket.isClosed()){
                ArrayList<String> array = new ArrayList<>();
                array = (ArrayList<String>) ois.readObject();
                int pos = Integer.parseInt(array.get(0));
                char carac = array.get(1).charAt(0);
                fcs.insertCharacter(pos,carac);

                boolean maj=true;
                oos.writeBoolean(maj);
                oos.flush();

                if (((String) ois.readObject()).equals("update")){
                    oos.writeObject(fcs.getFileContent());

                }else oos.writeObject("gr");
                oos.flush();
            }
            ois.close();
            oos.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
