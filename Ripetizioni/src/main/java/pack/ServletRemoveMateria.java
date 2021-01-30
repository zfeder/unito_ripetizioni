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

@WebServlet(name = "ServletRemoveMateria", urlPatterns = {"/ServletRemoveMateria"})
public class ServletRemoveMateria extends HttpServlet {
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
        String idMatRem = request.getParameter("idMatRem");
        System.out.println(idMatRem);
        String stato = "Errore nel rimuovere il corso";

        if (idMatRem != null) {
            MateriaREM p = new MateriaREM(idMatRem);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(p.getMateriaREM());
            DAO.removeMateria(p);
            stato = "Materia rimossa correttamente";
        }
        out.print(stato);
    }
}

