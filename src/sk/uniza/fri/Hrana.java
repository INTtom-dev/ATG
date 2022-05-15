package sk.uniza.fri;

/**
 * 1. 4. 2021 - 16:39
 *
 * @author Kristian
 */
public class Hrana {

    private int vrcholZ;
    private int vrcholDo;
    private int cena;
    private Vrchol zaciatocnyVrchol;
    private Vrchol koncovyVrchol;

    private int kapacita;
    private int tok;

    public Hrana(int vrcholZ, int vrcholDo, int cena) {
        this.vrcholZ = vrcholZ;
        this.vrcholDo = vrcholDo;
        this.cena = cena;
    }
    public Hrana(int vrcholZ, int vrcholDo, int cena, int kapacita) {
        this.vrcholZ = vrcholZ;
        this.vrcholDo = vrcholDo;
        this.cena = cena;
        this.kapacita = kapacita;
    }
    public int getVrcholZ() {
        return this.vrcholZ;
    }

    public int getVrcholDo() {
        return this.vrcholDo;
    }

    public int getCena() {
        return this.cena;
    }
    public int getKapacita() {
        return this.kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }
    public int getTok() {
        return this.tok;
    }

    public void setTok(int tok) {
        this.tok = tok;
    }

}