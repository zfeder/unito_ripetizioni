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
                HttpSession s3 = request.getSession();
                String utente3 = (String) s3.getAttribute("utente");
                String stato3 = " ";
                PrintWriter out = response.getWriter();
                if (utente3 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String idCorRem = request.getParameter("idCorRem");
                    System.out.println(idCorRem);
                    stato3 = "Errore nel rimuovere il corso";
                    if (idCorRem != null) {
                        System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                        String[] result = idCorRem.split(",");
                        System.out.println("idcorso" + result[0]);
                        System.out.println("iddoc" + result[1]);
                        System.out.println("materia" + result[2]);
                        InsegnamentoRemove p = new InsegnamentoRemove(result[0]);
                        DAO.removeCorso(p);
                        DAO.removeCalendario(result[1], result[2]);
                        stato3 = "Corso rimosso correttamente";
                    }
                } else {
                        stato3 = "Sessione scaduta";
                    }
                    out.print(stato3);
                break;

            case "removeDocente":
                HttpSession s4 = request.getSession();
                String utente4 = (String) s4.getAttribute("utente");
                String stato4 = " ";
                PrintWriter out1 = response.getWriter();
                if (utente4 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String idDocRem = request.getParameter("idDocRem");
                    System.out.println(idDocRem);
                    stato4 = "Errore nel rimuovere docente";
                    if (idDocRem != null) {
                        DocenteRemove p = new DocenteRemove(idDocRem);
                        System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                        System.out.println(p.getidDocenteRemove() + "idocente");
                        DAO.removeDocente(p);
                        stato4 = "Docente rimosso correttamente";
                    }
                } else {
                        stato4 = "Sessione scaduta";
                    }
                out1.print(stato4);
                break;

            case "removeMateria":
                HttpSession s5 = request.getSession();
                String utente5 = (String) s5.getAttribute("utente");
                String stato5 = " ";
                PrintWriter out2 = response.getWriter();
                if (utente5 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String idMatRem = request.getParameter("idMatRem");
                    System.out.println(idMatRem);
                    stato5 = "Errore nel rimuovere il corso";
                    if (idMatRem != null) {
                        MateriaRemove p = new MateriaRemove(idMatRem);
                        System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
                        System.out.println(p.getMateriaRemove());
                        DAO.removeMateria(p);
                        stato5 = "Materia rimossa correttamente";
                    }
                } else {
                        stato5 = "Sessione scaduta";
                    }
                out2.print(stato5);
                break;
        }
    }
}


