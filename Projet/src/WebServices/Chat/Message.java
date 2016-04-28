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
	
	Message(String pseudo, String message) {
		this.pseudo = pseudo;
		this.message = message;
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	this.heure = sdf.format(cal.getTime());
	}
	
	public String toString() {
		return ("["+heure+"] "+pseudo+" : "+message);
	}
}
