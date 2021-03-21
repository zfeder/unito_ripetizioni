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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DAO.*;

@WebServlet(name = "ServletRemove", urlPatterns = {"/ServletRemove"})
public class ServletRemove extends HttpServlet {
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
        String azione = request.getParameter("azione");
        System.out.println(azione);
        switch (azione) {
            case "removeCorso":
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                String idCorRem = request.getParameter("idCorRem");
                System.out.println(idCorRem);
                String stato = "Errore nel rimuovere il corso";
                if (idCorRem != null) {
                    System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                    String[] result = idCorRem.split(",");
                    System.out.println("idcorso"+ result[0]);
                    System.out.println("iddoc"+result[1]);
                    System.out.println("materia"+result[2]);
                    CorsoREM p = new CorsoREM(result[0]);
                    DAO.removeCorso(p);
                    DAO.removeCalendario(result[1], result[2]);
                    stato = "Corso rimosso correttamente";
                }
                out.print(stato);
                break;

            case "removeDocente":
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out1 = response.getWriter();
                String idDocRem = request.getParameter("idDocRem");
                System.out.println(idDocRem);
                String stato1 = "Errore nel rimuovere docente";
                if (idDocRem != null) {
                    DocenteREM p = new DocenteREM(idDocRem);
                    System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                    System.out.println(p.getidDocenteREM() + "idocente");
                    DAO.removeDocente(p);
                    stato1 = "Docente rimosso correttamente";
                }
                out1.print(stato1);
                break;

            case "removeMateria":
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out2 = response.getWriter();
                String idMatRem = request.getParameter("idMatRem");
                System.out.println(idMatRem);
                String stato2 = "Errore nel rimuovere il corso";
                if (idMatRem != null) {
                    MateriaREM p = new MateriaREM(idMatRem);
                    System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                    System.out.println(p.getMateriaREM());
                    DAO.removeMateria(p);
                    stato2 = "Materia rimossa correttamente";
                }
                out2.print(stato2);
                break;
        }
    }
}


