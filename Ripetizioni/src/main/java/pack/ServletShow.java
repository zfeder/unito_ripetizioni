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
        switch (azione) {
            case "miePrenotazioni":
                HttpSession s10 = request.getSession();
                String utente10 = (String) s10.getAttribute("utente");
                String stato10 = " ";
                PrintWriter out = response.getWriter();
                if (utente10 != null) {
                    response.setContentType("application/json");
                    HttpSession s = request.getSession();
                    String l = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome"));
                    request.setAttribute("info", l);
                    String Utente = String.valueOf(s.getAttribute("utente"));
                    ArrayList<Prenotazione> ar = DAO.MiaPrenotazione(Utente);
                    String ris = JSONMan.serializeJson(ar);
                    System.out.println("Collegato alla ServletShow - miePrenotazioni");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris);
                    System.out.println("---------------------------------------------------------");
                    out.print(ris);
                } else {
                    stato10 = "Sessione scaduta";
                }
                out.println(stato10);
                break;

            case "buttonPrenotazioni":
                HttpSession s11 = request.getSession();
                String utente11 = (String) s11.getAttribute("utente");
                String stato11 = " ";
                PrintWriter out1 = response.getWriter();
                if (utente11 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    System.out.println("Collegato alla ServletShow - buttonPrenotazioni");
                    String docente = request.getParameter("docente");
                    String orario = request.getParameter("orario");
                    String giorno = request.getParameter("giorno");
                    String corso = request.getParameter("corso");
                    HttpSession s1 = request.getSession();
                    String utente = (String) s1.getAttribute("utente");
                    System.out.println("Nome Utente: " + utente);
                    System.out.println("ID Docente: " + docente);
                    System.out.println("Orario: " + orario);
                    System.out.println("Giorno: " + giorno);
                    System.out.println("Nome Materia: : " + corso);
                    try {
                        DAO.prenota(docente, orario, giorno, corso, utente);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String stato1 = "Prenotazione avvenuta con successo";
                    out1.println(stato1);
                } else {
                    stato11 = "Sessione scaduta";
                }
                out1.println(stato11);
                break;

            case "allPrenotazioni":
                HttpSession s12 = request.getSession();
                String utente12 = (String) s12.getAttribute("utente");
                String stato12 = " ";
                PrintWriter out2 = response.getWriter();
                if (utente12 != null) {
                    response.setContentType("application/json");
                    String s2 = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome"));
                    request.setAttribute("info", s2);
                    ArrayList<Prenotazione> ar2 = DAO.PrenotazioneDB();
                    String ris2 = JSONMan.serializeJson(ar2);
                    System.out.println("Collegato alla ServletShow - allPrenotazioni");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris2);
                    System.out.println("---------------------------------------------------------");
                    out2.print(ris2);
                } else {
                stato12 = "Sessione scaduta";
                }
                out2.println(stato12);
                break;

            case "buttonDisdici" :
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out3 = response.getWriter();
                System.out.println("Collegato alla ServletShow - buttonDisdici");
                String idPrenotazione = request.getParameter("idPrenotazione");
                String idUtente = request.getParameter("idUtente");
                String idDocente = request.getParameter("idDocente");
                String idCorso = request.getParameter("idCorso");
                String orario1 = request.getParameter("orario");
                String giorno1 = request.getParameter("giorno");
                String stato = request.getParameter("stato");
                HttpSession s3 = request.getSession();
                String utente2 = (String) s3.getAttribute("utente");
                System.out.println("Nome Utente: " + idUtente);
                System.out.println("ID Prenotazione: " + idPrenotazione);
                System.out.println("ID Docente: " + idDocente);
                System.out.println("ID Corso: " + idCorso);
                System.out.println("Orario: " + orario1);
                System.out.println("Giorno: " + giorno1);
                System.out.println("Stato: " + stato);
                try {
                    DAO.disdici(idPrenotazione, idUtente, idDocente, idCorso, orario1, giorno1, utente2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String stato3 = "Prenotazione disdetta con successo";
                out3.println(stato3);
                break;


            case "buttonSvolta" :
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out4 = response.getWriter();
                System.out.println("Collegato alla ServletShow - buttonSvolta");
                String idPrenotazione2 = request.getParameter("idPrenotazione");
                String idUtente2 = request.getParameter("idUtente");
                String idDocente2 = request.getParameter("idDocente");
                String idCorso2 = request.getParameter("idCorso");
                String orario2 = request.getParameter("orario");
                String giorno2 = request.getParameter("giorno");
                String stato2 = request.getParameter("stato");
                HttpSession s4 = request.getSession();
                String utente3 = (String) s4.getAttribute("utente");
                System.out.println("Nome Utente: " + idUtente2);
                System.out.println("ID Prenotazione: " + idPrenotazione2);
                System.out.println("ID Docente: " + idDocente2);
                System.out.println("ID Corso: " + idCorso2);
                System.out.println("Orario: " + orario2);
                System.out.println("Giorno: " + giorno2);
                System.out.println("Stato: " + stato2);
                try {
                    DAO.svolta(idPrenotazione2, idUtente2, idDocente2, idCorso2, orario2, giorno2, utente3);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String stato4 = "Prenotazione svolta con successo";
                out4.println(stato4);
                break;

            case "Prenota":
                HttpSession s16 = request.getSession();
                String utente16 = (String) s16.getAttribute("utente");
                String stato16 = " ";
                PrintWriter out16 = response.getWriter();
                if (utente16 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    System.out.println("Collegato alla ServletShow - buttonPrenotazioni");
                    String idPrenotazione16 = request.getParameter("idPrenotazione");
                    HttpSession s1 = request.getSession();
                    String utente = (String) s1.getAttribute("utente");
                    System.out.println("Nome Utente: " + utente);
                    System.out.println("idPrenotazione: " + idPrenotazione16);

                    try {
                        DAO.prenotaAndroid(idPrenotazione16, utente);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String stato1 = "Prenotazione avvenuta con successo";
                    out16.println(stato1);
                } else {
                    stato16 = "Sessione scaduta";
                }
                out16.println(stato16);
                break;

            case "miePrenotazioni2":
                HttpSession s22 = request.getSession();
                String utente22 = (String) s22.getAttribute("utente");
                String stato22 = " ";
                PrintWriter out22 = response.getWriter();
                if (utente22 != null) {
                    response.setContentType("application/json");
                    HttpSession s = request.getSession();
                    String l = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome"));
                    request.setAttribute("info", l);
                    String Utente = String.valueOf(s.getAttribute("utente"));
                    ArrayList<Prenotazione> ar = DAO.MiaPrenotazioneAD(Utente);
                    String ris = JSONMan.serializeJson(ar);
                    String vero = "{ \"prenotazioni\" :" + ris + "}";
                    System.out.println("Collegato alla ServletShow - miePrenotazioni");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris);
                    System.out.println("---------------------------------------------------------");
                    out22.print(vero);
                } else {
                    stato22 = "Sessione scaduta";
                }
                out22.println(stato22);
                break;

            case "Storico":
                HttpSession s44 = request.getSession();
                String utente44 = (String) s44.getAttribute("utente");
                String stato44 = " ";
                PrintWriter out44 = response.getWriter();
                if (utente44 != null) {
                    response.setContentType("application/json");
                    HttpSession s = request.getSession();
                    String l = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Data", "Stato", "nome", "cognome"));
                    request.setAttribute("info", l);
                    String Utente = String.valueOf(s.getAttribute("utente"));
                    ArrayList<Prenotazione> ar = DAO.StoricoAD(Utente);
                    String ris = JSONMan.serializeJson(ar);
                    String vero = "{ \"prenotazioni\" :" + ris + "}";
                    System.out.println("Collegato alla ServletShow - miePrenotazioni");
                    System.out.println("---------------------------------------------------------");
                    System.out.println(ris);
                    System.out.println("---------------------------------------------------------");
                    out44.print(vero);
                } else {
                    stato44 = "Sessione scaduta";
                }
                out44.println(stato44);
                break;

            case "Svolta":
                HttpSession s222 = request.getSession();
                String utente222 = (String) s222.getAttribute("utente");
                String stato222 = " ";
                PrintWriter out222= response.getWriter();
                if (utente222 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    System.out.println("Collegato alla ServletShow - buttonPrenotazioni");
                    String idPrenotazione222 = request.getParameter("idPrenotazione");
                    HttpSession s1 = request.getSession();
                    String utente = (String) s1.getAttribute("utente");
                    System.out.println("Nome Utente: " + utente);
                    System.out.println("idPrenotazione: " + idPrenotazione222);

                    try {
                        DAO.svoltaAndroid(idPrenotazione222, utente);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String stato1 = "Prenotazione avvenuta con successo";
                    out222.println(stato1);
                } else {
                    stato222 = "Sessione scaduta";
                }
                out222.println(stato222);
                break;

            case "Disdici":
                HttpSession s333 = request.getSession();
                String utente333 = (String) s333.getAttribute("utente");
                String stato333 = " ";
                PrintWriter out333= response.getWriter();
                if (utente333 != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    System.out.println("Collegato alla ServletShow - buttonPrenotazioni");
                    String idPrenotazione333 = request.getParameter("idPrenotazione");
                    HttpSession s1 = request.getSession();
                    String utente = (String) s1.getAttribute("utente");
                    System.out.println("Nome Utente: " + utente);
                    System.out.println("idPrenotazione: " + idPrenotazione333);

                    try {
                        DAO.disdiciAndroid(idPrenotazione333, utente);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String stato1 = "Prenotazione avvenuta con successo";
                    out333.println(stato1);
                } else {
                    stato333 = "Sessione scaduta";
                }
                out333.println(stato333);
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
