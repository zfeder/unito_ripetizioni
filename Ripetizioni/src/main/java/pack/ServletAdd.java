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
import java.util.Date;

import DAO.*;

@WebServlet(name = "ServletAdd", urlPatterns = {"/ServletAdd"})
public class ServletAdd extends HttpServlet {
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
        switch (azione) {
            case "addCorso":
                HttpSession s6 = request.getSession();
                String utente4 = (String) s6.getAttribute("utente");
                String finale = null;
                PrintWriter out = response.getWriter();
                if (utente4 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String idDocenteC = request.getParameter("idDocenteC");
                    String idMateriaC = request.getParameter("idMateriaC");
                    System.out.println("Collegato alla ServletAdd - addCorso");
                    System.out.println("Valore Docente: " + idDocenteC);
                    System.out.println("Valore Materia: " + idMateriaC);
                    int stato = 33;
                    if (idDocenteC != null && idMateriaC != null) {
                        String s = "Corso aggiunto correttamente con ID: ";
                        stato = (DAO.insertInsegnamento(idDocenteC, idMateriaC));
                        String ss = "" + stato;
                        finale = s.concat(ss);
                    }
                } else {
                  finale = "Sessione scaduta";
                }
                out.print(finale);
                break;

            case "addDocente":
                HttpSession s5 = request.getSession();
                String utente3 = (String) s5.getAttribute("utente");
                String finale1 = null;
                PrintWriter out1 = response.getWriter();
                if (utente3 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    int stato1 = 33;
                    if (nome != null && cognome != null) {
                        Docente p = new Docente(nome, cognome, null);
                        System.out.println("Collegato alla ServletAdd - addDocente");
                        System.out.println("Nome Docente: " + p.getNome());
                        System.out.println("Cognome Docente: " + p.getCognome());
                        String s = "Docente aggiunto correttamente con ID: ";
                        stato1 = (DAO.insertDocente(p.getNome(), p.getCognome()));
                        String ss = "" + stato1;
                        finale1 = s.concat(ss);
                    }
                } else {
                    finale1 = "Sessione scaduta";
                }
                out1.print(finale1);

                break;

            case "addMateria":
                HttpSession s3 = request.getSession();
                String utente2 = (String) s3.getAttribute("utente");
                String stato2 = " ";
                PrintWriter out2 = response.getWriter();
                if (utente2 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    String titolocorso = request.getParameter("idMateria");
                     stato2 = "Errore inserimento";
                    if (titolocorso != null) {
                        if (DAO.checkMateria((titolocorso))) {
                            stato2 = "Materia già esistente";
                        } else {
                            Materia m = new Materia(titolocorso);
                            System.out.println("Collegato alla ServletAdd - addMateria");
                            System.out.println("Nome Materia: " + m.getTitoloCorso());
                            DAO.insertMateria(m);
                            stato2 = "Materia aggiunta correttamente";
                        }
                    }
                } else {
                        stato2 = "Sessione scaduta";
                    }
                out2.print(stato2);
                break;
        }
    }
}


