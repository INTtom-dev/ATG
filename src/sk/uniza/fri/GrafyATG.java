package sk.uniza.fri;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.File;

/**
 * @author Maroš
 */
public class GrafyATG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dir = "." + File.separator + "ATG_DAT" + File.separator + "ShortestPath" + File.separator;
        String file = dir + "TEST_mini.hrn";
        Graf g = Graf.nacitajGraf("src/sk/uniza/fri/TEST_mini.hrn");
        System.out.println("Vrcholov:" + g.getPocetVrchlov());
        System.out.println("Hrán:" + g.getPocetHran());

        int vrcholZaciatok = 1;
        int vrcholKoniec = 4;//145;
        long zaciatok = System.currentTimeMillis();
        //Kostra kotra = new Kostra();
        //Zakladny zakladny = new Zakladny(g);
        //java.util.Scanner scanner = new java.util.Scanner(System.in);

        //int vrcholZaciatok = scanner.nextInt();
        //int vrcholKoniec = scanner.nextInt();
        //zakladny.najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
//
//        zakladny.najdiVzdialenost2(vrcholZ, vrcholDo);
        new Label(g).najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
        //new Kruskalov(g);
        //new Kostra();
        //new Kruskalov(g, true);
        System.out.println((System.currentTimeMillis() - zaciatok) / 1000.0);
    }
}
