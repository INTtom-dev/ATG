package sk.uniza.fri;

import java.util.ArrayList;


/**
 * @author tmssa
 */
public class Label {


    private Graf graf;

    public Label(Graf graf) {
        this.graf = graf;
    }

    public int najdiVzdialenost(int vrcholZaciatok, int vrcholKoniec) {


        for (int j = 0; j < this.graf.getPocetVrchlov(); j++) {
            this.graf.getZoznamVrcholov().get(j).setT(Integer.MAX_VALUE);
            this.graf.getZoznamVrcholov().get(j).setX(-1);
        }
        ArrayList<Vrchol> epsilon = new ArrayList<>();
        Vrchol zaciatocnyVrchol = this.dajVrcholPodlaNazvu(vrcholZaciatok);
        zaciatocnyVrchol.setT(0);
        epsilon.add(zaciatocnyVrchol);
        while (!(epsilon.isEmpty())) {
            Vrchol riadiaciVrchol = null;
//            System.out.println("Epsilon: " + epsilon);
            int min = Integer.MAX_VALUE;
            for (Vrchol vrchol : epsilon) {
                if (vrchol.getT() < min) {
                    min = vrchol.getT();
                    riadiaciVrchol = vrchol;
                }
            }
            epsilon.remove(riadiaciVrchol);
//            System.out.println("Riadiaci vrchol: " + riadiaciVrchol.getNazov());
//            System.out.println("Epsilon po mazani: " + epsilon);
            for (int i = 0; i < this.graf.getPocetHran(); i++) {
                Hrana hrana = this.graf.getZoznamHran().get(i);
                if (hrana.getVrcholZ() == riadiaciVrchol.getNazov()) {
                    System.out.println("Vytiahol som hranu: " + hrana.getVrcholZ() + "-" + hrana.getVrcholDo());
                    Vrchol vrcholDo = this.dajVrcholPodlaNazvu(hrana.getVrcholDo());
                    if (vrcholDo.getT() > riadiaciVrchol.getT() + hrana.getCena()) {
                        vrcholDo.setT(riadiaciVrchol.getT() + hrana.getCena());
                        vrcholDo.setX(riadiaciVrchol.getNazov());
                        for (Vrchol vrchol1 : this.graf.getZoznamVrcholov()) {
                            if (vrchol1 == vrcholDo) {
                                this.graf.getZoznamVrcholov().remove(hrana.get);
                                this.graf.getZoznamVrcholov().remove(hrana.getVrcholZ());
                                this.graf.getZoznamVrcholov().remove(hrana.getCena());
                            }
                        }
                        boolean obsahujeVrcholDo = false;
                        for (Vrchol vrchol : epsilon) {
                            if (vrchol == vrcholDo) {
                                obsahujeVrcholDo = true;
                                break;
                            }
                        }
                        if (!obsahujeVrcholDo) {
                            epsilon.add(vrcholDo);
                        }
                    }
                }
            }
        }
//            System.out.println("Epsilon po cykle: " + epsilon);
//        System.out.println("----------------");
//        System.out.println("X po:");
//        System.out.println(this.graf.getZoznamVrcholov());
        Vrchol koncovyVrchol = this.dajVrcholPodlaNazvu(vrcholKoniec);
//        System.out.println("Vzdialenost vrcholov " + zaciatocnyVrchol + "->" + vrcholKoniec + " je " + koncovyVrchol.getT());
        ArrayList<Vrchol> cesta = new ArrayList<>();
        Vrchol aktualnyVrchol = koncovyVrchol;
        while (aktualnyVrchol != null) {
            cesta.add(aktualnyVrchol);
            if (aktualnyVrchol.getX() == -1) {
                break;
            }
            aktualnyVrchol = this.dajVrcholPodlaNazvu(aktualnyVrchol.getX());
        }
        System.out.println("Cesta vedie tadialto: ");
        for (int i = cesta.size() - 1; i >= 0; i--) {
            System.out.print(cesta.get(i).getNazov() + "->");
        }
        System.out.println("Cena je: " + koncovyVrchol.getT());
        return koncovyVrchol.getT();
    }

    private Vrchol dajVrcholPodlaNazvu(int vrcholZaciatok) {
        for (Vrchol vrchol : this.graf.getZoznamVrcholov()) {
            if (vrchol.getNazov() == vrcholZaciatok) {
                return vrchol;
            }
        }
        return null;
    }
}