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
        Graf g = Graf.nacitajGraf("src/sk/uniza/fri/TEST_mini.hrn");
        System.out.println("Vrcholov:" + g.getPocetVrchlov());
        System.out.println("Hrán:" + g.getPocetHran());

        int vrcholZaciatok = 1;
        int vrcholKoniec = 3;//145;
        long zaciatok = System.currentTimeMillis();
//        new Zakladny(g).najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
//        new Zakladny(g).najdiVzdialenost2(vrcholZaciatok, vrcholKoniec);
//        new Label(g).najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
        //new Kruskalov(g, true);






        System.out.println((System.currentTimeMillis() - zaciatok) / 1000.0);
    }

}
