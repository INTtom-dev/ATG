package sk.uniza.fri;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import sk.uniza.fri.cpm.AlgoritmusKonce;
import sk.uniza.fri.cpm.AlgoritmusZaciatky;
import sk.uniza.fri.cpm.MonotOcislovanie;

import java.io.File;
import java.io.IOException;

/**
 * @author Maroš
 */
public class GrafyATG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Graf g = Graf.nacitajGraf("src/sk/uniza/fri/CPM_mini.hrn");
        String cpmPriecinokTrvania = "src/sk/uniza/fri/kruskalDat/";
        String cpmSuborTrvania = cpmPriecinokTrvania + "CPM_mini.tim";
        System.out.println("Vrcholov:" + g.getPocetVrchlov());
        System.out.println("Hrán:" + g.getPocetHran());
        int vrcholZaciatok = 1;
        int vrcholKoniec = 3;//145;
        long zaciatok = System.currentTimeMillis();
//        new Zakladny(g).najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
//        new Zakladny(g).najdiVzdialenost2(vrcholZaciatok, vrcholKoniec);
//        new Label(g).najdiVzdialenost(vrcholZaciatok, vrcholKoniec);
        new KruskalNovy(g, true);
        new MonotOcislovanie(g);
        new AlgoritmusZaciatky(g, g.getPocetVrchlov(), g.nacitajTrvaniaCinnosti(cpmSuborTrvania), 0, 0);
        new AlgoritmusKonce(g, g.getPocetVrchlov(), , 0);
        System.out.println((System.currentTimeMillis() - zaciatok) / 1000.0);
    }

}
