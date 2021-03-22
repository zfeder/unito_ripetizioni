package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DocenteRemove {
    private String idDocenteREM;


    public DocenteRemove(String idDocenteREM) {

        this.idDocenteREM = idDocenteREM;
    }

    public String getidDocenteRemove() { return idDocenteREM; }

}



