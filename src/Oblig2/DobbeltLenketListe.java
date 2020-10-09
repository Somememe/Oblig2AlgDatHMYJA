package Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.lang.UnsupportedOperationException;

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
        //sjekke om indekser fra og til er lovlige, om ikke skal det kastes unntak
        fraTilKontroll(antall,fra,til);
        int subAntall = 0;

        Liste<T> liste = new DobbeltLenketListe<>();

        //hente ut elementer og lage det som en ny lenket liste, returnere en liste
        for (int i = fra; i < til; i++) {
            liste.leggInn(hent(i));
            subAntall++;
        }

        return liste;
    }

    @Override
    public int antall() {
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
        endringer++;
        return true;               // vellykket innlegging
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke lov med null-verdier");
        indeksKontroll(indeks, true);
        //legge inn verdi på gitt indeks
        //finne node som er p, bruke finnNode, gir node på indeks indeks
        Node<T> nyNode = new Node<>(verdi);
        Node<T> p = finnNode(indeks);

        if (antall == 0) {
            hode = nyNode;
            hale = nyNode;
        }
        else if (indeks == 0) {
            hode = hode.forrige = new Node<>(verdi, null,hode);

        }
        else if (indeks == antall) {
            hale = hale.neste = new Node<>(verdi, hale,null);

        } else {
            p.forrige = p.forrige.neste = new Node<>(verdi, p.forrige, p);
        }
        endringer++;
        antall++;
    }

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
            for (int i = antall-1; i > indeks; i--){
                p = p.forrige;
            }
        } else{
            p = hode;
            for (int i = 1; i < indeks+1; i++) {
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
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }

        Node<T> r = hode, p = null;

        while (r != null) {
            if (r.verdi.equals(verdi)) break;
            p = r;
            r = r.neste;
        }

        if (r == null) {
            return false;
        } else if (r == hode) {
            hode = hode.neste;
        } else {
            p.neste = r.neste;
        }

        if (r == hale) {
            hale = p;
        } else{
            r.neste.forrige = p;
        }
        r.neste = null;
        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        T returverdi;

        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks kan ikke være mindre enn null");
        }

        else if (antall < 1) {
            throw new IndexOutOfBoundsException("Listen kan ikke være tom");
        }
        else if(antall-1 < indeks){
            throw new IndexOutOfBoundsException("Indeksen er for høy");
        }

       if (indeks == 0) {
           returverdi = hode.verdi;
           hode = hode.neste;
           if(hode != null) {
               hode.forrige = null;
           }

           if(antall == 1) {
               hale = null;
           }

           endringer++;
           antall --;
           return returverdi;
       }
       else {
        Node <T> p = finnNode(indeks - 1);
        Node <T> q = p.neste;
        returverdi = q.verdi;

           if (q == hale) hale = p; {
               p.neste = q.neste;
           }
           if(indeks < antall-1){
               p.neste.forrige = p;
           }else {
               hale.neste = null;
           }
       }

       endringer++;
       antall --;
       return returverdi;
    }

    @Override
    public void nullstill() {
        // 7:
        Node<T> p = hode;
        Node<T> q;

        // 1.metode:
        while (p != null) {
            q = p.neste;
            p.neste = null;
            p.forrige = null;
            p.verdi = null;
            p = q;
        }

        hode = null;
        hale = null;
        antall = 0;
        endringer++;

        //2.metode:
        /*
        while (p != null) {
            q = p.neste;
            fjern(0);
            p = q;
        }
        */
    }

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
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
                throw new ConcurrentModificationException("Antall iteratorendringer er ikke lik antall endringer!");
            } else if (hasNext() != true) {
                throw new NoSuchElementException("Listen er tom, det er ikke flere verdier igjen!");
            } else {
                fjernOK = true;

                T tempVerdi = denne.verdi;
                denne = denne.neste;

                return tempVerdi;
            }
        }

        @Override
        public void remove(){
            if(!fjernOK) {
                throw new IllegalStateException();
            }
            if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException();
            }
            fjernOK = false;

            if (antall == 1) {
                hode = null;
                hale = null;
            }
            else if (denne == null) {
                hale = hale.forrige; //hale må oppdateres
                hale.neste = null;
            }
            else if (denne.forrige == hode) {
                hode = hode.neste; //hode må oppdateres
                hode.forrige = null;
            }
            else {
                denne.forrige = denne.forrige.forrige;
                denne.forrige.neste = denne;
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        //For løkke som starter med første element i liste og stopper etter nest siste element
        for (int i = 0; i < liste.antall() - 1; ++i) {
            T current_min = liste.hent(i);
            int current_index = i;
            //Finne minste verdi i intervallet [i, liste.antall) ved hjelp av c.compare
            for (int j = i + 1; j < liste.antall(); ++j) {
                /*Sammenligner elementet på plass j med det foreløpig
                minste elementet og finner minste element i lista*/
                if (c.compare(liste.hent(j), current_min) < 0) {
                    current_min = liste.hent(j);
                    current_index = j;
                }
            }
            //Bytter plass på minste verdi og elementet på plass i.
            T temporary = liste.hent(i);
            liste.oppdater(i, current_min);
            liste.oppdater(current_index, temporary);
        }
    }
} // class DobbeltLenketListe


