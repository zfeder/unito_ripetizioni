package DAO;

public class Utente {
    private String nome;
    private String cognome;
    private String utente;
    private String password;
    private String ruolo;


    public Utente(String nome, String cognome, String utente, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.utente = utente;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
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
    public String toString() { return nome + " " + cognome + " " + utente + " " + password + " " + ruolo + " ";
    }

}
