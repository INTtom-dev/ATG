package sk.uniza.fri;

import java.util.ArrayList;
import java.util.logging.Logger;

public class KruskalNovy {
    private Graf graf;
    private Graf kostra;

    private static final int NEKONECNO = Integer.MAX_VALUE - 2;

    private final boolean odNajmensieho;

    private Logger log;

    public KruskalNovy(Graf graf,boolean odNajmensieho) {
        this.kostra = new Graf(new ArrayList<Vrchol>(), new ArrayList<Hrana>(), 0, 0);
        this.graf = graf;

        this.odNajmensieho = odNajmensieho;
        this.log = Logger.getLogger(KruskalNovy.class.getName());
        this.najdenieKostry();
    }
    //zoradenie hran podla ceny
    private void zoradenieHran() {
        //vytvorennie noveho grafu
        Graf zoradenyGraf = new Graf(new ArrayList<Vrchol>(), new ArrayList<Hrana>(), 0, 0);
        while (!this.graf.getZoznamHran().isEmpty()/** && this.graf.getZoznamHran().size() == 1**/) {
            int indexSpracovavaneho = 0;
            if (odNajmensieho) {
                int najmensiaCena = NEKONECNO;
                for (int i = 0; i < this.graf.getZoznamHran().size(); i++) {
                    if (this.graf.getZoznamHran().get(i).getCena() < najmensiaCena) {
                        najmensiaCena = this.graf.getZoznamHran().get(i).getCena();
                        indexSpracovavaneho = i;
                    }
                }
                zoradenyGraf.getZoznamHran().add(this.graf.getZoznamHran().get(indexSpracovavaneho));
                this.graf.getZoznamHran().remove(indexSpracovavaneho);
            } else {
                int najvyssiaCena = 0;
                for (int i = this.graf.getZoznamHran().size() - 1; i >= 0; i--) {
                    if (this.graf.getZoznamHran().get(i).getCena() > najvyssiaCena) {
                        najvyssiaCena = this.graf.getZoznamHran().get(i).getCena();
                        indexSpracovavaneho = i;
                    }
                }
                zoradenyGraf.getZoznamHran().add(this.graf.getZoznamHran().get(indexSpracovavaneho));
                this.graf.getZoznamHran().remove(indexSpracovavaneho);
            }
        }
        this.graf = zoradenyGraf;
        this.log.info("Zoradenie hran podla ceny: ");
        for (int i = 0; i < this.graf.getZoznamHran().size(); i++) {
            this.log.info(this.graf.getZoznamHran().get(i).getVrcholZ() + "," + this.graf.getZoznamHran().get(i).getVrcholDo() + ", ceny" + this.graf.getZoznamHran().get(i).getCena());
        }
    }
    /**
     * inicializacia k
     */
    public void najdenieKostry() {
        int[] k = new int[this.graf.getZoznamVrcholov().size() + 1];
        for (int i = 1; i<= this.graf.getZoznamVrcholov().size(); i++) {
            k[i] = i;
        }
        for (int i = 0; i < this.graf.getZoznamHran().size(); i++) {
            if (k[this.graf.getZoznamHran().get(i).getVrcholZ()] != k[this.graf.getZoznamHran().get(i).getVrcholDo()]) {
                this.log.info("Spracovavanie hrany: " + this.graf.getZoznamHran().get(i).getVrcholZ() + "," + this.graf.getZoznamHran().get(i).getVrcholDo() + ", ceny" + this.graf.getZoznamHran().get(i).getCena());
                this.log.info("k(" + this.graf.getZoznamHran().get(i).getVrcholZ() + ") = " + k[this.graf.getZoznamHran().get(i).getVrcholZ()]);
                this.kostra.getZoznamHran().add(this.graf.getZoznamHran().get(i));

                int min = Math.min(k[this.graf.getZoznamHran().get(i).getVrcholZ()], k[this.graf.getZoznamHran().get(i).getVrcholDo()]);
                int max = Math.max(k[this.graf.getZoznamHran().get(i).getVrcholZ()], k[this.graf.getZoznamHran().get(i).getVrcholDo()]);

                for (int j = 1; j <= this.graf.getZoznamVrcholov().size(); j++) {
                    if (k[j] == max) {
                        this.log.info("Zmena k(" + j + ") = " +  + k[j] + "na k(" + j + ") = " + min);
                        k[j] = min;
                    }
                }
            }
        }
        this.log.info("Cena kostry: ");
        int cenaKostry = 0;
        for (Hrana hrana : this.kostra.getZoznamHran()) {
            cenaKostry += hrana.getCena();
        }
        this.log.info(String.valueOf(cenaKostry));
    }
}

