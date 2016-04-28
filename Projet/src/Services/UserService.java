package Services;

import DAL.UserRepository;
import Model_Objects.User;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class UserService {
    private static UserService _instance;

    public static UserService getInstance() {
        if(_instance == null){
            _instance = new UserService();
        }
        return _instance;
    }

    public boolean Authentication(String mail, String password){
        User user = UserRepository.getInstance().getUserByNameOrMail(mail);
        return (user.pass == password);
    }

    public boolean IsAlreadyRegistered(String mail){
        User user = UserRepository.getInstance().getUserByNameOrMail(mail);
        return (user == null);
    }

}
