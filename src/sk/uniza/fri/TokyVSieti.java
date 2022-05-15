package sk.uniza.fri;

import java.util.logging.Logger;

public class TokyVSieti {
    private int pocetVrcholov;
    private final int NEKONECNO;
    private int pocetHran;
    private int kapacita[][];
    private int tok[][];
    private int zaciatok;
    private int koniec;
    private int f[];
    private int oznac[];
    private int cesta[];
    private Graf g;

    private Logger log;

    public TokyVSieti(Graf g) {
        this.g = g;
        this.pocetVrcholov = g.getPocetVrchlov();
        this.pocetHran = g.getPocetHran();
        this.kapacita = new int[pocetVrcholov][pocetVrcholov];
        this.tok = new int[pocetVrcholov][pocetVrcholov];
        this.NEKONECNO = Integer.MAX_VALUE / 5;
        this.f = new int[pocetVrcholov + 2];
        this.log = Logger.getLogger(this.getClass().getName());
        this.read_input_file();
        this.vypis();
    }

    public int min(int x, int y) {
        return x < y ? x : y; //Math.min(x, y);
    }

    public void vloz(int x) {
        this.f[koniec] = x;
        this.koniec++;
        this.oznac[x] = 0;
    }

    public int vezmi() {
        int x = this.f[this.zaciatok];
        this.zaciatok++;
        oznac[x] = 1;
        return x;
    }

    public int hladanieZlepsujucejCesty(int start, int ciel) {
        int u;
        int v;
        int i;
        for (u = 0; u < pocetVrcholov; u++) {
            this.oznac[u] = -1;
        }
        this.zaciatok = this.koniec = 0;
        this.vloz(start);
        this.cesta[start] = -1;
        while (this.zaciatok != this.koniec) {
            u = this.vezmi();
            //hlada uzly oznacene -1, ak je rozdiel kapacita - tok z u do v kladny, vlozime v
            for (v = 0; v < pocetVrcholov; v++) {
                if (this.oznac[v] == -1 && this.kapacita[u][v] - this.tok[u][v] > 0) {
                    this.vloz(v);
                    this.cesta[v] = u;
                }
            }
        }
        //asi zly koniec return this.oznac[ciel] == -1;
        return this.oznac[ciel] == -1 ? -1 : this.cesta[ciel];
    }

    public int max_tok(int zdroj, int stok) {
        int i;
        int j;
        int u;
        int k;
        int s;
        //inicializacia prazdneho toku
        int max_tok = 0;
        for (i = 0; i < pocetVrcholov; i++) {
            for (j = 0; j < pocetVrcholov; j++) {
                this.tok[i][j] = 0;
            }
        }
        //Pokial existuje zlepsujuca cesta, zvysi tok pozdlz tejto cesty
        while (this.hladanieZlepsujucejCesty(zdroj, stok) == -1) {
            //urci hodnotu, o menej mozeme zvysit tok
            int prirastok = this.NEKONECNO;
            for (u = this.pocetVrcholov - 1; this.cesta[u] >= 0; u = this.cesta[u]) {
                this.tok[this.cesta[u]][u] += prirastok;
                this.tok[u][this.cesta[u]] -= prirastok;
            }
            System.out.printf("Prirastok: %d\n", prirastok);
            System.out.printf("Matica toku:\n");
            for (k = 0; k < pocetVrcholov; k++) {
                for (s = 0; s < pocetVrcholov; s++) {
                    System.out.printf("%3d%n ", this.tok[k][s]);
                }
                max_tok += prirastok;
            }
        }
        return max_tok;
    }
    public void read_input_file() {
        //vynulovanie matice kapacit
        for (int i = 0; i < pocetVrcholov; i++) {
            for (int j = 0; j < pocetVrcholov; j++) {
                this.kapacita[i][j] = 0;
            }
        }
        Hrana hrana;
        for (int i = 0; i < pocetHran; i++) {
            hrana = this.g.getZoznamHran().get(i);
        }
        //citanie kapacity hran
        for (int i = 0; i < pocetHran; i++) {
            this.kapacita[this.g.getZoznamHran().get(i).getVrcholZ()][this.g.getZoznamHran().get(i).getVrcholDo()] = this.g.getZoznamHran().get(i).getKapacita();
        }
    }

    public static void main(String[] args) {
        Graf g = Graf.nacitajGraf("src/sk/uniza/fri/TEST_mini.hrn");
        new TokyVSieti(g);
    }
    public void vypis() {
        int k;
        int s;
        System.out.printf("Matica kapacit:\n");
        for (k = 0; k < pocetVrcholov; k++) {
            for (s = 0; s < pocetVrcholov; s++) {
                System.out.printf("%3d%n ", this.kapacita[k][s]);
            }
        }
        System.out.printf("Maximalny tok: %d%n", this.max_tok(0, pocetVrcholov - 1));
    }
}