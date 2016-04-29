package WebServices.Chat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message {
	private static int nbMessages;
	@SuppressWarnings("unused")
	private final int NUM_MESSAGE = nbMessages++;
	private String pseudo;
	private String message;
	private String heure;
	
public	Message(String pseudo, String message) {
		this.pseudo = pseudo;
		this.message = message;
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	this.heure = sdf.format(cal.getTime());
	}
	
	public static int getNbMessages() {
	return nbMessages;
}

public static void setNbMessages(int nbMessages) {
	Message.nbMessages = nbMessages;
}

public String getPseudo() {
	return pseudo;
}

public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}

public String getHeure() {
	return heure;
}

public void setHeure(String heure) {
	this.heure = heure;
}

	public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

	public String toString() {
		return ("["+heure+"] "+pseudo+" : "+message);
	}
}
