package DAL;

import Model_Objects.Project;
import Model_Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class ProjectRepository extends DefaultRepository{

    private ProjectRepository() {
    }
    private static ProjectRepository _instance;
    public static ProjectRepository getInstance() {
        if(_instance == null){
            _instance = new ProjectRepository();
        }
        return _instance;
    }

    public Project add_project(String name,String pere){
        Connection connection = this.getConnection();
        PreparedStatement preparedStatement = null;
        Project project = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO projet (id_projet,nom,id_pere) VALUES(NULL ,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1, name );
            if (pere.equals("NULL"))preparedStatement.setString( 2, "0" );
            else preparedStatement.setString( 2, pere );
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int idProjet = rs.getInt(1);
            project = new Project(idProjet,name,Integer.parseInt(pere));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return project;
    }

    public Project getProjectById(String projectId) {
        Connection connection = this.getConnection();
        Project file = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projet WHERE id_projet=?;");
            preparedStatement.setString(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String nom = resultSet.getString(2);
            int id_pere = resultSet.getInt(3);
            file = new Project(id,nom,id_pere);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return file;
    }

    public ArrayList<Project> getProjectsForUser(int userId){
        Connection connection = this.getConnection();
        ArrayList<Project> arrayList= new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_projet,nom,id_pere FROM acces_projet LEFT OUTER JOIN projet WHERE acces_projet.id_projet=projet.id_projet AND id_util=?;");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                int id_pere = resultSet.getInt(3);
                Project project = new Project(id,nom,id_pere);
                arrayList.add(project);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arrayList;

    }

}
