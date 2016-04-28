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
        if(_instance == null){
            _instance = new FileService();
        }
        return _instance;
    }
    public File addFile(String pseudo, String idProjet ,String name) {
        File file = FileRepository.getInstance().add_file(idProjet, name);
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        FileAccessRepository.getInstance().InsertAdminAccess(file, user);
        return file;
    }

    public ArrayList<String> getCollaborators(String pseudo, int fileId){
        ArrayList<String> list = new ArrayList<String>();
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        FileAccessRepository.getInstance().getCollaborators(fileId,user.id);
        return list;
    }
}
