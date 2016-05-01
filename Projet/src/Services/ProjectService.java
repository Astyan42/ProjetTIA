package Services;

import DAL.*;
import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.util.ArrayList;

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

    public void addCollaborator(String pseudo, String projectId, String select, boolean droit, boolean admin){
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        Project project = ProjectRepository.getInstance().getProjectById(projectId);
        if(ProjectAccessRepository.getInstance().isAdmin(user,project)){
            ProjectAccessRepository.getInstance().InsertAccess(project,selected,droit,admin);
        }
    }


    public void removeCollaborator(String pseudo, String id, String select) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        Project project = ProjectRepository.getInstance().getProjectById(id);
        if(ProjectAccessRepository.getInstance().isAdmin(user,project)){
            ProjectAccessRepository.getInstance().RemoveAccess(project,selected);
        }
    }

    public void updateCollaborator(String pseudo, String id, String select, boolean droit, boolean admin) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        User selected = UserRepository.getInstance().getUserByNameOrMail(select);
        Project project = ProjectRepository.getInstance().getProjectById(id);
        if(ProjectAccessRepository.getInstance().isAdmin(user,project)){
            ProjectAccessRepository.getInstance().UpdateAcess(project,selected,droit,admin);
        }

    }

    public boolean removeProject(String pseudo, int fileId) {
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        Project project = ProjectRepository.getInstance().getProjectById(String.valueOf(fileId));
        if(ProjectAccessRepository.getInstance().isAdmin(user,project)){
            return ProjectRepository.getInstance().removeProjectById(fileId);
        }
        return false;
    }
}
