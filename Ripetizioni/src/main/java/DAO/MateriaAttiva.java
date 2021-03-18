package DAO;

public class MateriaAttiva {
    private String titoloCorso;
    private String corsoAttivo;


    public MateriaAttiva(String titoloCorso, String corsoAttivo) {

        this.titoloCorso = titoloCorso;
        this.corsoAttivo = corsoAttivo;
    }

    public String getTitoloCorso() {
        return titoloCorso;
    }
    public String getCorsoAttivo() { return corsoAttivo; }
}
