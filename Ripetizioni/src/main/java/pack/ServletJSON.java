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
        System.out.println(azione);
        switch (azione) {
            case "getUtente":
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                String s = JSONMan.serializeJson(new Utente("nome", "cognome", "nomeutente", "password", "ruolo"));
                request.setAttribute("info", s);
                ArrayList<Utente> ar = DAO.selectDB();
                String ris = JSONMan.serializeJson(ar);
                System.out.println(ris);
                out.print(ris);
                break;

            case "getCalendario":
                response.setContentType("application/json");
                PrintWriter out1 = response.getWriter();
                String nome = request.getParameter("value");
                System.out.println("CIAO" + nome);
                String s1 = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Giorno", "Stato", "nome", "cognome"));
                request.setAttribute("info", s1);
                ArrayList<Prenotazione> ar1 = DAO.prenotazioneDB(nome);
                String ris1 = JSONMan.serializeJson(ar1);
                System.out.println(ris1);
                out1.print(ris1);
                break;

            case "getCorso":
                response.setContentType("application/json");
                PrintWriter out2 = response.getWriter();
                String s2 = JSONMan.serializeJson(new Insegnamento("idInsegnamento", "idCorso", "idDocente"));
                request.setAttribute("info", s2);
                ArrayList<Insegnamento> ar2 = DAO.InsegnamentoDB();
                String ris2 = JSONMan.serializeJson(ar2);
                System.out.println(ris2);
                out2.print(ris2);
                break;

            case "getMateria" :
                response.setContentType("application/json");
                PrintWriter out3 = response.getWriter();
                String s3 = JSONMan.serializeJson(new Materia("TitoloCorso"));
                request.setAttribute("info", s3);
                ArrayList<Materia> ar3 = DAO.MateriaDB();
                String ris3 = JSONMan.serializeJson(ar3);
                System.out.println(ris3);
                out3.print(ris3);
                break;

            case "getDocente" :
                response.setContentType("application/json");
                PrintWriter out4 = response.getWriter();
                String s4 = JSONMan.serializeJson(new Docente("nome", "cognome", "idDocente"));
                request.setAttribute("info", s4);
                ArrayList<Docente> ar4 = DAO.DocenteDB();
                String ris4 = JSONMan.serializeJson(ar4);
                System.out.println(ris4);
                out4.print(ris4);
                break;

            case "getCorsoAttivo":
                response.setContentType("application/json");
                PrintWriter out5 = response.getWriter();
                String s5 = JSONMan.serializeJson(new Insegnamento("idInsegnamento", "idCorso", "idDocente"));
                request.setAttribute("info", s5);
                ArrayList<Insegnamento> ar5 = DAO.InsegnamentoAttivoDB();
                String ris5 = JSONMan.serializeJson(ar5);
                System.out.println(ris5);
                out5.print(ris5);
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
