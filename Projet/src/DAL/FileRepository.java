package DAL;

import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.sql.*;
import java.util.ArrayList;

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
            preparedStatement.setString(1, projectId);

            preparedStatement.setString(2, fileName);
            String path =  "fichiers\\"+fileName;
            preparedStatement.setString(3, path);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int idFic = rs.getInt(1);
            file = new File(idFic,Integer.parseInt(projectId),fileName,path);
        }
        catch(Exception e ){
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public File getFileById(String fileId) {
            Connection connection = this.getConnection();
            File file = null;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fichier WHERE id_fichier=?;");
                preparedStatement.setString(1, fileId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int id = resultSet.getInt(1);
                int projectId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String filePath = resultSet.getString(4);
                file = new File(id,projectId,name,filePath);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return file;

    }
    public File getFileByName(String fileName) {
        Connection connection = this.getConnection();
        File file = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fichier WHERE nom=?;");
            preparedStatement.setString(1, fileName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            int projectId = resultSet.getInt(2);
            String name = resultSet.getString(3);
            String filePath = resultSet.getString(4);
            file = new File(id,projectId,name,filePath);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return file;
    }
    public ArrayList<File> getFilesRootForUser(int userId){
        Connection connection = this.getConnection();
        ArrayList<File> arraylist=new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT fichier.id_fichier,fichier.id_projet,fichier.nom,fichier.chemin " +
                    "FROM fichier, droit_fichier " +
                    "WHERE id_util=? AND fichier.id_fichier = droit_fichier.id_fichier;");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();


            ArrayList<String> resultArray;
            while (resultSet.next()){
                int idFic=resultSet.getInt(1);
                int idProject=resultSet.getInt(2);
                String name=resultSet.getString(3);
                String chemin=resultSet.getString(4);
                int exist=0;
                for (Project project : ProjectRepository.getInstance().getProjectsForUser(userId)){
                    if(project.id_projet==idProject) exist=1;
                }
                if (exist==0&&idProject!=0) {
                    File file= new File(idFic,idProject,name,chemin);
                    arraylist.add(file);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arraylist;
    }
    public ArrayList<File> getFilesProjectForUser(int userId, int projectId) {
        Connection connection = this.getConnection();
        ArrayList<File> arraylist=new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT fichier.id_fichier,fichier.id_projet,fichier.nom,fichier.chemin " +
                    "FROM fichier, droit_fichier " +
                    "WHERE id_util=? AND id_projet=? AND fichier.id_fichier = droit_fichier.id_fichier;");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();


            ArrayList<String> resultArray;
            while (resultSet.next()){
                int idFic=resultSet.getInt(1);
                int idProject=resultSet.getInt(2);
                String name=resultSet.getString(3);
                String chemin=resultSet.getString(4);

                File file= new File(idFic,idProject,name,chemin);
                arraylist.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arraylist;
    }

}
