package Services;

import DAL.ProjectAccessRepository;
import DAL.ProjectRepository;
import DAL.UserRepository;
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
    public Project add_project(String pseudo, String name,String pere) {
        Project project = ProjectRepository.getInstance().add_project(name,pere);
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        ProjectAccessRepository.getInstance().InsertAdminAccess(project,user);
        return project;
    }

    public ArrayList<String> getCollaborators(String pseudo, int projectId){
        ArrayList<String> list = new ArrayList<String>();
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        list = ProjectAccessRepository.getInstance().getCollaborators(projectId,user.id);
        return list;
    }
}
