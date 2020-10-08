package Oblig2;

import Oblig2.DobbeltLenketListe;
import Oblig2.Liste;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String[] c = {"B", "C", "E", "F", "H", "I"};
        DobbeltLenketListe<String> liste2 = new DobbeltLenketListe<>(c);

        liste2.fjern("B");
        liste2.fjern("F");
        liste2.fjern("I");

        System.out.println(liste2.toString());
        System.out.println(liste2.omvendtString());



        /*
        String[] a = {"indeks 0", "indeks 1", "indeks 2", "indeks 3", "indeks 4 ", "indeks 5", "indeks 6"};

        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(a);



        Liste<String> subliste = liste.subliste(0,7);

        System.out.println(subliste);

        for(int i = 0; i<liste.antall(); ++i){
            System.out.println("Verdi: " + liste.hent(i));
        }

        String[] s = {};
        Liste<String> liste2 = new DobbeltLenketListe<>(s);

        System.out.println(liste.hent(2));

        System.out.println(liste.oppdater(6,a[2]));

        System.out.println(a);

        System. out .println(liste2. antall () + " " + liste2. tom ());

        System.out.println(liste.antall() + " " + liste.tom());

        System.out.println(liste.indeksTil("jeg"));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       /* String[] s1 = {"B"};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        System.out.println(l1.toString() );

       //OPPGAVE 2 A
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " "
                + l2.omvendtString() + " " + l3.omvendtString());

        //OPPGAVE 2 B
       DobbeltLenketListe<Integer> liste3 = new DobbeltLenketListe<>();
        System.out.println(liste3.toString() + " " + liste3.omvendtString());
        for (int i = 1; i <= 3; i++)
        {
            liste3.leggInn(i);
            System.out.println(liste3.toString() + " " + liste3.omvendtString());
        }
*/
    }
}
