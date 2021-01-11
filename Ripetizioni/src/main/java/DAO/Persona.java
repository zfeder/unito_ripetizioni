package DAO;

public class Persona {
    private String nome;
    private String cognome;
    private String matricola;


    public Persona(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " " + matricola;
    }
}
