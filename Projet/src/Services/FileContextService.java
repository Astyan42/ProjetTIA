package Services;

import DAL.FileRepository;
import Model_Objects.File;
import Model_Objects.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jdeveaux on 29/04/2016.
 */
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
    private final int MODIFICATION_COUNT_BETWEEN_SAVES=5;
    private int modificationsUntilNextUpdate = MODIFICATION_COUNT_BETWEEN_SAVES;
    // fileContent must be persisted every x modifications
    private java.io.File fileIO;
    private StringBuffer fileContent;

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

    public synchronized void insertCharacter(int position, char c){
        fileContent.insert(position,c);
        modificationsUntilNextUpdate --;
        if(modificationsUntilNextUpdate < 0){
            try {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(fileIO));
                bwr.write(fileContent.toString());
                bwr.flush();
                bwr.close();
                modificationsUntilNextUpdate = MODIFICATION_COUNT_BETWEEN_SAVES;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void deleteCharacter(int position){
        fileContent.deleteCharAt(position);
        if(modificationsUntilNextUpdate < 0){
            try {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(fileIO));
                bwr.write(fileContent.toString());
                bwr.flush();
                bwr.close();
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

