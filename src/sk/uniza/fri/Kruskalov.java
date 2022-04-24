//package sk.uniza.fri;
//
//import java.util.ArrayList;
//
///**
// * 30. 3. 2022 - 13:05
// *
// * @author tmssa
// */
//public class Kruskalov {
//    private Graf graf;
//    private final int NEKONECNO = Integer.MAX_VALUE -  2;
//    private boolean zoradenie;
//    //private ArrayList<Hrany> kruskalovySpojenie;
//
////    public Kruskalov(Graf graf) {
////        this.graf = graf;
////        this.zoradenie = false;
////        //this.kruskalovySpojenie = new ArrayList<>();
////    }
//
////    public void zorad() {
////        if (zoradenie) {
////            return;
////        }
////        zoradenie = true;
////        Collections.sort(graf.getHrany());
////    }
////
////    public void kruskal() {
////        zorad();
////        Graf mnozina = new Graf();
////        for (int i = 0; i < graf.getPocetVrcholov(); i++) {
////            mnozina.pridajVrchol(new Vrchol(i));
////        }
////        for (Hrany hrana : graf.getHrany()) {
////            if (mnozina.getVrchol(hrana.getVrchol1()).getKapacita() != 0 && mnozina.getVrchol(hrana.getVrchol2()).getKapacita() != 0) {
////                mnozina.pridajHranu(hrana);
////            }
////        }
////        int pocetVrcholov = mnozina.getPocetVrcholov();
////        int[] pocetHran = new int[pocetVrcholov];
////        for (int i = 0; i < pocetVrcholov; i++) {
////            pocetHran[i] = mnozina.getVrchol(i).getPocetHran();
////        }
////        int[] pocetHranNaSpojenie = new int[pocetVrcholov];
////        for (int i = 0; i < pocetVrcholov; i++) {
////            pocetHranNaSpojenie[i] = 0;
////        }
//    private Graf novy;
//    int pocetVrcholov;
//    int pocetHran;
//    public Kruskalov(Graf graF, boolean zoradenie) {
//        this.graf = graF;
//        this.zoradenie = zoradenie;
//        this.pocetVrcholov = 0;
//        this.pocetHran = 0;
//        this.zoradenie();
//    }
//    public void zoradenie() {
//        Graf novyGraf = new Graf(new ArrayList<Vrchol>(), new ArrayList<Hrana>(), 0, 0);
//        while (!this.graf.getZoznamHran().isEmpty()) {
//            int index = 0;
//            if (this.zoradenie) {
//                int najmensiaCena = NEKONECNO;
//                for (int i = 0; i < this.graf.getZoznamHran().size(); i++) {
//                    if (this.graf.getZoznamHran().get(i).getCena() < najmensiaCena) {
//                        najmensiaCena = this.graf.getZoznamHran().get(i).getCena();
//                        index = i;
//                    }
//                }
//                novyGraf.getZoznamHran().add(this.graf.getZoznamHran().get(index));
//                System.out.println("\npridana hrana: "+ this.graf.getZoznamHran().get(index));
//                this.graf.getZoznamHran().remove(index);
//                System.out.println("\nodstranena hrana: " + this.graf.getZoznamHran().remove(index));
//            } else {
//                int najvacsiaCena = 0;
//                for (int i = this.graf.getZoznamHran().size(); i >= 0; i--) {
//                    if (this.graf.getZoznamHran().get(i).getCena() > najvacsiaCena) {
//                        najvacsiaCena = this.graf.getZoznamHran().get(i).getCena();
//                        index = i;
//                    }
//                }
//                novyGraf.getZoznamHran().add(this.graf.getZoznamHran().get(index));
//                System.out.println("\nnajvacsia pridana hrana: "+ this.graf.getZoznamHran().get(index));
//                this.graf.getZoznamHran().remove(index);
//                System.out.println("\nnajvacsia odstranena hrana: " + this.graf.getZoznamHran().get(index));
//            }
//        }
//        this.graf = novyGraf;
//        System.out.println("\n\n\n\n priradenie noveho grafu k existujucemu grafu" + this.graf);
//        int[] k = new int[this.graf.getPocetVrchlov() + 1];
//        for (int i = 0; i <= this.graf.getPocetVrchlov(); i++) {
//            k[i] = i;
//            System.out.println("\nk[" + i + "] = " + i);
//        }
//        Graf kostra = new Graf(new ArrayList<Vrchol>(), new ArrayList<Hrana>(), 0, 0);
//        for (int i = 0; i < this.graf.getPocetHran(); i++) {
//            if (k[this.graf.getZoznamHran().get(i).getVrcholZ()] != k[this.graf.getZoznamHran().get(i).getVrcholDo()]) {
//                kostra.getZoznamHran().add(this.graf.getZoznamHran().get(i));
//                int min = Math.min(k[this.graf.getZoznamHran().get(i).getVrcholZ()], k[this.graf.getZoznamHran().get(i).getVrcholDo()]);
//                int max = Math.max(k[this.graf.getZoznamHran().get(i).getVrcholZ()], k[this.graf.getZoznamHran().get(i).getVrcholDo()]);
//                for (int j = 1; j <= this.graf.getPocetVrchlov(); j++) {
//                    if (k[j] == max) {
//                        k[j] = min;
//                    }
//                }
//            }
//        }
//        System.out.println("Kostra cena: ");
//        int cena = 0;
//        for (Hrana hrana : kostra.getZoznamHran()) {
//            cena = cena + hrana.getCena();
//        }
//        System.out.println(cena);
//    }
//}
