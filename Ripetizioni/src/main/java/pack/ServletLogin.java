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

@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
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
        String azione = request.getParameter("azione");
        switch (azione) {
            case "login":
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                System.out.println("Collegato alla ServletLogin - login");
                String utente = request.getParameter("utente");
                String password = request.getParameter("password");
                String stato = "Nonloggato";
                if (DAO.checkDB(utente, password)) {
                    HttpSession s = request.getSession();
                    s.setAttribute("utente", utente);
                    s.setAttribute("password", password);
                    System.out.println("Login effettuato con successo!");
                    s.setMaxInactiveInterval(1000);
                    if (DAO.checkAdmin(utente)) {
                        stato = "Admin";
                    } else {
                        stato = "Utente";
                    }
                }
                System.out.println(stato);

                out.println(stato);
                break;

            case "logout":
                response.setContentType("text/html;charset=UTF-8");
                HttpSession s = request.getSession();
                System.out.println("Collegato alla ServletLogin - logout");
                System.out.println("Logout effettuato con successo!");
                s.invalidate();
                PrintWriter out1 = response.getWriter();
                out1.println("Logout effettuato con successo");
                break;

        }
    }
}


