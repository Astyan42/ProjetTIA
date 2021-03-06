package Services;

import DAL.*;
import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {
    private static FileService _instance;

    public static FileService getInstance() {
        if (_instance == null) {
            _instance = new FileService();
        }
        return _instance;
    }

    public Integer addFile(String pseudo, String idProjet, String name) {
        File file = FileRepository.getInstance().add_file(idProjet, name);
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        new java.io.File(System.getProperty("user.home")+"/fichiers").getAbsolutePath();
        if (file!=null){
            FileAccessRepository.getInstance().InsertAdminAccess(file, user);
            try {
                // BUG dans le cas de la création de deux fichiers avec un même nom
                new java.io.File(System.getProperty("user.home")+"/fichiers").mkdir();
                BufferedWriter writer = new BufferedWriter(new FileWriter(new java.io.File(file.path)));
                writer.write(" ");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file.id;
        }
        return 0;
    }

    public boolean removeFile(String pseudo, int fileId){
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        if(FileAccessRepository.getInstance().isAdmin(user,fileId)){
            return FileRepository.getInstance().removeFileById(fileId);
        }
        return false;
    }

    public ArrayList<String> getCollaborators(String pseudo, int fileId) {
        ArrayList<String> list = new ArrayList<String>();
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        list = FileAccessRepository.getInstance().getCollaborators(fileId, user.id);
        return list;
    }

    public String addCollaborator(String pseudo, String fileId, String select, boolean droit, boolean admin) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected= UserRepository.getInstance().getUserByNameOrMail(select);
        File file = FileRepository.getInstance().getFileById(fileId);
        if (FileAccessRepository.getInstance().isAdmin(user, file)) {
            FileAccessRepository.getInstance().InsertAccess(file, selected, droit, admin);
        }else return "vous n'etes pas admin vous ne pouvez pas ajouter de collaborateur";
        return null;
    }

    public String removeCollaborator(String pseudo, String fileId, String select) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        File file = FileRepository.getInstance().getFileById(fileId);
        if(FileAccessRepository.getInstance().isAdmin(user,file)){
            FileAccessRepository.getInstance().RemoveAccess(file, selected);
        }else return "vous n'etes pas admin vous ne pouvez pas ajouter de collaborateur";
        return null;
    }

    public String updateCollaborator(String pseudo, String fileId, String select, boolean droit, boolean admin) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        File file = FileRepository.getInstance().getFileById(fileId);
        if(FileAccessRepository.getInstance().isAdmin(user,file)){
            FileAccessRepository.getInstance().UpdateAccess(file, selected,droit,admin);
        }else return  "vous n'etes pas admin vous ne pouvez pas ajouter de collaborateur";
        return null;
    }


}
