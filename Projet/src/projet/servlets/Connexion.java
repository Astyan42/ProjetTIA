package projet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Services.UserService;
import projet.beans.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "utilisateur";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE              = "/Index";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter( "connexion_mail" );

      /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
        Utilisateur utilisateur =new Utilisateur();
        utilisateur.setEmail(request.getParameter( "connexion_mail" ));
        utilisateur.setMotDePasse(request.getParameter( "connexion_password" ));
        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();
        if (UserService.getInstance().IsAlreadyRegistered(userName)) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );

        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }
        
        response.sendRedirect("Index");
	}

}
