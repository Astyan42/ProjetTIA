package WebServices.Threads;

import Services.FileService;
import Services.ProjectService;
import WebServices.BddRequest.InsertBDD;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewUpdate implements Runnable {
    private final Socket socket;
    public static InsertBDD insertBDD = new InsertBDD();

    public NewUpdate(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> Insert = (ArrayList<String>) ois.readObject();
            String pseudo;
            String idProjet;
            String name;
            String id;
            String select;
            String type;
            System.out.println(Insert);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            switch (Insert.get(0)){
                case "projet":
                    pseudo = Insert.get(1);
                    idProjet = Insert.get(2);
                    name=Insert.get(3);
                    oos.writeObject(ProjectService.getInstance().add_project(pseudo, name, idProjet));
                    break;
                case "fichier":
                    pseudo = Insert.get(1);
                    idProjet = Insert.get(2);
                    name=Insert.get(3);
                    oos.writeObject(FileService.getInstance().addFile(pseudo,idProjet,name));
                    break;
                case "collab":
                    // collab,commande,pseudo,type,id,select,droit,admin
                    pseudo = Insert.get(2);
                    type = Insert.get(3);
                    id= Insert.get(4);
                    String droit;
                    String admin;
                    switch (Insert.get(1)){
                        case "show":
                            if(type == "projet"){
                                ProjectService.getInstance().getCollaborators(pseudo, Integer.parseInt(id));
                            }
                            if(type == "fichier"){
                                FileService.getInstance().getCollaborators(pseudo, Integer.parseInt(id));
                            }
                            oos.writeObject(i);
                            System.out.println(i);
                            break;

                        case "add":

                            select=Insert.get(5);
                            droit = Insert.get(6);
                            admin =Insert.get(7);
                            oos.writeObject(insertBDD.addCollab(pseudo,type,id,select,droit,admin));
                            break;
                        case "remove":
                            select=Insert.get(5);
                            oos.writeObject(insertBDD.remCollab(pseudo,type,id,select));
                            break;
                        case "modif":
                            droit = Insert.get(6);
                            admin =Insert.get(7);
                            select=Insert.get(5);
                            oos.writeObject(insertBDD.modCollab(pseudo,type,id,select));
                            break;
                    }
                    break;
            }

            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
