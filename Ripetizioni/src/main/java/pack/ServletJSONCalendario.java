package pack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.*;

import DAO.*;

@WebServlet(name = "ServletJSONCalendario", urlPatterns = {"/ServletJSONCalendario"})
public class ServletJSONCalendario extends HttpServlet {
    public void init(ServletConfig conf) throws ServletException {
        DAO.registerDriver();
    }

    // creo il traduttore da e verso JSON; si puo' usare Gson in alternativa.
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

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // creo oggetto JSON con oggetto Coppia
        String s = JSONMan.serializeJson(new Prenotazione("idPrenotazione", "idUtente", "idDocente", "idCorso", "Orario", "Giorno"));
        request.setAttribute("info", s);


        ArrayList<Prenotazione> ar = DAO.PrenotazioneDB();
        String ris = JSONMan.serializeJson(ar);
        System.out.println(ris);
        out.print(ris);
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
