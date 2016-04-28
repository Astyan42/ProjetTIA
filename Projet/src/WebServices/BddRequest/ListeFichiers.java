package WebServices.BddRequest;

import WebServices.Ressources.BddRessources;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// SELECTION FICHIER
// SELECT fichier.id_fichier,fichier.nom FROM fichier,droit_fichier WHERE fichier.id_fichier=droit_fichier.id_fichier AND fichier.id_projet is NULL AND droit_fichier.id_util=? AND droit_fichier.droit_lecture is TRUE
   // SELECTION DOSSIER
//SELECT projet.id_projet,projet.nom FROM acces_projet,projet WHERE acces_projet.id_projet=projet.id_projet AND acces_projet.id_util=?
public class ListeFichiers {
	
	private List<String> messages = new ArrayList<String>();
	private HashMap <Integer,String> liste_fichiers = new <Integer,String> HashMap();
	private HashMap <Integer,String> liste_projets = new <Integer,String> HashMap();
	
	public List<String> Arborescence_bdd(String mail){
	/* Chargement du driver JDBC pour MySQL */
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	} catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
	}

	int id_utilisateur=-1;
	try {
		// RECUPERATION ID UTILISATEUR VIA MAIL
	    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    preparedStatement = (PreparedStatement) connexion.prepareStatement( "SELECT id_util FROM utilisateur WHERE mail=?;" );
	    preparedStatement.setString( 1, mail );
        resultat = preparedStatement.executeQuery();  
        resultat.next();
        id_utilisateur=resultat.getInt("id_util");
        resultat = null;
    	preparedStatement=null;
	    // CREATION REQUETE PREPARE POUR RECUPERATION LISTE DES FICHIERS
	    preparedStatement = (PreparedStatement) connexion.prepareStatement( "SELECT fichier.id_fichier,fichier.nom FROM fichier,droit_fichier WHERE fichier.id_fichier=droit_fichier.id_fichier AND fichier.id_projet is NULL AND droit_fichier.id_util=? AND droit_fichier.droit_lecture is TRUE;" );
	    preparedStatement.setInt( 1, id_utilisateur );
        resultat = preparedStatement.executeQuery();  
        while (resultat.next())
        {
        	liste_fichiers.put(resultat.getInt("id_fichier"), resultat.getString("nom"));
        }
        resultat = null;
    	preparedStatement=null;
    	// CREATION REQUETE PREPARE POUR RECUPERATION LISTE DES PROJETS
        preparedStatement = (PreparedStatement) connexion.prepareStatement( "SELECT projet.id_projet,projet.nom FROM acces_projet,projet WHERE acces_projet.id_projet=projet.id_projet AND acces_projet.id_util=?;" );
        preparedStatement.setInt( 1, id_utilisateur );
        resultat = preparedStatement.executeQuery(); 
        while (resultat.next())
        {
           
           liste_projets.put(resultat.getInt("id_projet"), resultat.getString("nom"));
        }
	} catch ( SQLException e ) {
		messages.add("ERREUR lors de la connection a la bdd "+ e.getMessage());
	} finally {
		if ( resultat != null ) {
	        try {
	            /* On commence par fermer le ResultSet */
	            resultat.close();
	        } catch ( SQLException ignore ) {
	        }
	    }

		if ( preparedStatement != null ) {
		    try {
		        preparedStatement.close();
		    } catch ( SQLException ignore ) {
		    }
		}
	    if ( connexion != null )
	        try {
	            /* Fermeture de la connexion */
	            connexion.close();
	        } catch ( SQLException ignore ) {
	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
	        }
	}
	return messages;
	}

	public HashMap <Integer,String> getListe_fichiers() {
		return liste_fichiers;
	}

	public void setListe_fichiers(HashMap <Integer,String> liste_fichiers) {
		this.liste_fichiers = liste_fichiers;
	}

	public HashMap <Integer,String> getListe_projets() {
		return liste_projets;
	}

	public void setListe_projets(HashMap <Integer,String> liste_projets) {
		this.liste_projets = liste_projets;
	}

	
}
