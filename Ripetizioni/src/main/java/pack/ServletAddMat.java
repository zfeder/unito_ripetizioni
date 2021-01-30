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


@WebServlet(name = "ServletAddMat", urlPatterns = {"/ServletAddMat"})
public class ServletAddMat extends HttpServlet {

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
        String titolocorso = request.getParameter("idMateria");
        //System.out.println("INSERIMENTO DEI PARAMETRI");
        String stato = "Errore inserimento";
        if (titolocorso != null) {
            Materia m = new Materia(titolocorso);
            System.out.println("PARAMETRI INSERITI CORRETTAMENTE");
            System.out.println(m.getTitoloCorso());
            DAO.insertMateria(m);
            stato = "Materia aggiunta correttamente";
        }
        out.print(stato);


    }

}

