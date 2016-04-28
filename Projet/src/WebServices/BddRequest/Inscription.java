package WebServices.BddRequest;

import WebServices.Ressources.BddRessources;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class Inscription extends BddRessources {
	private int resultat;
	private List<String> messages = new ArrayList<String>();
	
	public List<String> inscription(HttpServletRequest request){
	/* Chargement du driver JDBC pour MySQL */
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	} catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
	}

	int resultat = 0;
	try {
	    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    /* Cr�ation de l'objet g�rant la requ�te pr�par�e d�finie */
	    preparedStatement = connexion.prepareStatement( "INSERT INTO utilisateur (nom,mail,password) VALUES(?,?,?);" );
	    String paramNom = request.getParameter( "inscription_nom" );
	    String paramEmail = request.getParameter( "inscription_mail" );
	    String paramMotDePasse = request.getParameter( "inscription_password" );	  
	    
	    preparedStatement.setString( 1, paramNom ); 
	    preparedStatement.setString( 2, paramEmail );
	    preparedStatement.setString( 3, paramMotDePasse );

        resultat = preparedStatement.executeUpdate();
        this.setResultat(resultat);
	} catch ( SQLException e ) {
		messages.add("ERREUR lors de la connection a la bdd "+ e.getMessage());
	} finally {
		
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

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

	
}
