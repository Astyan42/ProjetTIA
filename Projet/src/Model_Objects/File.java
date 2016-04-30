package Model_Objects;

import java.util.ArrayList;

public class File {
    public int id;
    public int projectId;
    public String name;
    public String path;
    private ArrayList<Integer> usedIndexes;

    public File(int id, int projectId, String name, String path) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.path = path;
    }


}
