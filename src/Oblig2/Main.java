package Oblig2;

import Oblig2.DobbeltLenketListe;
import Oblig2.Liste;

public class Main {
    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();

        String[] s = {null};
        Liste<String> liste2 = new DobbeltLenketListe<>(s);

        System. out .println(liste2. antall () + " " + liste2. tom ());

        System.out.print(liste.antall() + " " + liste.tom());
     }
}
