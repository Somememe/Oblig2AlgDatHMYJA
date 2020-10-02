package Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

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
            throw new IndexOutOfBoundsException("Fra er for liten");
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
        //System.out.println("p-verdi: " + p.verdi);
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


