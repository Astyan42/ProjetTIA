package Services;

import DAL.*;
import Model_Objects.File;
import Model_Objects.Project;
import Model_Objects.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArborescenceService {
    private static ArborescenceService _instance;

    public static ArborescenceService getInstance() {
        if (_instance == null) {
            _instance = new ArborescenceService();
        }
        return _instance;
    }

    public HashMap<Integer,String> getChildFiles(String mail, String name){
        HashMap<Integer,String> result = new HashMap<>();
        User user=UserRepository.getInstance().getUserByNameOrMail(mail);
        Project father = ProjectRepository.getInstance().getProjectByName(name);
        for(File file:FileRepository.getInstance().getFilesById(father.id_projet)){
            if (FileAccessRepository.getInstance().isAccess(user,file.id))
                result.put(file.id,file.name);
        }
        return result;
    }

    public HashMap<Integer,String> getChildProject(String mail,String name){
        HashMap<Integer,String> result = new HashMap<Integer,String>();
        User user=UserRepository.getInstance().getUserByNameOrMail(mail);
        Project father = ProjectRepository.getInstance().getProjectByName(name);
        for(Project project : ProjectRepository.getInstance().getProjectsById(father.id_projet)){
            if (ProjectAccessRepository.getInstance().isAccess(user,project.id_projet))
                result.put(project.id_projet,project.nom);
        }
        return result;
    }

    public String getArboXml(String pseudo){
        User user = UserRepository.getInstance().getUserByNameOrMail(pseudo);
        ArrayList<File> files = FileRepository.getInstance().getFilesProjectForUser(user.id, 0);
        ArrayList<File> filesRoot = FileRepository.getInstance().getFilesRootForUser(user.id);
        ArrayList<Project> projects = ProjectRepository.getInstance().getProjectsForUser(user.id);
        ArrayList<Project> projectsRoot = ProjectRepository.getInstance().getProjectsRootForUser(user.id);
        String file = "";

        file+="<?xml version=\"1.0\"?>\n" +
                //"<!DOCTYPE arborescence SYSTEM \"dtd_arborescence.dtd\">\n" +
                "<arborescence>\n" +
                "<projet idp=\"0\" name=\"/\">\n";

        System.out.println(files);
        file = XmlWriteProjectRoot(file,projectsRoot,0,user.id);
        file = XmlWriteProject(file,projects,0,user.id);
        for (File rowsFiles : filesRoot){
            file+="\t<fichier idf=\""+rowsFiles.id+"\" name=\""+rowsFiles.name+"\"/>\n ";
        }
        for (File rowsFiles : files){
            file+="\t<fichier idf=\""+rowsFiles.id+"\" name=\""+rowsFiles.name+"\"/>\n ";
        }

        file+="</projet>\n" +
                "</arborescence>";
        System.out.println(file);
        return file;
    }
    private String XmlWriteProjectRoot(String file, ArrayList<Project> table, int idPere, int user) {
        return XmlWriteProjectRootRec(file,table,idPere,1,user);
    }
    private String XmlWriteProjectRootRec(String file, ArrayList<Project> table, int idPere, int indent, int user) {
        ArrayList<Project>tablePere = new ArrayList<>();
        String indentS=repeat(indent,"\t");
        for (Project rows: table){
            file+=indentS+"<projet idp=\""+rows.id_projet+"\" name=\""+rows.nom+"\">\n";
            // sous Dossier
            file = XmlWriteProjectRec(file,table,rows.id_projet,indent+1, user);
            // Fichier
            ArrayList<File> files = FileRepository.getInstance().getFilesProjectForUser(user,rows.id_projet);
            for (File rowsFiles : files ){
                file+=indentS+"\t<fichier idf=\""+rowsFiles.id+"\" name=\""+rowsFiles.name+"\"/>\n ";
            }
            file+=(indentS+"</projet>\n");
        }
        return file;
    }
    private String XmlWriteProject(String file, ArrayList<Project> table, int idPere, int user) {
        return XmlWriteProjectRec(file,table,idPere,1,user);
    }
    private String repeat(int count , String s) {
        String result ="";
        for (int i = 0; i < count   ; i++) {
            result+=s;
        }
        return result;
    }
    public String XmlWriteProjectRec(String file, ArrayList<Project> table, int idPere, int indent, int user){
        ArrayList<Project>tablePere = new ArrayList<>();
        String indentS=repeat(indent,"\t");
        for (Project rows: table){
            if(rows.id_pere==idPere){
                file+=indentS+"<projet idp=\""+rows.id_projet+"\" name=\""+rows.nom+"\">\n";
                // sous Dossier
                file = XmlWriteProjectRec(file,table,rows.id_projet,indent+1, user);
                // Fichier
                ArrayList<File> files = FileRepository.getInstance().getFilesProjectForUser(user,rows.id_projet);
                for (File rowsFiles : files ){
                    file+=indentS+"\t<fichier idf=\""+rowsFiles.id+"\" name=\""+rowsFiles.name+"\"/>\n ";
                }
                file+=(indentS+"</projet>\n");
            }
        }
        return file;

    }

}
