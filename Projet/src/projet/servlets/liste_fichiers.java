package projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebServices.BddRequest.ListeFichiers;
import projet.beans.Utilisateur;

/**
 * Servlet implementation class liste_fichiers
 */
@WebServlet("/liste_fichiers")
public class liste_fichiers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_REQUETE_PROJETS = "liste_projets";
	public static final String ATT_REQUETE_FICHIERS = "liste_fichiers";
	public static final String VUE = "/Site/Mes_fichiers.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public liste_fichiers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 Utilisateur user=(Utilisateur)session.getAttribute(ATT_SESSION_USER);
		 ListeFichiers Bdd = new ListeFichiers();
		 List<String> messages = Bdd.Arborescence_bdd(user.getEmail());
		 request.setAttribute(ATT_REQUETE_PROJETS, Bdd.getListe_projets());
		 request.setAttribute(ATT_REQUETE_FICHIERS, Bdd.getListe_fichiers());
         request.setAttribute( "messages", messages );
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
