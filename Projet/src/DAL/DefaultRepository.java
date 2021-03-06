package DAL;

import WebServices.Ressources.BddRessources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultRepository {
    public Connection getConnection(){
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        try{
            return DriverManager.getConnection(BddRessources.url, BddRessources.utilisateur, BddRessources.motDePasse);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
