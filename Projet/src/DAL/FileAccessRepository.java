package DAL;

import Model_Objects.File;
import Model_Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class FileAccessRepository extends DefaultRepository{
    private FileAccessRepository(){
        
    }
    private static FileAccessRepository _instance;
    public static FileAccessRepository getInstance() {
        if(_instance == null){
            _instance = new FileAccessRepository();
        }
        return _instance;
    }

    public void InsertAdminAccess(File f, User u){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO droit_fichier (id_util,id_fichier,droit_lecture,droit_ecriture,administrateur) VALUES (?,?,TRUE,TRUE,TRUE);");
            preparedStatement.setInt(1, u.id);
            preparedStatement.setInt(2, f.id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void InsertAccess(File f, User u, boolean write, boolean isAdmin){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO droit_fichier (id_util,id_fichier,droit_lecture,droit_ecriture,administrateur)VALUES (?,?,TRUE,?,?);");
            preparedStatement.setInt(1, u.id);
            preparedStatement.setInt(2, f.id);
            preparedStatement.setBoolean(3, write);
            preparedStatement.setBoolean(4, isAdmin);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCollaborators(int fileId, int userId) {
        Connection connection = this.getConnection();
        PreparedStatement preparedStatement;
        ArrayList<String> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT utilisateur.nom " +
                    "from utilisateur " +
                    "Left outer join droit_fichier ON utilisateur.id_util=droit_fichier.id_util " +
                    "where id_fichier=? AND utilisateur.id_util<>?;");
            preparedStatement.setInt(1, fileId);
            preparedStatement.setInt(2, userId);
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

    public boolean isAdmin(User u, File f){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("SELECT administrateur from droit_fichier where id_util=? And id_fichier=?");
            preparedStatement.setString(1, String.valueOf(u.id));
            preparedStatement.setString(2, String.valueOf(f.id));
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

    public void RemoveAccess(File file, User selected) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("DELETE FROM droit_fichier WHERE id_fichier=? AND id_util=?");
            preparedStatement.setInt(1, file.id);
            preparedStatement.setInt(2, selected.id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateAccess(File file, User selected, boolean droit, boolean admin) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = this.getConnection().prepareStatement("UPDATE droit_fichier SET droit_ecriture=? , administrateur=?  WHERE id_fichier=? AND id_util=?");
            preparedStatement.setBoolean(1, droit);
            preparedStatement.setBoolean(2, admin);
            preparedStatement.setInt(3, file.id);
            preparedStatement.setInt(4, selected.id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
