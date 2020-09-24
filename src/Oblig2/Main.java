package Oblig2;

import Oblig2.DobbeltLenketListe;
import Oblig2.Liste;

public class Main {
    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();

        System.out.print(liste.antall() + " " + liste.tom());
     }
}
