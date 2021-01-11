package DAO;

public class Utente {
    private String utente;
    private String password;
    private String ruolo;


    public Utente(String utente, String password, String ruolo) {
        this.utente = utente;
        this.password = password;
        this.ruolo = ruolo;
    }


    public String getUtente() {
        return utente;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String toString() {
        return utente + " " + password + " " + ruolo + " ";
    }

}
