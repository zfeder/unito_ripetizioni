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


@WebServlet(name = "ServletAddCorso", urlPatterns = {"/ServletAddCorso"})
public class ServletAddCorso extends HttpServlet {

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
        String idDocenteC = request.getParameter("idDocenteC");
        String idCorso = request.getParameter("idCorso");
        //System.out.println("INSERIMENTO DEI PARAMETRI");
        int stato = 33;
        if ( idDocenteC!= null  && idCorso != null) {
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(idDocenteC);
            System.out.println(idCorso);
            stato =  (DAO.insertInsegnamento(idDocenteC, idCorso));
        }
        out.print(stato);

    }

}

