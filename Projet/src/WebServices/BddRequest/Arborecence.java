package WebServices.BddRequest;

import WebServices.Ressources.BddRessources;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Arborecence extends BddRessources {
    public String getArbo(String pseudo){
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
        }
        String file = "";

        file+="<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE arborescence SYSTEM \"dtd_arborescence.dtd\">\n" +
                "<arborescence>\n" +
                "<projet idp=\"NULL\" name=\"/\">\n";

        ArrayList<ArrayList<String>> table = getFolders(pseudo);
        ArrayList<ArrayList<String>> files = getFiles(pseudo, "null");
        System.out.println(files);
        for (ArrayList<String> rowsFiles : files ){
            file+="\t<fichier idf=\""+rowsFiles.get(0)+"\" name=\""+rowsFiles.get(1)+"\"/>\n ";
        }
        file = XmlWriteProject(file,table,"NULL",pseudo);


        file+="</projet>\n" +
                "</arborescence>";
        System.out.println(file);
        return file;
    }

    private String XmlWriteProject(String file, ArrayList<ArrayList<String>> table, String idPere, String pseudo) {
        return XmlWriteProjectRec(file,table,"0",1,pseudo);
    }
    private String repeat(int count , String s) {
        String result ="";
        for (int i = 0; i < count   ; i++) {
            result+=s;
        }
        return result;
    }
    public String XmlWriteProjectRec(String file, ArrayList<ArrayList<String>> table, String idPere, int indent, String pseudo){
        ArrayList<ArrayList<String>>tablePere = new ArrayList<>();
        String indentS=repeat(indent,"\t");
        for (ArrayList<String> rows: table){
            if(rows.get(2).equals(idPere)){
                    file+=indentS+"<projet idp=\""+rows.get(0)+"\" name=\""+rows.get(1)+"\">\n";
                    //sous Dossier
                    file = XmlWriteProjectRec(file,table,rows.get(0),indent+1, pseudo);
                    // Fichier
                    ArrayList<ArrayList<String>> files = getFiles(pseudo,rows.get(0));
                    for (ArrayList<String> rowsFiles : files ){
                        file+=indentS+"\t<fichier idf=\""+rowsFiles.get(0)+"\" name=\""+rowsFiles.get(1)+"\"/>\n ";
                    }
                    file+=(indentS+"</projet>\n");
                }
        }
        return file;

    }

    public ArrayList<ArrayList<String>> getFiles(String pseudo, String idProjet){
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<ArrayList<String>> files = new ArrayList<>();
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
        }
        int idUtil= 0;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            preparedStatement = connexion.prepareStatement( "SELECT id_util FROM utilisateur WHERE (nom=? OR mail=? );");
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            idUtil = resultSet.getInt(1);

            preparedStatement = connexion.prepareStatement( "SELECT fichier.id_fichier,fichier.nom " +
                    "FROM fichier, droit_fichier " +
                    "WHERE id_util=? AND id_projet=? AND fichier.id_fichier = droit_fichier.id_fichier;");
            preparedStatement.setString(1, String.valueOf(idUtil));
            preparedStatement.setString(2, idProjet);
            resultSet = preparedStatement.executeQuery();

            String idFic;
            String name;
            ArrayList<String> resultArray;
            while (resultSet.next()){
                resultArray=new ArrayList<>();
                idFic = resultSet.getString(1);
                name = resultSet.getString(2);
                resultArray.add(idFic);
                resultArray.add(name);
                files.add(resultArray);
            }
        } catch ( SQLException e ) {
            messages.add("ERREUR"+ e.getMessage());
        } finally {

            if ( preparedStatement != null ) {
                try {
                    preparedStatement.close();
                } catch ( SQLException ignore ) {}
            }
            if ( connexion != null )
                try {
	            /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }
        return files;
    }
    public ArrayList<ArrayList<String>> getFolders(String pseudo){
        ArrayList<String> messages = new ArrayList<>();
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
        }
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        int idUtil= 0;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            preparedStatement = connexion.prepareStatement( "SELECT id_util FROM utilisateur WHERE (nom=? OR mail=? );");
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            idUtil = resultSet.getInt(1);

            preparedStatement = connexion.prepareStatement( "SELECT projet.id_projet,projet.nom,projet.id_pere " +
                    "FROM projet , acces_projet " +
                    "WHERE id_util=? AND projet.id_projet = acces_projet.id_projet");
            preparedStatement.setString(1, String.valueOf(idUtil));
            resultSet = preparedStatement.executeQuery();

            int idProj=0;
            String name;
            int idPere=0;
            ArrayList<String> resultArray;
            while (resultSet.next()){
                resultArray=new ArrayList<>();
                idProj = resultSet.getInt(1);
                name = resultSet.getString(2);
                idPere = resultSet.getInt(3);
                resultArray.add(String.valueOf(idProj));
                resultArray.add(name);
                resultArray.add(String.valueOf(idPere));
                table.add(resultArray);
            }



        } catch ( SQLException e ) {
            messages.add("ERREUR"+ e.getMessage());
        } finally {

            if ( preparedStatement != null ) {
                try {
                    preparedStatement.close();
                } catch ( SQLException ignore ) {}
            }
            if ( connexion != null )
                try {
	            /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }
        return table;
    }
    
}
