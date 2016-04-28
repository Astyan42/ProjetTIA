package WebServices.Ressources;

import WebServices.BddRequest.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BddRessources {
    public String url = "jdbc:mysql://localhost:3306/bdd_ptg_fichier";
    public String utilisateur = "root";
    public String motDePasse = "toor";
    public Connection connexion = null;
    public ResultSet resultat = null;
    public PreparedStatement preparedStatement=null;
}
