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
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE nomeUtente!='null';");
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
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE JOIN DOCENTE ON PRENOTAZIONE.idDocente = DOCENTE.idDocente WHERE idUtente!='null';");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"), rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"), rs.getString("Stato"), rs.getString("nome"), rs.getString("cognome"));
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

    public static ArrayList<Insegnamento> InsegnamentoAttivoDB() {
        Connection conn1 = null;
        ArrayList<Insegnamento> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM INSEGNAMENTO WHERE INSEGNAMENTOATTIVO ='True'");
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
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE JOIN DOCENTE ON PRENOTAZIONE.idDocente = DOCENTE.idDocente WHERE idCorso='"+nome+"' AND idUtente='null' AND Stato='Libera' ORDER BY CASE WHEN Giorno = 'Lunedì' THEN 1 WHEN Giorno = 'Martedì' THEN 2 WHEN Giorno = 'Mercoledì' THEN 3 WHEN Giorno = 'Giovedì' THEN 4 WHEN Giorno = 'Venerdì' THEN 5 END, orario");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"), rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"), rs.getString("Stato"), rs.getString("nome"), rs.getString("cognome"));
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


    //CONTROLLO UTENTI
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
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE nomeUtente!='null';");
            String s = "Amministratore";
            while (rs.next() && t == false) {
                String utente = rs.getString("NOMEUTENTE");
                String password = rs.getString("PASSWORD");
                String ruolo = rs.getString("RUOLO");
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

    //CONTROLLO AMMINISTRATORE
    public static boolean checkAdmin(String futente) {
        Connection conn1 = null;
        boolean b = false;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE");
            String s = "Amministratore";
            while (rs.next() && b == false) {
                String utente = rs.getString("NOMEUTENTE");
                String ruolo = rs.getString("RUOLO");
                if(utente.equals(futente) && ruolo.equals(s)) {
                    b = true;
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
        return b;
    }

    public static boolean checkMateria(String corso) {
        Connection conn1 = null;
        boolean b = false;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO WHERE TITOLOCORSO = '"+corso+"' AND CORSOATTIVO = 'True'");
            String s = "True";
            while (rs.next() && b == false) {
                String corsoc = rs.getString("TITOLOCORSO");
                String attivo = rs.getString("CORSOATTIVO");
                if(corsoc.equals(corso) && attivo.equals(s)) {
                    System.out.println("corso  già esistente");
                    b = true;
                }
                else
                    System.out.println("corso non esistente");
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
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO WHERE corsoAttivo = 'True'");
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

    public static ArrayList<Materia> Materia2DB(String idDocenteC) {
        Connection conn1 = null;
        ArrayList<Materia> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT titoloCorso FROM INSEGNAMENTO RIGHT JOIN CORSO ON insegnamento.idCorso = corso.titoloCorso WHERE corsoAttivo = 'True' AND (idDocente IS NULL OR idDocente !='"+idDocenteC+"')");
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


    public static ArrayList<Docente> DocenteDB() {
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE WHERE DOCENTEATTIVO = 'True'");
            while (rs.next()) {
                Docente d = new Docente(rs.getString("nome"), rs.getString("cognome"), rs.getString("idDocente"));
                out.add(d);
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
            stmt.executeUpdate("INSERT INTO DOCENTE (NOME, COGNOME, DOCENTEATTIVO) VALUES ('"+nome+"','"+cognome+"','True')", Statement.RETURN_GENERATED_KEYS);
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
            stmt.executeUpdate("INSERT INTO INSEGNAMENTO (IDDOCENTE, IDCORSO, INSEGNAMENTOATTIVO) VALUES ('"+idDocenteC+"','"+idCorso+"', 'True')", Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                s = rs.getInt(1);
            } else {

                // throw an exception from here
            }

            Statement stmt2 = conn1.createStatement();
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Lunedi' ', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Lunedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Lunedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Lunedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Martedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Martedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Martedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Martedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Mercoledi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Mercoledi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Mercoledi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Mercoledi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Giovedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Giovedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Giovedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Giovedi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '15-16', 'Venerdi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '16-17', 'Venerdi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '17-18', 'Venerdi', 'Libera')");
            stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocenteC+"', '"+idCorso+"', '18-19', 'Venerdi', 'Libera')");

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

    public static void removeDocente(DocenteRemove i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("DELETE FROM PRENOTAZIONE WHERE idUtente='null' AND idDocente = '"+i.getidDocenteRemove()+"'");
        stmt2.executeUpdate("UPDATE DOCENTE SET DOCENTEATTIVO = 'False' WHERE idDocente= '"+i.getidDocenteRemove()+"'");
        conn.close();

    }

    public static void removeCalendario(String idDocente, String Materia) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("DELETE FROM PRENOTAZIONE WHERE idUtente='null' AND idDocente = '"+idDocente+"' AND idCorso = '"+Materia+"'");
        conn.close();

    }


    public static void removeCorso(InsegnamentoRemove i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(("UPDATE INSEGNAMENTO SET INSEGNAMENTOATTIVO = 'False' WHERE idInsegnamento=?"));
        stmt.setString(1, i.getInsegnamentoRemove());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void insertMateria(Materia m) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        PreparedStatement st = conn.prepareStatement("SELECT corsoAttivo FROM CORSO WHERE TITOLOCORSO=?;");
        st.setString(1, m.getTitoloCorso());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            st.close();
            PreparedStatement stmt = conn.prepareStatement(("UPDATE CORSO SET CORSOATTIVO = 'True' WHERE titolocorso=?"));
            stmt.setString(1, m.getTitoloCorso());
            stmt.executeUpdate();
            stmt.close();
        } else  {
            st.close();
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO CORSO (TITOLOCORSO, CORSOATTIVO) VALUES (?,'True') ");
            stmt1.setString(1, m.getTitoloCorso());
            stmt1.executeUpdate();
          }
        conn.close();
    }

    public static void removeMateria(MateriaRemove i) throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("UPDATE CORSO SET CORSOATTIVO = 'False' WHERE titolocorso=?");
        stmt.setString(1, i.getMateriaRemove());
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
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE JOIN DOCENTE ON PRENOTAZIONE.idDocente = DOCENTE.idDocente WHERE idUtente='"+Utente+"';");
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getString("idPrenotazione"), rs.getString("idUtente"), rs.getString("idDocente"),
                        rs.getString("idCorso"), rs.getString("Orario"), rs.getString("Giorno"), rs.getString("Stato"), rs.getString("nome"), rs.getString("Cognome"));
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
        PreparedStatement stmt = conn.prepareStatement("UPDATE Prenotazione SET idUtente = '" +Utente+ "', stato = 'Prenotata' WHERE idDocente = '" + Docente + "' AND Orario = '" + Orario + "' AND Giorno = '" + Giorno + "' AND idCorso = '" + Corso + "';");
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    public static void disdici(String idPrenotazione, String idUtente, String idDocente, String idCorso, String orario1, String giorno1, String utente2)  throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Prenotazione SET stato = 'Disdetta' WHERE idDocente = '" + idDocente + "' AND Orario = '" + orario1 + "' AND Giorno = '" + giorno1 + "' AND idUtente = '" + utente2 + "' AND idCorso = '"+ idCorso+"' AND idPrenotazione = '"+ idPrenotazione +"';");
        stmt.executeUpdate();
        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocente+"', '"+idCorso+"', '"+orario1+"', '"+giorno1+"', 'Libera')");
        stmt.close();
        conn.close();

    }

    public static void svolta(String idPrenotazione, String idUtente, String idDocente, String idCorso, String orario1, String giorno1, String utente2)  throws SQLException {
        Connection conn = DriverManager.getConnection(url1, user, password);
        Statement st = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Prenotazione SET stato = 'Svolta' WHERE idDocente = '" + idDocente + "' AND Orario = '" + orario1 + "' AND Giorno = '" + giorno1 + "' AND idUtente = '" + utente2 + "' AND idCorso = '"+ idCorso+"' AND idPrenotazione = '"+ idPrenotazione +"';");
        stmt.executeUpdate();
        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("INSERT INTO PRENOTAZIONE (IDUTENTE, IDDOCENTE, IDCORSO, ORARIO, GIORNO, STATO) VALUES ('null', '"+idDocente+"', '"+idCorso+"', '"+orario1+"', '"+giorno1+"', 'Libera')");
        stmt.close();
        conn.close();

    }
}



