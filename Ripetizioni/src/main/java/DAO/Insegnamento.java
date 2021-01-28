package DAO;

public class Insegnamento {
    private String idInsegnamento;
    private String idDocenteC;
    private String idCorso;


    public Insegnamento(String idInsegnamento, String idCorso , String idDocenteC) {
        this.idInsegnamento = idInsegnamento;
        this.idDocenteC = idDocenteC;
        this.idCorso = idCorso;
    }


    public String getidInsegnamento() {
        return idInsegnamento;
    }

    public String getidDocenteC() {
        return idDocenteC;
    }

    public String getidCorso() {
        return idCorso; }

}