package projet.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message {
	@SuppressWarnings("unused")
	private final int NUM_MESSAGE;
	private static int nbMessages = 0;
	private String pseudo;
	private String contenu;
	private String heure;

	public Message(String s,String pseudo) {
		NUM_MESSAGE = nbMessages;
		nbMessages++;
		this.pseudo=pseudo;
		this.setHeure(new SimpleDateFormat("hh:mm:ss", Locale.FRANCE).format(new Date()));
		this.setContenu(s);

	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}
}
