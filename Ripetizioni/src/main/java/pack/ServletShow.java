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

@WebServlet(name = "ServletShow", urlPatterns = {"/ServletShow"})
public class ServletShow extends HttpServlet {
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
        System.out.println(azione);
        switch (azione) {
            case "miePrenotazioni":
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                HttpSession s = request.getSession();
                System.out.println(s.getAttribute("provaaaa"));
                String l = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome") );
                request.setAttribute("info", l);
                String Utente = String.valueOf(s.getAttribute("utente"));
                ArrayList<Prenotazione> ar = DAO.MiaPrenotazione(Utente);
                String ris = JSONMan.serializeJson(ar);
                System.out.println(ris);
                out.print(ris);
                break;

            case "buttonPrenotazioni":
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out1 = response.getWriter();
                System.out.println("Collegato alla servlet Prenota");
                String docente = request.getParameter("docente");
                String orario = request.getParameter("orario");
                String giorno = request.getParameter("giorno");
                String corso = request.getParameter("corso");
                HttpSession s1 = request.getSession();
                String utente = (String) s1.getAttribute("utente");
                System.out.println(utente + docente + orario + giorno + corso);
                try {
                    DAO.prenota(docente, orario, giorno, corso, utente);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //System.out.println("INSERIMENTO DEI PARAMETRI");
                String stato1 = "Prenotazione avvenuta con successo";
                out1.println(stato1);
                break;

            case "allPrenotazioni":
                response.setContentType("application/json");
                PrintWriter out2 = response.getWriter();
                String s2 = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome") );
                request.setAttribute("info", s2);
                ArrayList<Prenotazione> ar2 = DAO.PrenotazioneDB();
                String ris2 = JSONMan.serializeJson(ar2);
                System.out.println(ris2);
                out2.print(ris2);
                break;

            case "buttonDisdici" :
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out3 = response.getWriter();
                System.out.println("Collegato alla servlet Prenota");
                String idPrenotazione = request.getParameter("idPrenotazione");
                String idUtente = request.getParameter("idUtente");
                String idDocente = request.getParameter("idDocente");
                String idCorso = request.getParameter("idCorso");
                String orario1 = request.getParameter("orario");
                String giorno1 = request.getParameter("giorno");
                String stato = request.getParameter("stato");
                HttpSession s3 = request.getSession();
                String utente2 = (String) s3.getAttribute("utente");
                System.out.println(utente2 + idPrenotazione + idUtente + idDocente + idCorso  + orario1 + giorno1 + stato);
                try {
                    DAO.disdici(idPrenotazione, idUtente, idDocente, idCorso, orario1, giorno1, utente2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //System.out.println("INSERIMENTO DEI PARAMETRI");
                String stato3 = "Prenotazione disdetta con successo";
                out3.println(stato3);
                break;


            case "buttonSvolta" :
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out4 = response.getWriter();
                System.out.println("Collegato alla servlet Prenota");
                String idPrenotazione2 = request.getParameter("idPrenotazione");
                String idUtente2 = request.getParameter("idUtente");
                String idDocente2 = request.getParameter("idDocente");
                String idCorso2 = request.getParameter("idCorso");
                String orario2 = request.getParameter("orario");
                String giorno2 = request.getParameter("giorno");
                String stato2 = request.getParameter("stato");
                HttpSession s4 = request.getSession();
                String utente3 = (String) s4.getAttribute("utente");
                System.out.println(utente3 + idPrenotazione2 + idUtente2 + idDocente2 + idCorso2  + orario2 + giorno2 + stato2);
                try {
                    DAO.svolta(idPrenotazione2, idUtente2, idDocente2, idCorso2, orario2, giorno2, utente3);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //System.out.println("INSERIMENTO DEI PARAMETRI");
                String stato4 = "Prenotazione svolta con successo";
                out4.println(stato4);
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
