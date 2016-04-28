package Services;

import DAL.*;
import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.util.ArrayList;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class ProjectService {

    private static ProjectService _instance;

    public static ProjectService getInstance() {
        if(_instance == null){
            _instance = new ProjectService();
        }
        return _instance;
    }
    public String add_project(String pseudo, String name,int pere) {
        Project project = ProjectRepository.getInstance().add_project(name,pere);
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        if(project!=null){
            ProjectAccessRepository.getInstance().InsertAdminAccess(project,user);
            return project.nom;
        }
        return null;
    }

    public ArrayList<String> getCollaborators(String pseudo, int projectId){
        ArrayList<String> list = new ArrayList<String>();
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        list = ProjectAccessRepository.getInstance().getCollaborators(projectId,user.id);
        return list;
    }

    public String addCollaborator(String pseudo, String projectId, String select, boolean droit, boolean admin){
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        Project project = ProjectRepository.getInstance().getProjectById(projectId);
        if(ProjectAccessRepository.getInstance().isAdmin(user,project)){
            ProjectAccessRepository.getInstance().InsertAccess(project,user,droit,admin);
        }else return "vous n'etes pas admin vous ne pouvez pas ajouter de collaborateur";
        return null;
    }


    public void removeCollaborator(String pseudo, String id, String select) {
    }

    public void updateCollaborator(String pseudo, String id, String select) {

    }
}
