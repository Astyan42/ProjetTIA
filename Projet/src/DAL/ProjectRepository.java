package DAL;

import Model_Objects.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
            project = new Project(idProjet,name,pere);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return project;
    }
}
