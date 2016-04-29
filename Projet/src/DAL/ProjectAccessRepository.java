package DAL;

import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class ProjectAccessRepository extends DefaultRepository{
    private ProjectAccessRepository(){
        
    }
    private static ProjectAccessRepository _instance;
    public static ProjectAccessRepository getInstance() {
        if(_instance == null){
            _instance = new ProjectAccessRepository();
        }
        return _instance;
    }

    public void InsertAdminAccess(Project p, User u){
        Connection connection = this.getConnection();
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO acces_projet (id_projet,id_util,admin,lecture)VALUES (?,?,TRUE ,TRUE );");
            preparedStatement.setString(1, String.valueOf(p.id_projet));
            preparedStatement.setString(2, String.valueOf(u.id));
            int resultat = preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void InsertAccess(Project p, User u, boolean read, boolean isAdmin){
        Connection connection = this.getConnection();
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement( "INSERT INTO acces_projet (id_projet,id_util,admin,lecture) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, p.id_projet);
            preparedStatement.setInt(2, u.id);
            preparedStatement.setBoolean(3, isAdmin);
            preparedStatement.setBoolean(4, read);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCollaborators(int projectId, int userId) {
        Connection connection = this.getConnection();
        User user = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT utilisateur.nom " +
                    "from utilisateur " +
                    "Left outer join acces_projet ON utilisateur.id_util=acces_projet.id_util " +
                    "where id_projet=? AND utilisateur.id_util<>?;");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setString(2, String.valueOf(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean isAdmin(User u, Project p){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("SELECT admin from acces_projet where id_util=? And id_projet=?");
            preparedStatement.setInt(1, u.id);
            preparedStatement.setInt(2, p.id_projet);
            int testDroit;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testDroit = resultSet.getInt(1);
                if (testDroit != 0) {
                    return true;
                }
            }
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void RemoveAccess(Project project, User selected) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("DELETE FROM acces_projet WHERE id_projet=? AND id_util=?");
            preparedStatement.setInt(1, project.id_projet);
            preparedStatement.setInt(2, selected.id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateAcess(Project project, User selected, boolean droit, boolean admin) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("UPDATE acces_projet SET lecture=? , admin=?  WHERE id_projet=? AND id_util=?");
            preparedStatement.setBoolean(1, droit);
            preparedStatement.setBoolean(2, admin);
            preparedStatement.setInt(3, project.id_projet);
            preparedStatement.setInt(4, selected.id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
