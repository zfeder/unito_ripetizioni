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
        //PrintWriter out = response.getWriter();
        String cins = request.getParameter("cins");
        String cdoc = request.getParameter("cdoc");
        String ccor = request.getParameter("ccor");
        //System.out.println("INSERIMENTO DEI PARAMETRI");

        if (cins != null && cdoc != null && ccor != null) {
            Insegnamento d = new Insegnamento(cins, cdoc, ccor);
            System.out.println(d.getidInsegnamento());
            System.out.println(d.getidDocenteC());
            System.out.println(d.getidCorso());
            System.out.println("PARAMETRI CORSO INSERITI CORRETTAMENTE");
            DAO.insertInsegnamento(d);
        }
    }

}

