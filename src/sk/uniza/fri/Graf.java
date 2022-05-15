package sk.uniza.fri;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maroš
 */
public class Graf {

    private ArrayList<Vrchol> zoznamVrcholov; // smerníky na prvu hranu pre dany vrchol pomocou arraylistu
    private ArrayList<Hrana> zoznamHran; // zoznam hran (arraylist) pomocou objektu
    private int[][] hrany; // zoznam hran pomocou matice H[i][0] je zaciatocny vrchol, H[i][1] je koncovy vrchol a H[i][3] je cena hrany
    private int[] smerniky; // smerniky na prvu hranu pre dany vrchol
    private int pocetVrchlov;
    private int pocetHran;

    @Contract("_ -> new")
    public static @NotNull Graf nacitajGraf(String filename) {
        Input input = new Input();
        input.readData(filename);
        return new Graf(input.getZoznamVrcholov(), input.getZoznamHran(), input.getPocetVrcholov(), input.getPocetHran());
    }
    public static Graf nacitajGrafToky(String filename) {
        Input input = new Input();
        input.readDataToky(filename);
        return new Graf(input.getZoznamVrcholov(), input.getZoznamHran(), input.getPocetVrcholov(), input.getPocetHran());
    }
    public Graf(ArrayList<Vrchol> zoznamVrcholov, ArrayList<Hrana> zoznamHran, int pocetVrchlov, int pocetHran) {
        this.zoznamVrcholov = zoznamVrcholov;
        this.zoznamHran = zoznamHran;
        this.pocetVrchlov = pocetVrchlov;
        this.pocetHran = pocetHran;
    }
    public Graf(int[][] hrany, int[] smerniky, int pocetVrchlov, int pocetHran) {
        this.hrany = hrany;
        this.smerniky = smerniky;
        this.pocetVrchlov = pocetVrchlov;
        this.pocetHran = pocetHran;
    }
    public ArrayList<Vrchol> getZoznamVrcholov() {
        return this.zoznamVrcholov;
    }

    public ArrayList<Hrana> getZoznamHran() {
        return this.zoznamHran;
    }

    public int[] getSmerniky() {
        return this.smerniky;
    }

    public int getPocetVrchlov() {
        return this.pocetVrchlov;
    }

    public int getPocetHran() {
        return this.pocetHran;
    }
    public void addHranyToVrcholGraph() {
        for (Hrana hrana: this.zoznamHran) {
            this.zoznamVrcholov.get(hrana.getVrcholZ()).pridajVystupnuHranu(hrana);
            this.zoznamVrcholov.get(hrana.getVrcholDo()).pridajVystupnuHranu(hrana);
            this.zoznamVrcholov.get(hrana.getVrcholZ()).pridajVstupnuHranu(hrana);
            this.zoznamVrcholov.get(hrana.getVrcholDo()).pridajVstupnuHranu(hrana);
        }
    }
    public void addHranyToVrcholDiGraph() {
        for (Hrana hrana: this.zoznamHran) {
            this.zoznamVrcholov.get(hrana.getVrcholZ()).pridajVystupnuHranu(hrana);
            this.zoznamVrcholov.get(hrana.getVrcholDo()).pridajVstupnuHranu(hrana);
        }
    }

    public int[] nacitajTrvaniaCinnosti(String fileName) throws IOException {
        int[] trvania = new int[this.pocetVrchlov];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new java.io.FileReader(fileName));
            String line;
            for (int i = 1; i < this.pocetVrchlov; i++) {
                line = br.readLine();
                int trvanie = Integer.parseInt(line);
                trvania[i] = trvanie;
                this.zoznamVrcholov.get(i).setTrvanie(trvanie);
            }
        } catch (IOException e){
            System.out.println("Nepodarilo sa otvorit subor");
        } finally {
            Objects.requireNonNull(br).close();
        }
        return trvania;
    }
}
