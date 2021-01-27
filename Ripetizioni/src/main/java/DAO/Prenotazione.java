package DAO;

public class Prenotazione {
    private String idPrenotazione;
    private String idUtente;
    private String idDocente;
    private String idCorso;
    private String Orario;
    private String Data;


    public Prenotazione(String idPrenotazione, String idUtente, String idDocente, String idCorso, String Orario, String Data) {
        this.idPrenotazione = idPrenotazione;
        this.idUtente = idUtente;
        this.idDocente = idDocente;
        this.idCorso = idCorso;
        this.Orario = Orario;
        this.Data = Data;

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

    public String getData() {
        return Data;
    }


}
