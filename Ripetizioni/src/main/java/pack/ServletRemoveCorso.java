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
import java.sql.SQLException;

import DAO.*;

@WebServlet(name = "ServletRemoveCorso", urlPatterns = {"/ServletRemoveCorso"})
public class ServletRemoveCorso extends HttpServlet {
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
        String idCorRem = request.getParameter("idCorRem");
        System.out.println(idCorRem);
        String stato = "Errore nel rimuovere il corso";

        if (idCorRem != null) {
            CorsoREM p = new CorsoREM(idCorRem);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(p.getCorsoREM());
            DAO.removeCorso(p);
            stato = "Corso rimosso correttamente";
        }
        out.print(stato);
    }
}

