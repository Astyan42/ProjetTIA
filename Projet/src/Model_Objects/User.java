package Model_Objects;

public class User {
    public int id;
    public String mail;
    public String nom;
    public String pass;

    public User(int id, String mail, String nom, String pass) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.pass = pass;
    }
}
