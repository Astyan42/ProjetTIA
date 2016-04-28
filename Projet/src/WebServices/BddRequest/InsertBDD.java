package WebServices.BddRequest;


import WebServices.Ressources.BddRessources;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertBDD {

    public ArrayList<String> addCollab(String pseudo, String type, String id, String select, String droit, String admin) {
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String>result=new ArrayList<>();
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
        }
        int idNewUtil;
        int idUtil= 0;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            preparedStatement = connexion.prepareStatement( "SELECT id_util FROM utilisateur WHERE (nom=? OR mail=? );");
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            idUtil = resultSet.getInt(1);
            preparedStatement = connexion.prepareStatement( "SELECT id_util FROM utilisateur WHERE (nom=? OR mail=? );");
            preparedStatement.setString(1, select);
            preparedStatement.setString(2, select);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            idNewUtil = resultSet.getInt(1);
            else {
                messages.add("L'utilisateur n'existe pas");
                return messages;
            }
            int testDroit;
            switch (type){
                case "projet":

                    preparedStatement = connexion.prepareStatement("SELECT admin from acces_projet where id_util=? And id_projet=?");
                    preparedStatement.setString(1, String.valueOf(idUtil));
                    preparedStatement.setString(2, id);

                    preparedStatement.executeQuery();

                    if(resultSet.next())
                        testDroit = resultSet.getInt(1);
                    else {
                        messages.add("erreur l'utilisateur selectionner ne possede aucun droit");
                        return messages;
                    }

                    if(testDroit==0) {
                        messages.add("Vous ne possédez pas les droit necessaire");
                    }
                    else{
                        preparedStatement = connexion.prepareStatement("INSERT INTO acces_projet (id_projet,id_util,admin,lecture) VALUES (?,?,?,?);");
                        preparedStatement.setString(1, id);
                        preparedStatement.setString(2, String.valueOf(idNewUtil));
                        preparedStatement.setString(3, admin);
                        preparedStatement.setString(4, droit);

                        preparedStatement.executeUpdate();
                    }



                    break;
                case "fichier":
                    preparedStatement = connexion.prepareStatement("SELECT administrateur from droit_fichier where id_util=? And id_fichier=?");
                    preparedStatement.setString(1, String.valueOf(idUtil));
                    preparedStatement.setString(2, id);

                    preparedStatement.executeQuery();

                    if(resultSet.next())
                        testDroit = resultSet.getInt(1);
                    else {
                        messages.add("erreur l'utilisateur selectionner ne possede aucun droit");
                        return messages;
                    }

                    if(testDroit==0) {
                        messages.add("Vous ne possédez pas les droit necessaire");
                    }
                    else {
                        preparedStatement = connexion.prepareStatement("INSERT INTO droit_fichier (id_util,id_fichier,droit_lecture,droit_ecriture,administrateur) VALUES (?,?,1,?,?);");
                        preparedStatement.setString(1, String.valueOf(idNewUtil));
                        preparedStatement.setString(2, id);
                        preparedStatement.setString(3, droit);
                        preparedStatement.setString(4, admin);

                        preparedStatement.executeUpdate();
                        break;
                    }
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
        System.out.println(messages);
        return messages;
    }

    public Object remCollab(String pseudo, String type, String id, String select) {
        return null;
    }

    public Object modCollab(String pseudo, String type, String id, String select) {
        return null;
    }
}
