package Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) throws NullPointerException {

        Objects.requireNonNull(a, "Tabellen a er null");

        for (int i = 0; i < a.length; i++) {
            if(hode == null && a[i] != null){
                hode = hale = new Node<>(a[i], null, null);
                antall++;
            }
            else if (a[i] != null) {
                hale = hale.neste = new Node(a[i], hale, null);
                //hale = hale.forrige = new Node()
                antall++;
            }
        }
    }


    private static void fraTilKontroll (int antall, int fra, int til) throws IndexOutOfBoundsException, IllegalArgumentException{

        if (fra < 0 || til > antall) {
            throw new IndexOutOfBoundsException("Ugyldig fra eller til indeks");
        }
        if (fra > til) {
            throw new IllegalArgumentException("Ugyldig intervall");
        }
    }


    public Liste<T> subliste(int fra, int til){
        //hente ut elementer og lage det som en ny lenket liste, returnere en liste
        //må kanskje lage en ny konstruktør
        //sette hode-peker, hale-peker og antall
        //sjekke om indekser fra og til er lovlige, om ikke skal det kastes unntak
        fraTilKontroll(antall,fra,til);
        int subAntall = 0;

        Liste<T> liste = new DobbeltLenketListe<>();

        for (int i = fra; i < til; i++) {
            liste.leggInn(hent(i));
            subAntall++;
        }

        return liste;
    }

    @Override
    public int antall() {
        /*
        int antall = 0;

        while(hode.neste != null){
            antall++;
        }
        */
        return antall;
    }

    @Override
    public boolean tom() {
        boolean erTom = true;

        if(hode != null){
            erTom = false;
        }

        return erTom;
    }

    //Y
    @Override
    public boolean leggInn(T verdi) throws UnsupportedOperationException {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if (antall == 0)  {
            hode = new Node<>(verdi, null, null);  // tom liste
            hale = hode;
        }
        else {
            hale.neste = new Node<>(verdi, hale, null); // legges bakerst
            hale = hale.neste;
        }

        antall++;                  // en mer i listen
        return true;               // vellykket innlegging

        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke lov med null-verdier");
        indeksKontroll(indeks, true);
        //legge inn verdi på gitt indeks
        //finne node som er p, bruke finnNode, gir node på indeks indeks
        // q skal legges inn mellom p og r
        //q ny node, og r er p.neste
        Node<T> nyNode = new Node<>(verdi);
        Node<T> p = finnNode(indeks);

        if (antall == 0) {
            hode = nyNode;
            hale = nyNode;
        }
        else if (indeks == 0) {
            hode = hode.forrige = new Node<>(verdi, null,hode);

            /*hode = nyNode.neste;
            hode.forrige = nyNode;
            nyNode.forrige = null;
             */
        }
        else if (indeks == antall) {
            hale = hale.neste = new Node<>(verdi, hale,null);
           /* nyNode.forrige = hale;
            hale.neste = nyNode;
            nyNode.neste = null;
            */
        } else {
            //nyNode.neste = p;
            //nyNode.forrige = p.forrige;
            p.forrige = p.forrige.neste = new Node<>(verdi, p.forrige, p);
        }
        endringer++;
        antall++;

    }
        /*
        Node <T> p = finnNode(indeks);
        Node <T> q = new Node<>(verdi);
        Node <T> r = p.neste;
        if (p == null) {
            p = hale = q;
            p.neste = null;
            hale.neste = null;
        }
        if (indeks == 0) {
            p = hode;
            q.neste = hode;
            p.forrige = q;
            q.forrige = null;
            p = q;
        }
        if (indeks == antall) {
            p = hale;
            p.neste = q;
            q.forrige = p;
            q.neste = null;
            q = p;
        } else {
            for (int i = 1; i < indeks; i++) {
                q.neste = r;
                r.forrige = q;
                p.neste = q;
                q.forrige = p;
            }
        }
        antall++;
        endringer++;
        }

         */

