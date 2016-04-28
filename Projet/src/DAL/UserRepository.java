package DAL;

import Model_Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class UserRepository extends DefaultRepository{
    private UserRepository(){

    }
    private static UserRepository _instance;
    public static UserRepository getInstance() {
        if(_instance == null){
            _instance = new UserRepository();
        }
        return _instance;
    }
    public User getUserByNameOrMail(String pseudo){
        Connection connection = this.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE (nom=? OR mail=? );");
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idUtil = resultSet.getInt(1);
            String mail = resultSet.getString(3);
            String nom = resultSet.getString(2);
            String password = resultSet.getString(4);
            user = new User(idUtil,mail,nom,password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;

    }
}
