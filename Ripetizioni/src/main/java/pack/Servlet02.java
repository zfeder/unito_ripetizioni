package pack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.*;
import java.util.Date;

import DAO.*;

@WebServlet(name = "Servlet02", urlPatterns = {"/Servlet02"})
public class Servlet02 extends HttpServlet {
    public void init(ServletConfig conf) throws ServletException {
        DAO.registerDriver();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String utente = request.getParameter("utente");
            String password = request.getParameter("password");
            System.out.println(utente + password);
            String url = response.encodeURL("index.html");
            HttpSession s = request.getSession();
            String azione = request.getParameter("no");
            if (azione != null) {
                s.invalidate();
                out.println("<p>Sessione invalidata!</p>");
            } else {
                out.print("<p>Stato della sessione: ");
                if (s.isNew())
                    out.println(" nuova sessione</p>");
                else out.println(" vecchia sessione</p>");
                out.println("<p>ID di sessione: " + s.getId() + "</p>");
                out.println("<p>Data di creazione: " + new Date(s.getCreationTime()) + "</p>");
                out.println("<p>Max inactive time interval (in secondi): "
                        + s.getMaxInactiveInterval() + "</p>");
                //out.println("<p>Invalida <a href=\"" + url + "?action=invalida\"> la sessione</a></p>");
                out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");

                if (DAO.checkDB(utente, password)) {
                    out.println("Utente Registrato!");
                    out.println(utente + password);
                    {
                        //HttpSession s = request.getSession();
                        //String url = response.encodeURL("index.html");
                        if (utente != null)
                            s.setAttribute("utente", utente);
                        try {
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Servlet02</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<p>Sei collegato come: " + s.getAttribute("utente") + "</p>");
                            //String azione = request.getParameter("no");
                            out.println("<p>URL: " + url + "</p>");


                        /*else {
                            out.print("<p>Stato della sessione: ");
                            if (s.isNew())
                                out.println(" nuova sessione</p>");
                            else out.println(" vecchia sessione</p>");
                            out.println("<p>ID di sessione: " + s.getId() + "</p>");
                            out.println("<p>Data di creazione: " + new Date(s.getCreationTime()) + "</p>");
                            out.println("<p>Max inactive time interval (in secondi): "
                                    + s.getMaxInactiveInterval() + "</p>");
                            //out.println("<p>Invalida <a href=\"" + url + "?action=invalida\"> la sessione</a></p>");
                            out.println("<p>Ricarica <a href=\"" + url + "\"> la pagina</a></p>");
                        }*/
                            out.println("</body>");
                            out.println("</html>");
                        } finally {
                            out.close();
                        }
                    }

                } else
                    out.println("Utente Errato!");
            }
        }
        finally{
            if (out != null)
                out.close();
        }


    }
}