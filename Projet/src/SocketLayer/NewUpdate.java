package SocketLayer;

import Services.FileService;
import Services.ProjectService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewUpdate implements Runnable {
    private final Socket socket;

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
                    boolean droit;
                    boolean admin;
                    switch (Insert.get(1)){
                        case "show":
                            if(type == "projet"){
                                oos.writeObject(ProjectService.getInstance().getCollaborators(pseudo, Integer.parseInt(id)));
                            }
                            if(type == "fichier"){
                                oos.writeObject(FileService.getInstance().getCollaborators(pseudo, Integer.parseInt(id)));
                            }
                            break;

                        case "add":
                            select=Insert.get(5);
                            droit = Boolean.parseBoolean(Insert.get(6));
                            admin = Boolean.parseBoolean(Insert.get(7));
                            if(type.equals("projet")){
                                ProjectService.getInstance().addCollaborator(pseudo, id, select, droit, admin);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
                            if(type.equals("fichier") ){
                                FileService.getInstance().addCollaborator(pseudo, id, select, droit, admin);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
                            break;
                        case "remove":
                            select=Insert.get(5);
                            if(type.equals("projet")){
                                ProjectService.getInstance().removeCollaborator(pseudo, id, select);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
                            if(type.equals("fichier")){
                                FileService.getInstance().removeCollaborator(pseudo, id, select);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
                            break;
                        case "modif":
                            select=Insert.get(5);
                            if(type.equals("projet")){
                                ProjectService.getInstance().updateCollaborator(pseudo, id, select);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
                            if(type.equals("fichier")){
                                FileService.getInstance().updateCollaborator(pseudo, id, select);
                                oos.writeObject("Les erreurs ne sont pas encore gérées");
                            }
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
