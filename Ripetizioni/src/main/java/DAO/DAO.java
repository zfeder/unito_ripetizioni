package DAO;

import java.sql.*;
import java.util.ArrayList;


public class DAO {

    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    //SELECT
    public static ArrayList<Persona> selectDB() {
        Connection conn1 = null;
        ArrayList<Persona> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PERSONA");
            while (rs.next()) {
                Persona p = new Persona(rs.getString("NOME"), rs.getString("COGNOME"), rs.getString("MATRICOLA"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }


    //CONTROL
    public static boolean checkDB(String futente, String fpassword) {
        Connection conn1 = null;
        boolean t = false;
        //System.out.println("Utente: " + futente);
        //System.out.println("Password: " + fpassword);
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE");
            String s = "Amministratore";
            while (rs.next() && t == false) {
                String utente = rs.getString("ID_UTENTE");
                String password = rs.getString("PASSWORD");
                String ruolo = rs.getString("RUOLO");
                if(ruolo.equals(s))
                    System.out.println("Questo utente è un amministratore");
                else
                    System.out.println("Questo utente non è un amministratore");
                if (utente.equals(futente) && password.equals(fpassword)) {
                    t = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return t;
    }

}
