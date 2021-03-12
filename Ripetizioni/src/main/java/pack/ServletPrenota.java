package pack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.*;


@WebServlet(name = "ServletPrenota", urlPatterns = {"/ServletPrenota"})
public class ServletPrenota extends HttpServlet {

    public void init(ServletConfig conf) throws ServletException {
        DAO.registerDriver();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String docente = request.getParameter("docente");
        String orario = request.getParameter("orario");
        String giorno = request.getParameter("giorno");
        String corso = request.getParameter("corso");
        HttpSession s = request.getSession();
        String utente = (String) s.getAttribute("utente");
        System.out.println(utente);
        DAO.prenota(docente, orario, giorno, corso, utente);
        //System.out.println("INSERIMENTO DEI PARAMETRI");


    }

}

