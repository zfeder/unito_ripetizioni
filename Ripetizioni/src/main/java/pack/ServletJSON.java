package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.*;
import DAO.*;

import static DAO.DAO.selectDB;


@WebServlet(name = "ServletJSON", urlPatterns = {"/ServletJSON"})
public class ServletJSON extends HttpServlet {
    // creo il traduttore da e verso JSON; si puo' usare Gson in alternativa.
    private JSONManager JSONMan = new JSONManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Collegato alla servletJSON");

        // creo oggetto JSON con oggetto Coppia
        String s = JSONMan.serializeJson(new Utente("nome", "cognome", "utente", "password", "ruolo"));
        request.setAttribute("info", s);

        ArrayList<Utente> ar = selectDB();
        String ris = JSONMan.serializeJson(ar);
        System.out.println(ris);
        out.print(ris);
    }


}