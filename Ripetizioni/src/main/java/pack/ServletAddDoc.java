package pack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.*;


@WebServlet(name = "ServletAddDoc", urlPatterns = {"/ServletAddDoc"})
public class ServletAddDoc extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String iddocente = request.getParameter("iddocente");
        //System.out.println("INSERIMENTO DEI PARAMETRI");
        String stato = "Errore inserimento";
        if (nome != null && cognome != null && iddocente != null) {
            Docente p = new Docente(nome, cognome, iddocente);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(p.getNome());
            System.out.println(p.getCognome());
            System.out.println(p.getidDocente());
            DAO.insertDocente(p);
            stato = "Docente aggiunto correttamente";
        }
        out.print(stato);


    }

}

