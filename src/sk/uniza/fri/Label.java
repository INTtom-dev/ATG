package sk.uniza.fri;

import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * @author tmssa
 */
public class Label {


    private Graf graf;

    public Label(Graf graf) {
        this.graf = graf;
    }

    public int najdiVzdialenost(int vrcholZaciatok, int vrcholKoniec) {
        Logger log = Logger.getLogger(Label.class.getName());
        //nastavenie hodnot značiek T a X všetkých vrcholov
        for (int j = 0; j < this.graf.getPocetVrchlov(); j++) {
            this.graf.getZoznamVrcholov().get(j).setT(Integer.MAX_VALUE);
            this.graf.getZoznamVrcholov().get(j).setX(-1);
        }
        //vytvorenie epsilonu
        ArrayList<Vrchol> epsilon = new ArrayList<>();
        //vyhladanie vrcholu zaciatku
        Vrchol zaciatocnyVrchol = this.dajVrcholPodlaNazvu(vrcholZaciatok);
        //nastavenie hodnoty T v zaciatocnom vrchole
        zaciatocnyVrchol.setT(0);
        //pridanie zaciatocneho vrcholu do epsilonu
        epsilon.add(zaciatocnyVrchol);
        //ckylus prechadzania vrcholob
        while (!(epsilon.isEmpty())) {
            Vrchol riadiaciVrchol = null;
//            log.info("Epsilon: " + epsilon);
            int min = Integer.MAX_VALUE / 2;
            for (Vrchol vrchol : epsilon) {
                if (vrchol.getT() < min) {
                    min = vrchol.getT();
                    riadiaciVrchol = vrchol;
                }
            }
            epsilon.remove(riadiaciVrchol);
//            log.info("Riadiaci vrchol: " + riadiaciVrchol.getNazov());
//            log.info("Epsilon po mazani: " + epsilon);
            for (int i = 0; i < this.graf.getPocetHran(); i++) {
                Hrana hrana = this.graf.getZoznamHran().get(i);
                if (hrana.getVrcholZ() == riadiaciVrchol.getNazov()) {
                    log.info("Vytiahol som hranu: " + hrana.getVrcholZ() + "-" + hrana.getVrcholDo());
                    Vrchol vrcholDo = this.dajVrcholPodlaNazvu(hrana.getVrcholDo());
                    if (vrcholDo.getT() > riadiaciVrchol.getT() + hrana.getCena()) {
                        vrcholDo.setT(riadiaciVrchol.getT() + hrana.getCena());
                        vrcholDo.setX(riadiaciVrchol.getNazov());
//                        for (Vrchol vrchol1 : this.graf.getZoznamVrcholov()) {
//                            if (vrchol1 == vrcholDo) {
//                                this.graf.getZoznamVrcholov().remove(hrana.getVrcholDo());
//                                this.graf.getZoznamVrcholov().remove(hrana.getVrcholZ());
//                                this.graf.getZoznamVrcholov().remove(hrana.getCena());
//                            }
//                        }
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
//        log.info("Epsilon po cykle: " + epsilon);
//        log.info("----------------");
//        log.info("X po:");
//        log.info(this.graf.getZoznamVrcholov());
        Vrchol koncovyVrchol = this.dajVrcholPodlaNazvu(vrcholKoniec);
//        log.info("Vzdialenost vrcholov " + zaciatocnyVrchol + "->" + vrcholKoniec + " je " + koncovyVrchol.getT());
        ArrayList<Vrchol> cesta = new ArrayList<>();
        Vrchol aktualnyVrchol = koncovyVrchol;
        while (aktualnyVrchol != null) {
            cesta.add(aktualnyVrchol);
            if (aktualnyVrchol.getX() == -1) {
                break;
            }
            aktualnyVrchol = this.dajVrcholPodlaNazvu(aktualnyVrchol.getX());
        }
        log.info("Cesta vedie tadialto: ");
        for (int i = cesta.size() - 1; i >= 0; i--) {
            System.out.print(cesta.get(i).getNazov() + "->");
        }
        log.info("Cena je: " + koncovyVrchol.getT());
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