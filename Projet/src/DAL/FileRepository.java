package DAL;

import Model_Objects.File;
import Model_Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class FileRepository extends DefaultRepository{

    private FileRepository(){

    }
    private static FileRepository _instance;
    public static FileRepository getInstance() {
        if(_instance == null){
            _instance = new FileRepository();
        }
        return _instance;
    }

    public File add_file(String projectId, String fileName){
        Connection connection = this.getConnection();
        File file = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO fichier (id_fichier,id_projet,nom,chemin) VALUES (NULL,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            if (projectId.equals("NULL")) preparedStatement.setString(1, "0");
            else preparedStatement.setString(1, projectId);

            preparedStatement.setString(2, fileName);
            String path =  "./fichiers/" + fileName;
            preparedStatement.setString(3, path);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int idFic = rs.getInt(1);
            file = new File(idFic,Integer.parseInt(projectId),fileName,path);
        }
        catch(Exception e ){
            e.printStackTrace();
        }
        return file;
    }
}
