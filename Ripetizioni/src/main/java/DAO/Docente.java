package DAO;

public class Docente {
    private String nome;
    private String cognome;
    private String idDocente;


    public Docente(String nome, String cognome, String idDocente) {
        this.nome = nome;
        this.cognome = cognome;
        this.idDocente = idDocente;
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getidDocente() { return idDocente; }

}