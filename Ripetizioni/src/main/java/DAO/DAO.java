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
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"), rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"));
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

    public static ArrayList<Prenotazione> prenotazioneDB(String s) {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        String nome = s;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE idCorso='"+nome+"' AND idUtente='null' ;" );
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"), rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"));
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

    public static int insertDocente(String nome, String cognome) {
        Connection conn1 = null;
        ResultSet rs = null;
        int s = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement stmt = conn1.createStatement();
            stmt.executeUpdate("INSERT INTO DOCENTE (NOME, COGNOME) VALUES ('"+nome+"','"+cognome+"')", Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                s = rs.getInt(1);
            } else {

                // throw an exception from here
            }

            System.out.println(s);
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
        return s;
    }



    public static int insertInsegnamento(String idDocenteC, String idCorso) {
        Connection conn1 = null;
        ResultSet rs = null;
        int s = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement stmt = conn1.createStatement();
            stmt.executeUpdate("INSERT INTO INSEGNAMENTO (IDDOCENTE, IDCORSO) VALUES ('"+idDocenteC+"','"+idCorso+"')", Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                s = rs.getInt(1);
            } else {

                // throw an exception from here
            }

            Statement stmt2 = conn1.createStatement();
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Lunedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Lunedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Lunedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Lunedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Martedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Martedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Martedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Martedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Mercoledì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Mercoledì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Mercoledì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Mercoledì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Giovedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Giovedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Giovedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Giovedì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Venerdì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Venerdì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Venerdì')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Venerdì')");

            System.out.println(s);
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
        return s;
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
                        rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"));
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

    public static void prenota(String Docente, String Giorno, String Orario, String Corso, String Utente)  throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        System.out.println(Docente + Orario + Giorno + Corso + Utente + "Dentro prenota");
        PreparedStatement stmt = conn.prepareStatement("UPDATE Prenotazione SET idUtente = '" +Utente+ "' WHERE idDocente = '" + Docente + "' AND Orario = '" + Orario + "' AND Giorno = '" + Giorno + "' AND idCorso = '" + Corso + "';");
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }
}



