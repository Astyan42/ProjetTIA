package WebServices;

import SocketLayer.Sockets.*;
import WebServices.Chat.Chat;
import WebServices.Ressources.GlobalRessources;

import javax.servlet.*;
import java.io.IOException;

public class WebService extends GlobalRessources implements Servlet {
    /**
     * Execute toutes les sockets
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Thread(new ConnectionSocket()).start();
        new Thread(new XmlUpdateSocket()).start();
        new Thread(new ChatSocket()).start();
        //new Thread(new EditeurSocket()).start();
        new Thread(new UpdateSocket()).start();
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        new Thread(new ConnectionSocket()).start();
        new Thread(new XmlUpdateSocket()).start();
        new Thread(new ChatSocket()).start();
        //new Thread(new EditeurSocket()).start();
        new Thread(new UpdateSocket()).start();
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
