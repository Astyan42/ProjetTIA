package Model_Objects;

public class Project {
    public String nom;
    public int id_projet;
    public int id_pere;

    public Project(int id_projet, String nom, int id_pere) {
        this.id_projet = id_projet;
        this.nom = nom;
        this.id_pere = id_pere;
    }
}
