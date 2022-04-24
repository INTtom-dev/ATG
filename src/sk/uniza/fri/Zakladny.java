package sk.uniza.fri;

import java.util.ArrayList;

/**
 * @author Maroš
 */
public class Zakladny {

    private Graf graf;
    private final int INFINITY = 10000;

    public Zakladny(Graf graf) {
        this.graf = graf;
    }

    /**
     * Implementacia zakladneho algoritmu. Znacky x a t su ako polia pre jednotlive vrcholy
     *
     * @param vrcholZaciatok vrcholZaciatok
     * @param vrcholKoniec   vrcholKoniec
     */
    public int najdiVzdialenost(int vrcholZaciatok, int vrcholKoniec) {

        int[] t = new int[this.graf.getPocetVrchlov()];
        int[] x = new int[this.graf.getPocetVrchlov()];

        for (int i = 0; i < t.length; i++) {
            t[i] = INFINITY;
            x[i] = -1;
        }
        t[vrcholZaciatok] = 0;
        boolean zmena = true;
        while (zmena) {
            zmena = false;
            for (int i = 0; i < this.graf.getPocetHran(); i++) {
                Hrana hrana = this.graf.getZoznamHran().get(i);

                if (t[hrana.getVrcholDo()] > t[hrana.getVrcholZ()] + hrana.getCena()) {
                    t[hrana.getVrcholDo()] = t[hrana.getVrcholZ()] + hrana.getCena();
                    x[hrana.getVrcholDo()] = hrana.getVrcholZ();
                    zmena = true;
                }
            }
        }

        System.out.println("Cesta z vrchola " + vrcholZaciatok + " do vrchola " + vrcholKoniec + " je " + t[vrcholKoniec]);
        ArrayList<Integer> cesta = new ArrayList<>();

        for (int i = vrcholKoniec; i > -1; i = x[i]) {
            cesta.add(i);
        }

        System.out.println("Cesta vyzera nasledovne: ");
        for (int i = cesta.size() - 1; i >= 0; i--) {
            System.out.print(cesta.get(i) + "->");
        }
        System.out.println();
        return t[vrcholKoniec];
    }

    /**
     * Implementacia zakladneho algoritmu. Znacky x a t su ako atributy vrchola
     *
     * @param vrcholZaciatok
     * @param vrcholKoniec
     * @return
     */
    public int najdiVzdialenost2(int vrcholZaciatok, int vrcholKoniec) {

        for (Vrchol vrchol : this.graf.getZoznamVrcholov()) {
            vrchol.setX(-1);
            vrchol.setT(INFINITY);
        }
        ArrayList<Vrchol> epsilon = new ArrayList<>();
        Vrchol zaciatocnyVrchol = this.graf.getZoznamVrcholov().get(vrcholZaciatok);
        zaciatocnyVrchol.setT(0);
        epsilon.add(zaciatocnyVrchol);
        while (!epsilon.isEmpty()) {

            Vrchol riadiaciVrchol = epsilon.remove(0);
            System.out.println("Riadici vrchol: " + riadiaciVrchol.getNazov());

            ArrayList<Hrana> vystupneHrany = riadiaciVrchol.getVytupneHrany();

            for (Hrana hrana : vystupneHrany) {
                System.out.println("Hrana z :" + hrana.getVrcholZ() + " hrana do: " + hrana.getVrcholDo());
            }
        }


        boolean zmena = true;
        while (zmena) {
            zmena = false;
            for (int i = 0; i < this.graf.getPocetHran(); i++) {
                Hrana hrana = this.graf.getZoznamHran().get(i);
                Vrchol vrcholJ = this.graf.getZoznamVrcholov().get(hrana.getVrcholDo());
                Vrchol vrcholI = this.graf.getZoznamVrcholov().get(hrana.getVrcholZ());
                if (vrcholJ.getT() > vrcholI.getT() + hrana.getCena()) {
                    vrcholJ.setT(vrcholI.getT() + hrana.getCena());
                    vrcholJ.setX(vrcholI.getNazov());
                    zmena = true;
                }
            }
        }
        Vrchol koncovyVrchol = this.graf.getZoznamVrcholov().get(vrcholKoniec);
        System.out.println("Vzdialenost vrcholov " + vrcholZaciatok + "->" + vrcholKoniec + " je " + koncovyVrchol.getT());

        ArrayList<Integer> cesta = new ArrayList<>();
        for (int i = vrcholKoniec; i != -1; i = this.graf.getZoznamVrcholov().get(i).getX()) {
            cesta.add(i);
        }
        System.out.println("Cesta vedie tadialto: ");
        for (int i = cesta.size() - 1; i >= 0; i--) {
            System.out.print(cesta.get(i) + "->");
        }
        System.out.println();
        return koncovyVrchol.getT();
    }

}
