package sk.uniza.fri.cpm;

import sk.uniza.fri.Graf;

import java.util.logging.Logger;

public class AlgoritmusKonce {
    private Graf graf;
    private final int pocetVrcholov;
    private int[] postupnost;
    private int[] ohodnotenia;
    private int trvanie;
    private int[] z;

    public AlgoritmusKonce(Graf graf, int pocetVrcholov, int[] postupnost, int[] ohodnotenia, int trvanie, int[] z) {
        this.graf = graf;
        this.pocetVrcholov = pocetVrcholov;
        this.postupnost = postupnost;
        this.ohodnotenia = ohodnotenia;
        this.trvanie = trvanie;
        this.z = z;
    }

    /**
     * Inicializacia k(v) a nasledne pouzity algoritmus
     */
    private void urcenieKoncov() {
        Logger log = Logger.getLogger(AlgoritmusKonce.class.getName());
        int[] k = new int[pocetVrcholov + 1];
        for (int i = 1; i <= pocetVrcholov; i++) {
            k[i] = this.trvanie;
        }
        int riadiaciVrchol = 0;

        for (int i = this.pocetVrcholov; i >= 0; i--) {
            riadiaciVrchol = this.postupnost[i];
            log.info("Vyberám riadiaci vrchol k(" + riadiaciVrchol + ") = " + k[riadiaciVrchol]);
            for (int j = 0; j < this.graf.getZoznamHran().size(); j++) {
                if (this.graf.getZoznamHran().get(j).getVrcholZ() == riadiaciVrchol) {
                    log.info("Vyberám vystupný vrchol riadiaceho vrcholu k(" + this.graf.getZoznamHran().get(j).getVrcholDo() + ") = " + k[this.graf.getZoznamHran().get(j).getVrcholDo()]);
                    if (k[riadiaciVrchol] > k[this.graf.getZoznamHran().get(j).getVrcholDo()] - this.ohodnotenia[this.graf.getZoznamHran().get(j).getVrcholDo()]) {
                        k[riadiaciVrchol] = k[this.graf.getZoznamHran().get(j).getVrcholDo()] - this.ohodnotenia[this.graf.getZoznamHran().get(j).getVrcholDo()];
                        log.info("Nastavujem k(" + riadiaciVrchol + ") = " + k[riadiaciVrchol]);
                    }
                }
            }
        }
        log.info("Určené nutné konce pre všetky vrcholy: ");
        for (int i = 1; i <= pocetVrcholov; i++) {
            log.info("k(" + i + ") = " + k[i]);
        }
        log.info("Časové rezervy pre každý vrchol:");
        for (int i = 1; i <= pocetVrcholov; i++) {
            log.info("R(" + i + ") = " + (k[i] - z[i] - this.postupnost[i]));
        }
        for (int i = 0; i <= pocetVrcholov; i++) {
            if (k[i] - z[i] - this.postupnost[i] == 0) {
                log.info("Kriticka cinnost: " + i);
            }
        }
    }
}