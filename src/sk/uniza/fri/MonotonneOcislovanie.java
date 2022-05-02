package sk.uniza.fri;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonotonneOcislovanie {
    private Graf graf;
    private final int pocetVrchlov;
    private final int pocetHran;
    private final int[] postupnost;
    private Logger loger;
    private ArrayList<Integer> zoznamNulovejMnoziny;

    public MonotonneOcislovanie (Graf graf, int pocetVrcholov, int pocetHran) {
        this.graf = graf;
        this.pocetVrchlov = pocetVrcholov;
        this.pocetHran = pocetHran;
        this.postupnost = new int[pocetVrcholov + 1];
        this.zoznamNulovejMnoziny = new ArrayList<>();
    }
    public void monotonneOcisluj() {
        int[] d = new int[this.pocetVrchlov + 1];
        for (int i = 1; i <= this.pocetVrchlov; i++) {
            for (int j = 0; j <= this.pocetVrchlov; j++) {
                if (this.graf.getZoznamHran().get(j).getVrcholDo() == i) {
                    d[i]++;
                }
            }
        }


        for (int i = 1; i <= this.pocetVrchlov; i++) {
            int vrcholSNulou;
            for (int j = 1; j <= this.pocetVrchlov; j++) {
                if ((d[j] == 0) && !this.nachadzaSaVPostupnosti(j) && this.nenachadzaSaVPostupnosti(j)) {
                    this.zoznamNulovejMnoziny.add(j);
                    this.loger.info(String.format("Pridavam vrchol %o do nulovej mnoziny", j));
                }
            }
            this.loger.log(Level.INFO, "Aktualizujem nulovu postupnost:");
            for (Integer integer : this.zoznamNulovejMnoziny) {
                this.loger.info(String.format("Vrchol %o ma nulovu postupnost %o", i, d[i]));

            }
            vrcholSNulou = this.zoznamNulovejMnoziny.get(0);
            this.loger.info(i + " vrchol v postupnosti je teraz vrchol " + vrcholSNulou);
            this.zoznamNulovejMnoziny.remove(0);
            this.postupnost[i] = vrcholSNulou;

            for (Hrana hrana : this.graf.getZoznamHran()){
                if (hrana.getVrcholZ() == vrcholSNulou) {
                    d[hrana.getVrcholDo()]--;
                    this.loger.info("Vrcholu " + hrana.getVrcholDo() + " znižujem značku d na " + d[hrana.getVrcholDo()]);
                }
            }
        }
        this.loger.info("Monotonne ocislovanie vrcholov: ");
        for (int i = 1; i <= this.pocetVrchlov; i++) {
            this.loger.info(" " + this.postupnost[i]);
        }
    }
    public boolean nachadzaSaVPostupnosti(int vrchol) {
        for (int i : this.zoznamNulovejMnoziny){
            if (i == vrchol) {
                return true;
            }
        }
        return false;
    }
    private boolean nenachadzaSaVPostupnosti(int vrchol) {
        for (int i : this.zoznamNulovejMnoziny){
            if (i == vrchol) {
                return false;
            }
        }
        return true;
    }
    public int[] getPostupnost() {
        return this.postupnost;
    }
}
