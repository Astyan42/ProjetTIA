package Model_Objects;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class Project {
    public String nom;
    public int id_projet;
    public String id_pere;

    public Project(int id_projet, String nom, String id_pere) {
        this.id_projet = id_projet;
        this.nom = nom;
        this.id_pere = id_pere;
    }
}
