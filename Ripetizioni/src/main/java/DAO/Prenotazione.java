package DAO;

public class Prenotazione {
    private String idPrenotazione;
    private String idUtente;
    private String idDocente;
    private String idCorso;
    private String Orario;
    private String Giorno;
    private String Stato;


    public Prenotazione(String idPrenotazione, String idUtente, String idDocente, String idCorso, String Orario, String Giorno, String Stato) {
        this.idPrenotazione = idPrenotazione;
        this.idUtente = idUtente;
        this.idDocente = idDocente;
        this.idCorso = idCorso;
        this.Orario = Orario;
        this.Giorno = Giorno;
        this.Stato = Stato;


    }

    public String getIdPrenotazione() {
        return idPrenotazione;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public String getIdCorso() {
        return idCorso;
    }

    public String getOrario() {
        return Orario;
    }

    public String getGiorno() {
        return Giorno;
    }

    public String getStato() {
            return Stato;
    }



}
