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
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement( "INSERT INTO droit_fichier (id_util,id_fichier,droit_lecture,droit_ecriture,administrateur)VALUES (?,?,TRUE,TRUE,TRUE);");
            preparedStatement.setString(2, String.valueOf(u.id));
            preparedStatement.setString(1, String.valueOf(f.id));
            int resultat = preparedStatement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCollaborators(int fileId, int userId) {
        Connection connection = this.getConnection();
        User user = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT utilisateur.nom " +
                    "from utilisateur " +
                    "Left outer join acces_projet ON utilisateur.id_util=acces_projet.id_util " +
                    "where id_projet=? AND utilisateur.id_util<>?;");
            preparedStatement.setInt(1, fileId);
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
}
