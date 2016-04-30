package Services;

import DAL.FileRepository;
import Model_Objects.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FileContextService {
    private static final ConcurrentMap<String, FileContextService> multitons = new ConcurrentHashMap<>();

    private FileContextService(String fileId) {
        this.fileModel = FileRepository.getInstance().getFileById(fileId);
        this.fileIO = new java.io.File(this.fileModel.path);
        String temp = "";
        try {
            temp = this.readFile(this.fileIO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileContent = new StringBuffer(temp);
    }

    public static FileContextService getInstance(final String fileId) {
        return multitons.computeIfAbsent(fileId, FileContextService::new);
    }

    // The instance file
    private final File fileModel;
    private final int MODIFICATION_COUNT_BETWEEN_SAVES=10;
    private int modificationsUntilNextUpdate = MODIFICATION_COUNT_BETWEEN_SAVES;
    // fileContent must be persisted every x modifications
    private java.io.File fileIO;
    private StringBuffer fileContent;
    private Boolean update=false;

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    private String readFile(java.io.File file) throws IOException {
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");
        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public synchronized void insertCharacter(int position, char c) throws ArrayIndexOutOfBoundsException{
        fileContent.insert(position,c);
        modificationsUntilNextUpdate --;
        persist();
    }

    public synchronized void deleteCharacter(int position,boolean left) throws StringIndexOutOfBoundsException{
        if (left)if (position>1) position-=1;
        if (!fileContent.toString().equals("")||!(left && position==0) || !(position==fileContent.toString().length())){
            fileContent.deleteCharAt(position);
            modificationsUntilNextUpdate --;
            persist();
        }
    }

    private void persist() {
        if(modificationsUntilNextUpdate < 0){
            try {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(fileIO));
                bwr.write(fileContent.toString());
                bwr.flush();
                bwr.close();
                update = true ;
                modificationsUntilNextUpdate = MODIFICATION_COUNT_BETWEEN_SAVES;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized String getFileContent(){
        return fileContent.toString();
    }
}

