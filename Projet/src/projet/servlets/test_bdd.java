package projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WebServices.BddRequest.Connect;

@WebServlet("/testbdd")
public class test_bdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_MESSAGES = "messages";
    public static final String VUE          = "/WEB-INF/test_bdd.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Initialisation de l'objet Java et r�cup�ration des messages */
        Connect test = new Connect();
        List<String> messages = test.existance_compte( request );

        /* Enregistrement de la liste des messages dans l'objet requ�te */
        request.setAttribute( ATT_MESSAGES, messages );

        /* Transmission vers la page en charge de l'affichage des r�sultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}