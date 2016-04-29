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
                int pos = ois.readInt();
                char carac = ois.readChar();

                try {
                    keyboardEvent(pos,carac);
                }catch (Exception e){e.printStackTrace();}

                boolean maj=fcs.getUpdate();
                oos.writeBoolean(maj);
                fcs.setUpdate(false);
                oos.flush();
                String query = (String) ois.readObject();

                if(query.equals("update")){
                    oos.writeObject(fcs.getFileContent());
                    oos.flush();
                }
            }
            ois.close();
            oos.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void keyboardEvent(int pos, char carac) {
        switch (carac){
            case 9:
                fcs.insertCharacter(pos,'\t');
                break;
            case 13 :
                fcs.insertCharacter(pos,'\n');
                break;
            case 127:
                fcs.deleteCharacter(pos,false);
                break;
            case 8 :
                fcs.deleteCharacter(pos,true);
                break;
            case 32:
                fcs.insertCharacter(pos,' ');
                break;
            default:
                if (Character.isLetterOrDigit(carac))
                    fcs.insertCharacter(pos, carac);
                break;
        }
    }
}
