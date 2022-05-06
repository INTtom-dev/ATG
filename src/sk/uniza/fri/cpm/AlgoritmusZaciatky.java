package sk.uniza.fri.cpm;

import sk.uniza.fri.Graf;

import java.util.logging.Logger;

public class AlgoritmusZaciatky {
    private Graf graf;
    private final int pocetVrcholov;
    private int[] postupnosti;
    private int[] ohodnotenia;
    private int trvanie;
    private int[] z;

    public AlgoritmusZaciatky(Graf graf, int pocetVrcholov, int[] postupnosti, int[] ohodnotenia){
        this.graf = graf;
        this.pocetVrcholov = pocetVrcholov;
        this.postupnosti = postupnosti;
        this.ohodnotenia = ohodnotenia;
        this.trvanie = 0;
        this.z = new int[this.pocetVrcholov + 1];
    }
    /*
    * inicializácoa z(v)
     */
    public void urciZaciatky() {
        Logger log = Logger.getLogger(AlgoritmusZaciatky.class.getName());


        for (int i = 1; i <= this.pocetVrcholov; i++) {
            this.z[i] = 0;
        }
        int riadiaciVrchol = 0;
        for (int i = 0; i <= this.pocetVrcholov; i++) {
            riadiaciVrchol = this.postupnosti[i];
            log.info("Vyberám riadiaci vrchol z(" + riadiaciVrchol + ") = " +this.z[riadiaciVrchol]);
            for (int j = 0; j <= this.graf.getZoznamHran().size(); j++) {
                if (this.graf.getZoznamHran().get(j).getVrcholZ() == riadiaciVrchol) {
                    log.info("Vyberam výstupný vrchol riadiaceho z(" + this.graf.getZoznamHran().get(j).getVrcholZ() + ") = " + this.z[this.graf.getZoznamHran().get(j).getVrcholDo()]);
                    if (this.z[this.graf.getZoznamHran().get(j).getVrcholDo()] < this.z[riadiaciVrchol] + this.ohodnotenia[riadiaciVrchol]) {
                        this.z[this.graf.getZoznamHran().get(j).getVrcholDo()] = this.z[riadiaciVrchol] + this.ohodnotenia[riadiaciVrchol];
                        Logger.getLogger(AlgoritmusZaciatky.class.getName()).info("Mením z(" + this.graf.getZoznamHran().get(j).getVrcholDo() + ") = " + this.z[this.graf.getZoznamHran().get(j).getVrcholDo()]);
                    }
                }
            }
        }
        log.info("Možné začiatkky každého vrcholu: ");
        for (int i = 1; i <= this.pocetVrcholov; i++) {
            log.info("z(" + i + ") = " + this.z[i]);
        }
        for (int i = 1; i <= this.pocetVrcholov; i++) {
            if (this.z[i] + this.ohodnotenia[i] > this.trvanie) {
                this.trvanie = this.z[i] + this.ohodnotenia[i];
            }
        }
        log.info("Trvanie algoritmu: " + this.trvanie);
    }
    public int getTrvanie() {
        return this.trvanie;
    }
    public int[] getZ() {
        return this.z;
    }
}
