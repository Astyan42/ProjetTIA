package Services;

import DAL.*;
import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.util.ArrayList;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class FileService {

    private static FileService _instance;

    public static FileService getInstance() {
        if (_instance == null) {
            _instance = new FileService();
        }
        return _instance;
    }

    public String addFile(String pseudo, String idProjet, String name) {
        File file = FileRepository.getInstance().add_file(idProjet, name);
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        if (file!=null){
            FileAccessRepository.getInstance().InsertAdminAccess(file, user);
            return file.name;
        }
        return null;
    }

    public ArrayList<String> getCollaborators(String pseudo, int fileId) {
        ArrayList<String> list = new ArrayList<String>();
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        FileAccessRepository.getInstance().getCollaborators(fileId, user.id);
        return list;
    }

    public void addCollaborator(String pseudo, String fileId, String select, boolean droit, boolean admin) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        File file = FileRepository.getInstance().getFileById(fileId);
        if (FileAccessRepository.getInstance().isAdmin(user, file)) {
            FileAccessRepository.getInstance().InsertAccess(file, user, droit, admin);
        }
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

    public void updateCollaborator(String pseudo, String fileId, String select) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        File file = FileRepository.getInstance().getFileById(fileId);
        if(FileAccessRepository.getInstance().isAdmin(user,file)){
            FileAccessRepository.getInstance().UpdateAccess(file, selected);
        }
    }

}
