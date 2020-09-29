package Oblig2;

import Oblig2.DobbeltLenketListe;
import Oblig2.Liste;

public class Main {
    public static void main(String[] args) {
        String[] a = {"indeks 0", "indeks 1", "indeks 2", "indeks 3", "indeks 4 ", "indeks 5", "indeks 6"};

        Liste<String> liste = new DobbeltLenketListe<>(a);

        String[] s = {};
        Liste<String> liste2 = new DobbeltLenketListe<>(s);

        System.out.println(liste.hent(2));

        System. out .println(liste2. antall () + " " + liste2. tom ());

        System.out.println(liste.antall() + " " + liste.tom());

        System.out.println(liste.indeksTil("jeg"));
     }
}
