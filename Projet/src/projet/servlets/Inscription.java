package projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model_Objects.User;
import Services.UserService;
import projet.beans.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "utilisateur";
	public static final String ATT_FORM         = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE              = "/index";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter( "inscription_nom" );
		String mail = request.getParameter( "inscription_mail" );
		String password = request.getParameter( "inscription_password" );

		User user = UserService.getInstance().createUser(name,mail,password);

		Utilisateur utilisateur =new Utilisateur();
		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();
		if (user != null) {
			utilisateur.setNom(request.getParameter( "inscription_nom" ));
			utilisateur.setEmail(request.getParameter( "inscription_mail" ));
			utilisateur.setMotDePasse(request.getParameter( "inscription_password" ));
			session.setAttribute( ATT_SESSION_USER, utilisateur );

		} else {
			session.setAttribute( ATT_SESSION_USER, null );
		}
		response.sendRedirect("Index");
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
