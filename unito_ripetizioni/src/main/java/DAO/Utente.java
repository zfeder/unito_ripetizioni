package DAO;

public class Utente {
    private String nome;
    private String cognome;
    private String id_utente;
    private String password;
    private String ruolo;


    public Utente(String nome, String cognome, String id_utente, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.id_utente = id_utente;
        this.password = password;
        this.ruolo = ruolo;
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getId_utente() { return id_utente; }

    public String getPassword() { return password; }

    public String getRuolo() { return ruolo; }

    @Override
    public String toString() {
        return nome + " " + cognome + " " + id_utente + " " + password + " " + ruolo + " "; }

}
