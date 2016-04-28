package Model_Objects;

/**
 * Created by jdeveaux on 28/04/2016.
 */
public class File {
    public int id;
    public int projectId;
    public String name;
    public String path;

    public File(int id, int projectId, String name, String path) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.path = path;
    }
}
