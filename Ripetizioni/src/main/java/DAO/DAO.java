package DAO;

import java.sql.*;
import java.util.ArrayList;


public class DAO {

    private static final String url1 = "jdbc:mysql://localhost:3306/unito_ripetizioni";
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
    public static ArrayList<Utente> selectDB() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE");
            while (rs.next()) {
                Utente u = new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("nomeUtente"), rs.getString("password"), rs.getString("ruolo"));
                out.add(u);
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

    public static ArrayList<Prenotazione> PrenotazioneDB() {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"), rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Data"));
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

    public static ArrayList<Insegnamento> InsegnamentoDB() {
        Connection conn1 = null;
        ArrayList<Insegnamento> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM INSEGNAMENTO");
            while (rs.next()) {
                Insegnamento p = new Insegnamento(rs.getString("idInsegnamento"), rs.getString("idDocente"), rs.getString("idCorso"));
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

    public static ArrayList<Prenotazione> prenotazioneDB() {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"),
                                    rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Data"));
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
        System.out.println("Utente: " + futente);
        System.out.println("Password: " + fpassword);
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE");
            String s = "Amministratore";
            while (rs.next() && t == false) {
                String utente = rs.getString("NOMEUTENTE");
                String password = rs.getString("PASSWORD");
                String ruolo = rs.getString("RUOLO");
                //if(ruolo.equals(s))
                    //System.out.println("Questo utente è un amministratore");
                //else
                    //System.out.println("Questo utente non è un amministratore");
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

    public static boolean checkAdmin(String futente) {
        Connection conn1 = null;
        boolean b = false;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                //System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE");
            String s = "Amministratore";
            while (rs.next() && b == false) {
                String utente = rs.getString("NOMEUTENTE");
                String ruolo = rs.getString("RUOLO");
                if(utente.equals(futente) && ruolo.equals(s)) {
                    System.out.println("Amministratore");
                    b = true;
                }
                else
                    System.out.println("Non Amministratore");
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
        return b;
    }

    public static ArrayList<Materia> MateriaDB() {
        Connection conn1 = null;
        ArrayList<Materia> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO");
            while (rs.next()) {
                Materia p = new Materia(rs.getString("titoloCorso"));
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

    public static void insertDocente(Docente p) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO DOCENTE (NOME, COGNOME, IDDOCENTE) VALUES (?,?,?)");
        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getCognome());
        stmt.setString(3, p.getidDocente());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void insertInsegnamento(Insegnamento i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO INSEGNAMENTO (IDINSEGNAMENTO, IDDOCENTE, IDCORSO) VALUES (?,?,?)");
        stmt.setString(1, i.getidInsegnamento());
        stmt.setString(2, i.getidDocenteC());
        stmt.setString(3, i.getidCorso());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void removeDocente(DocenteREM i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM DOCENTE WHERE iddocente=?;");
        stmt.setString(1, i.getidDocenteREM());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void removeCorso(CorsoREM i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM INSEGNAMENTO WHERE idinsegnamento=?;");
        stmt.setString(1, i.getCorsoREM());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void insertMateria(Materia m) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CORSO (TITOLOCORSO) VALUES (?)");
        stmt.setString(1, m.getTitoloCorso());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void removeMateria(MateriaREM i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM CORSO WHERE titolocorso=?;");
        stmt.setString(1, i.getMateriaREM());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static ArrayList<Prenotazione> MiaPrenotazione(String Utente) {
        Connection conn1 = null;
        String u = Utente;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE idUtente='"+Utente+"';");
            //   st.setString(1, Utente);
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"),
                        rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Data"));
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

}