/*
        if (indeks == 0) {
            p.neste = hode;
            hode = new Node<>(verdi, hode, hode);
           if (antall == 0) {
                p.forrige = q.neste;
           }
        } else if(indeks == antall) {
            p.forrige = r.forrige;
            hale = hode;
            hale = hale.neste = new Node<>(verdi, p.forrige, r.neste);
        } else {
            for (int i = 1; i < indeks; i++) {
                p = p.neste;
                p.neste = new Node<>(verdi, q.forrige, p.neste);
            }
            antall++;
            endringer++;
        }
 */



    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int indeks){
        //loope fra start av listen
        //lønner seg å starte på slutten dersom du er over halvveis, og loope bakover
        Node <T> p;

        if (indeks > (antall/2)){
            p = hale;
            for (int i = antall; i > indeks+1; i--){
                p = p.forrige;
            }
        } else{
            p = hode;
            for (int i = 0; i < indeks; i++) {
                p = p.neste;
            }
        }
        return p;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node <T> p = finnNode(indeks);
        return p.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(hode == null){
            return -1;
        }

        Node<T> current = hode;

        for(int i = 0; i<antall; ++i){
            if(current.verdi.equals(verdi)){
                return i;
            }
            current = current.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //metoden skal erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før
        //husk! sjekk indeks, null-verdier skal ikke legges inn, variabelen endringer skal økes
        Objects.requireNonNull(nyverdi, "Ikke lov med null-verdier");
        indeksKontroll(indeks, false);
        Node <T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;
        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) { throw new UnsupportedOperationException(); }

    @Override
    public T fjern(int indeks) { throw new UnsupportedOperationException(); }

    @Override
    public void nullstill() {
        /*
        Lag metoden void nullstill(). Den skal «tømme» listen og nulle alt slik at
        «søppeltømmeren» kan hente alt som ikke lenger brukes. Kod den på to måter
        og velg den som er mest effektiv (gjør tidsmålinger):
         */

        // 1.metode:
        // Får feil på test 7b og 7c.

        Node<T> p = hode;
        Node<T> q;
        Node<T> r = hale;

        while (p != null)
        {
            q = p.neste;
            p.verdi = null;
            p.neste = null;
            p.forrige = null;
            p = q;
        }

        /*Til slutt:
          Sett både hode og hale til null, antall til 0 og endringer økes.

          Metoden clear() i klassen LinkedList i Java.
        */
        p = null;
        r = null;
        antall = 0;
        endringer++;

        /*2.måte:
          Lag en løkke som inneholder metodekallet fjern(0) (den første
          noden fjernes) og som går inntil listen er tom.
         */

    }

    //Y
    @Override
    public String toString() throws UnsupportedOperationException{
        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom())
        {
            Node<T> p = hode;
            s.append(p.verdi);

            p = p.neste;

            while (p != null)  // tar med resten hvis det er noe mer
            {
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }

        s.append(']');

        return s.toString();

        //throw new UnsupportedOperationException();
    }

    //Y
    public String omvendtString() {
        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom())
        {
            Node<T> p = hale;
            s.append(p.verdi);

            p = p.forrige;

            while (p != null)  // tar med resten hvis det er noe mer
            {
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }

        s.append(']');

        return s.toString();

        //throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        //8b:
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        //8d;
        /*
        if (indeksKontroll(indeks, false)) {
           return new DobbeltLenketListeIterator(indeks);
        }
         */
        throw new UnsupportedOperationException();

    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            //8c:
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            // 8a:
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Iteratorendringer er ikke lik endringer");
            }

            if (hasNext() == false) {
                throw new NoSuchElementException("Ikke flere verdier igjen i listen!");
            }

            fjernOK = true;
            T midlertidig = denne.verdi;
            denne = denne.neste;

            return midlertidig;
        }

        @Override
        public void remove(){

            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        //For løkke som starter med første element i liste og stopper etter nest siste element
        for(int i = 0; i < liste.antall()-1; ++i){
            T current_maks = liste.hent(i);
            //Finne maks verdi i intervallet [i, liste.antall) ved hjelp av c.compare
            for(int j = i + 1; j < liste.antall(); ++j) {

            }
            //Bytter plass på maks verdi og elementet på plass i.
        }
    }

} // class DobbeltLenketListe


