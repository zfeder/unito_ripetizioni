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

@WebServlet(name = "ServletRemoveDoc", urlPatterns = {"/ServletRemoveDoc"})
public class ServletRemoveDoc extends HttpServlet {
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
        String idDocRem = request.getParameter("idDocRem");
        System.out.println(idDocRem);
        String stato = "Errore nel rimuovere docente";

        if (idDocRem != null) {
            DocenteREM p = new DocenteREM(idDocRem);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(p.getidDocenteREM());
            DAO.removeDocente(p);
            stato = "Docente rimosso correttamente";
        }
        out.print(stato);
    }
}

