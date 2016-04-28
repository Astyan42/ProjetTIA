package WebServices.BddRequest;

import WebServices.Ressources.BddRessources;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Connect extends BddRessources {
	private int nombre_resultat;
	private List<String> messages = new ArrayList<String>();
	
	public List<String> existance_compte(HttpServletRequest request){
	/* Chargement du driver JDBC pour MySQL */
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	} catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
	}

	int compteur = 0;
	try {
	    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    /* Cr�ation de l'objet g�rant la requ�te pr�par�e d�finie */
	    preparedStatement = connexion.prepareStatement( "SELECT * FROM utilisateur WHERE password=? AND mail=?;" );
	    String paramEmail = request.getParameter( "connexion_mail" );
	    String paramMotDePasse = request.getParameter( "connexion_password" );
	    preparedStatement.setString( 1, paramMotDePasse );
	    preparedStatement.setString( 2, paramEmail );
        resultat = preparedStatement.executeQuery();  
        while (resultat.next())
        {
           compteur++;
        }
        this.setNombre_resultat(compteur);
        
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

	public int getNombre_resultat() {
		return nombre_resultat;
	}

	public void setNombre_resultat(int nombre_resultat) {
		this.nombre_resultat = nombre_resultat;
	}

	public boolean connectionValidate(String mail, String password){
	/* Chargement du driver JDBC pour MySQL */
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
	    /* G�rer les �ventuelles erreurs ici. */
		}

		int compteur = 0;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    /* Cr�ation de l'objet g�rant la requ�te pr�par�e d�finie */
            preparedStatement = connexion.prepareStatement( "SELECT * FROM utilisateur WHERE password=? AND (mail=? OR nom=?);" );
            String paramEmail = mail;
            String paramMotDePasse = password;
            preparedStatement.setString( 1, paramMotDePasse );
            preparedStatement.setString( 2, paramEmail );
            preparedStatement.setString( 3, paramEmail );

            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                compteur++;
            }
			this.setNombre_resultat(compteur);

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
        return nombre_resultat > 0;
	}
}
