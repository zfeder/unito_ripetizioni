package DAO;

public class Utente {
    private String nome;
    private String cognome;
    private String nomeUtente;
    private String password;
    private String ruolo;


    public Utente(String nome, String cognome, String nomeutente, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getnomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }

}
