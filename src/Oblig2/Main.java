package Oblig2;

import Oblig2.DobbeltLenketListe;
import Oblig2.Liste;

public class Main {
    public static void main(String[] args) {
        String[] a = {"Hei", "jeg"};

        Liste<String> liste = new DobbeltLenketListe<>(a);

        String[] s = {};
        Liste<String> liste2 = new DobbeltLenketListe<>(s);

        System. out .println(liste2. antall () + " " + liste2. tom ());

        System.out.println(liste.antall() + " " + liste.tom());

        System.out.println(liste.indeksTil("jeg"));
     }
}
