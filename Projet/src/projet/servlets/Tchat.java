package projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebServices.Chat.Message;
import projet.beans.Utilisateur;
import WebServices.Chat.*;
/**
 * Servlet implementation class Tchat
 */
@WebServlet("/Tchat")
public class Tchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	Hashtable<Integer, ArrayList<Message>> liste_Chat;
	ArrayList<Message> liste_Messages;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Tchat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init Servlet");
		liste_Chat = new Hashtable<Integer, ArrayList<Message>>();
		liste_Messages = new ArrayList<Message>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// RENVOIE LISTE MESSAGE
		if(request.getParameter("id")!=null){
			if(liste_Chat.containsKey(Integer.parseInt(request.getParameter("id")))){
				ArrayList<Message> liste_msg_temp=liste_Chat.get(Integer.parseInt(request.getParameter("id")));
				response.setContentType("html");
				for (Message msg_temp : liste_msg_temp) {
					response.getWriter().print("<p>"+msg_temp.getPseudo()+" : "+msg_temp.getMessage()+" ("+msg_temp.getHeure()+")</p> <br/>"); 
				}
			}
			else{
				response.getWriter().print("<p> Pas de message pour l'instant </p>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ECRITURE MESSAGE
		if(request.getParameterMap().containsKey("id") && request.getParameterMap().containsKey("message")){
			if(!liste_Chat.containsKey(Integer.parseInt(request.getParameter("id")))){  // SI LA LISTE NE CONTIENT AUCUN MESSAGE ON LA CREE
				liste_Chat.put(Integer.parseInt(request.getParameter("id")), new ArrayList<Message>());
				System.out.println("Creation de la liste");
			}
			// ENSUITE ON ACCEDE A LA LISTE ET AJOUTE LE NOUVEAU MESSAGE
			HttpSession session = request.getSession();
			Utilisateur user =(Utilisateur) session.getAttribute(ATT_SESSION_USER);
			ArrayList<Message> liste_msg_temp=liste_Chat.get(Integer.parseInt(request.getParameter("id")));
			liste_msg_temp.add(new Message(request.getParameter("message"),user.getEmail()));
			System.out.println("Lecture de la liste :");
			for (Message msg_temp : liste_msg_temp) {
				System.out.println(msg_temp.getMessage());
			}

		}

	}

}
