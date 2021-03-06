package Services;

import DAL.UserRepository;
import Model_Objects.User;

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
        return (user != null && user.pass.equals(password));
    }

    public boolean IsAlreadyRegistered(String mail){
        User user = UserRepository.getInstance().getUserByNameOrMail(mail);
        return (user != null);
    }

    public User createUser(String name, String mail, String pass){
        User user = null;
        if(!IsAlreadyRegistered(mail)){
            UserRepository.getInstance().createUser(name,mail,pass);
            user = UserRepository.getInstance().getUserByNameOrMail(mail);
        }
        return user;
    }

}
