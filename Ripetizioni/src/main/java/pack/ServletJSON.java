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
import java.util.*;

import DAO.*;

@WebServlet(name = "ServletJSON", urlPatterns = {"/ServletJSON"})
public class ServletJSON extends HttpServlet {
    public void init(ServletConfig conf) throws ServletException {
        DAO.registerDriver();
    }

    private JSONManager JSONMan = new JSONManager();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String azione = request.getParameter("azione");
        switch (azione) {
            case "getUtente":
                HttpSession b = request.getSession();
                String utente2 = (String) b.getAttribute("utente");
                PrintWriter out = response.getWriter();
                String ris;
                if (utente2 != null) {
                    response.setContentType("application/json");
                    String s = JSONMan.serializeJson(new Utente("nome", "cognome", "nomeutente", "password", "ruolo"));
                    request.setAttribute("info", s);
                    ArrayList<Utente> ar = DAO.selectDB();
                    ris = JSONMan.serializeJson(ar);
                    System.out.println("Collegato alla ServletJSON - getUtente");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris);
                    System.out.println("---------------------------------------------------------");
                } else {
                    ris = "Sessione scaduta";
                }
                out.print(ris);

                break;

            case "getCalendario":
                response.setContentType("application/json");
                PrintWriter out1 = response.getWriter();
                String nome = request.getParameter("value");
                String s1 = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Giorno", "Stato", "nome", "cognome"));
                request.setAttribute("info", s1);
                ArrayList<Prenotazione> ar1 = DAO.prenotazioneDB(nome);
                String ris1 = JSONMan.serializeJson(ar1);
                System.out.println("Collegato alla ServletJSON - getCalendario");
                System.out.println("Nome Materia: " + nome);
                System.out.println("---------------------------------------------------------");
                System.out.println(ris1);
                System.out.println("---------------------------------------------------------");
                out1.print(ris1);
                break;

            case "getCorso":
                HttpSession d = request.getSession();
                String utente4 = (String) d.getAttribute("utente");
                PrintWriter out2 = response.getWriter();
                String ris2;
                if (utente4 != null) {
                    response.setContentType("application/json");
                    String s2 = JSONMan.serializeJson(new Insegnamento("idInsegnamento", "idCorso", "idDocente"));
                    request.setAttribute("info", s2);
                    ArrayList<Insegnamento> ar2 = DAO.InsegnamentoDB();
                    ris2 = JSONMan.serializeJson(ar2);
                    System.out.println("Collegato alla ServletJSON - getCorso");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris2);
                    System.out.println("---------------------------------------------------------");
                } else {
                    ris2 = "Sessione scaduta";
                }
                out2.print(ris2);
                break;

            case "getMateria" :
                response.setContentType("application/json");
                PrintWriter out3 = response.getWriter();
                String s3 = JSONMan.serializeJson(new Materia("TitoloCorso"));
                request.setAttribute("info", s3);
                ArrayList<Materia> ar3 = DAO.MateriaDB();
                String ris3 = JSONMan.serializeJson(ar3);
                System.out.println("Collegato alla ServletJSON - getMateria");
                System.out.println("---------------------------------------------------------");
                System.out.println(ris3);
                System.out.println("---------------------------------------------------------");
                out3.print(ris3);
                break;

            case "getMateria2" :
                HttpSession e = request.getSession();
                String utente5 = (String) e.getAttribute("utente");
                PrintWriter out6 = response.getWriter();
                String ris6;
                if (utente5 != null) {
                    response.setContentType("application/json");
                    String s6 = JSONMan.serializeJson(new Materia("TitoloCorso"));
                    request.setAttribute("info", s6);
                    String idDocenteC = request.getParameter("idDocenteC");
                    ArrayList<Materia> ar6 = DAO.Materia2DB(idDocenteC);
                    ris6 = JSONMan.serializeJson(ar6);
                    System.out.println("Collegato alla ServletJSON - getMateria2");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris6);
                    System.out.println("---------------------------------------------------------");
                } else {
                    ris6 ="Sessione scaduta";
                }
                out6.print(ris6);
                break;

            case "getDocente" :
                HttpSession f = request.getSession();
                String utente6 = (String) f.getAttribute("utente");
                PrintWriter out4 = response.getWriter();
                String ris4;
                if (utente6 != null) {
                    response.setContentType("application/json");
                    String s4 = JSONMan.serializeJson(new Docente("nome", "cognome", "idDocente"));
                    request.setAttribute("info", s4);
                    ArrayList<Docente> ar4 = DAO.DocenteDB();
                    ris4 = JSONMan.serializeJson(ar4);
                    System.out.println("Collegato alla ServletJSON - getDocente");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris4);
                    System.out.println("---------------------------------------------------------");
                } else {
                    ris4 = "Sessione scaduta";
                }
                out4.print(ris4);
                break;

            case "getCorsoAttivo":
                HttpSession g = request.getSession();
                String utente7 = (String) g.getAttribute("utente");
                PrintWriter out5 = response.getWriter();
                String ris5;
                if (utente7 != null) {
                    response.setContentType("application/json");
                    String s5 = JSONMan.serializeJson(new Insegnamento("idInsegnamento", "idCorso", "idDocente"));
                    request.setAttribute("info", s5);
                    ArrayList<Insegnamento> ar5 = DAO.InsegnamentoAttivoDB();
                    ris5 = JSONMan.serializeJson(ar5);
                    System.out.println("Collegato alla ServletJSON - getCorsoAttivo");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris5);
                    System.out.println("---------------------------------------------------------");
                } else {
                    ris5 = "Sessione scaduta";
                }
                out5.print(ris5);
                break;

            case "getCalendario2":
                response.setContentType("application/json");
                PrintWriter out7 = response.getWriter();
                String nome1 = request.getParameter("value");
                String s6 = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Giorno", "Stato", "nome", "cognome"));
                request.setAttribute("info", s6);
                ArrayList<Prenotazione> ar2 = DAO.prenotazioneDB(nome1);
                String s = JSONMan.serializeJson(ar2);
                String vero = "{ \"prenotazioni\" :" + s + "}";
                System.out.println("Collegato alla ServletJSON - getCalendario");
                System.out.println("Nome Materia: " + nome1);
                System.out.println("---------------------------------------------------------");
                System.out.println(vero);
                System.out.println("---------------------------------------------------------");
                out7.print(vero);
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold
}
