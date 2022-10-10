

////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
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
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        if(a.length!=0){
            Node current = hode;
            int index=0;
            for(int i = 0; i<a.length; ++i){
                if(a[i]==null){
                    continue;
                }
                hode = new Node(a[i]);
                index=i;
                antall++;
                current = hode;
                break;
            }
            for(int i = index+1; i<a.length; ++i){
                if(a[i]==null){
                    continue;
                }
                current.neste = new Node(a[i]);
                antall++;
                Node temp = current;
                current = current.neste;
                current.forrige=temp;
            }
            hale=current;

        }else System.out.println("Tabellen er null!");
    }


    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(hode==null){
            return true;
        }else return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        if(verdi==null){
            return false;
        }
        Node inn = new Node(verdi);
        if(hale == null && hode == null && antall == 0){
            hale.neste = inn;
            hode.forrige = inn;
            inn.neste = hale;
            inn.forrige = hode;
            Node temp = inn;
        }else{
            hale.neste = inn;
            inn.neste = null;
            inn.forrige = hale;
            hale = inn;
        }
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
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
        StringBuilder ut = new StringBuilder("[");
        if(hode==null){
            ut.append("]");
            return ut.toString();
        }
        Node current = hode;
        for(int i = 0; i<antall; ++i){
            if(current==hale){
                ut.append(current.verdi);
            }else{
                ut.append(current.verdi).append(", ");
            }
            current = current.neste;
        }
        ut.append("]");
        return ut.toString();
    }
    public String omvendtString() {
        StringBuilder ut = new StringBuilder("[");
        if(hode==null){
            ut.append("]");
            return ut.toString();
        }
        Node current = hale;
        for(int i = antall; i>0; --i){
            if(current==hode){
                ut.append(current.verdi);
            }else{
                ut.append(current.verdi).append(", ");
            }
            current = current.forrige;
        }
        ut.append("]");
        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe



