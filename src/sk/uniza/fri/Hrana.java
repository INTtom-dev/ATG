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

    public Hrana(int vrcholZ, int vrcholDo, int cena) {
        this.vrcholZ = vrcholZ;
        this.vrcholDo = vrcholDo;
        this.cena = cena;
    }

    public Hrana(int vrcholZ, int vrcholDo) {
        this.vrcholZ = vrcholZ;
        this.vrcholDo = vrcholDo;
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

}