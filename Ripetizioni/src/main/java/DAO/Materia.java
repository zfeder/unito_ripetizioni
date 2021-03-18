package DAO;

import java.sql.*;

public class Materia {
    private String titoloCorso;


    public Materia(String titoloCorso) {

        this.titoloCorso = titoloCorso;
    }

    public String getTitoloCorso() {
        return titoloCorso;
    }
}

