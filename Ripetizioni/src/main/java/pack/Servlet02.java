package pack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.*;
import DAO.*;

@WebServlet(name = "Servlet02", urlPatterns = {"/Servlet02"})
public class Servlet02 extends HttpServlet {
    public void init(ServletConfig conf) throws ServletException {
        DAO.registerDriver();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String utente = request.getParameter("futente");
            String password = request.getParameter("fpassword");
            //System.out.println(utente + password);
            if(DAO.checkDB(utente, password)){
                out.println("Utente Registrato!");
                out.println(utente + password);
                out.flush();
            }
            else
                out.println("Utente Non Registrato!");
        } finally {
            if (out!=null)
                out.close();
        }
    }
}
