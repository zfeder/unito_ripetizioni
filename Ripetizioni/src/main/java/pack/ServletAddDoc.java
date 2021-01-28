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
        //PrintWriter out = response.getWriter();
        String dname = request.getParameter("dname");
        String lname = request.getParameter("lname");
        String idDoc = request.getParameter("idDoc");
        //System.out.println("INSERIMENTO DEI PARAMETRI");

        if (dname != null && lname != null && idDoc != null) {
            Docente p = new Docente(dname, lname, idDoc);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(p.getNome());
            System.out.println(p.getCognome());
            System.out.println(p.getidocente());
            DAO.insertDocente(p);
        }
    }

}

