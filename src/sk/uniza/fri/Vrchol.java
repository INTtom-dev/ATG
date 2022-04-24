package sk.uniza.fri;

import java.util.ArrayList;

/**
 * 1. 4. 2021 - 16:39
 *
 * @author Kristian
 */
public class Vrchol {


    private int nazov;
    private ArrayList<Hrana> vstupneHrany;
    private ArrayList<Hrana> vytupneHrany;
    private int t;
    private int x;
    private boolean jeOcislovany;

    public Vrchol(int cislo) {
        this.nazov = cislo;
        vstupneHrany = new ArrayList<>();
        vytupneHrany = new ArrayList<>();
    }

    public int getNazov() {
        return this.nazov;
    }

    public ArrayList<Hrana> getVstupneHrany() {
        return this.vstupneHrany;
    }

    public ArrayList<Hrana> getVytupneHrany() {
        return this.vytupneHrany;
    }
    public void pridajVystupnuHranu(Hrana h) {
        this.vytupneHrany.add(h);
    }
    public void pridajVstupnuHranu(Hrana h) {
        this.vstupneHrany.add(h);
    }

    public int getT() {
        return this.t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
