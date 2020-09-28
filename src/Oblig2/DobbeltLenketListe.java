package Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

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
            if(a.length != 0) {
                hode = hale = new Node<>(a[0], null, null);
            }

            for (int i = 0; i < a.length; i++) {
                if (a[i] != null) {
                   hale = hale.neste = new Node(a[i], hale, null);
                   //hale = hale.forrige = new Node()
                    antall++;
                }

            }
    }

    public Liste<T> subliste(int fra, int til){
        //hente ut elementer og lage det som en ny lenket liste
        //må kanskje lage en ny konstruktør
        //sette hode-peker, hale-peker og antall
        throw new UnsupportedOperationException();
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

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke lov med null-verdier");
        //indeksKontroll(indeks, true);

        if (indeks == 0) {
            hode = new Node<>(verdi, hode, hode);
            if (antall == 0) {
                hale = hode;
            }
        } else if(indeks == antall) {
            hale = hale.neste = new Node<>(verdi, null, null);
        } else {
            Node<T> p = hode;
            for (int i = 1; i < indeks; i++) {
                p = p.neste;
                p.neste = new Node<>(verdi, null, p.neste);
            }
            antall++;
            endringer++;
        }
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks){
        //loope fra start av listen til listen du kommer til
        //lønner seg å starte på slutten dersom du er over halvveis, og loope bakover
        if (indeks < 0 || indeks >= antall) {
            return null;
        }
        if (indeks < (antall/2)){

        }
        for (int i = 0; i < indeks; i++) {

        }
        return finnNode(indeks);
    }

    @Override
    public T hent(int indeks) {

        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        if(hode == null){
            return -1;
        }

        Node current = hode;

        for(int i = 0; i<antall; ++i){
            if(current.equals(new Node<>(verdi))){
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

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){

            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


