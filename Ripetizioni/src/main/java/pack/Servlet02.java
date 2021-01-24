package pack;

import javax.servlet.RequestDispatcher;
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
        String utente = request.getParameter("utente");
        String password = request.getParameter("password");
        String stato = "Nonloggato";
        if (DAO.checkDB(utente, password)) {
            HttpSession s = request.getSession();
            if (request.getParameter("azione") != null) {
                System.out.println("Sessione invalidata");
                out.println("invalidato");
                s.invalidate();
            }
            s.setAttribute("utente", utente);
            s.setAttribute("password", password);
            if (DAO.checkAdmin(utente)) {
                stato = "Admin";
            } else {
                stato = "Utente";
            }
        }
        out.println(stato);
    }
}

