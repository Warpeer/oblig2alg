

////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

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

    }

    public DobbeltLenketListe(T[] a) {
        if(a.length!=0){
            Node<T> current = hode;
            int index=0;
            for(int i = 0; i<a.length; ++i){
                if(a[i]==null){
                    continue;
                }
                hode = new Node<T>(a[i]);
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
        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();
        return liste;
    }

    @Override
    public int antall() {
        return antall;
    }
    public int endringer() {
        return endringer;
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
        if(antall == 0){
            hode=inn;
            hale=inn;
        }else{
            hale.neste = inn;
            inn.forrige=hale;
            hale = inn;
        }
        antall++;
        endringer++;
        return true;
    }

    private Node<T> finnNode(int indeks) {

        Node<T> current;

        // Om indeksen er i første halvdel av listen starter vi fra hode.
        if (indeks < antall/2) {
            current = hode;
            for(int i=0; i<indeks; i++) {
                current = current.neste;
            }
        }
        // Om indeksen er i andre halvdel av listen starter vi fra halen.
        else {
            current = hale;
            for(int i=antall-1; i>indeks; i--) {
                current = current.forrige;
            }
        }
        return current;
    }

    @Override
    public void leggInn(int indeks, T verdi) {

        if (verdi == null){
            throw new NullPointerException("Verdi er null");
        }
        Node ny = new Node(verdi);
        if(indeks == 0 && antall!=0){
            indeksKontroll(indeks, true);
            Node temp = hode;
            hode = ny;
            hode.neste = temp;
            temp.forrige = hode;
        }else if(indeks == antall-1){
            indeksKontroll(indeks, true);
            Node temp = hale;
            hale = ny;
            hale.forrige=temp;
            temp.neste=hale;
        }else if(antall == 0){
            hode=ny;
            hale=ny;

        }else{
            indeksKontroll(indeks, true);
            Node forrigeNode = finnNode(indeks-1);
            Node nesteNode = finnNode(indeks);
            System.out.println(hale.verdi);
            forrigeNode.neste = ny;
            nesteNode.forrige=ny;
            ny.neste = nesteNode;
            ny.forrige = forrigeNode;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        int tom = indeksTil(verdi);
        if (tom == -1){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> current = finnNode(indeks);
        return current.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        /*if (verdi == null){
            throw new IllegalArgumentException("Verdi er null");
        }

        T sjekkVerdi = null;
        int indeks = -1;
        while(verdi!=sjekkVerdi) {
            indeks++;
            if(indeks == antall) {
                return -1;
            }
            sjekkVerdi = finnNode(indeks).verdi;
        }
        return indeks;*/
        if (verdi == null){
            throw new IllegalArgumentException("Verdi er null");
        }
        int indeks = -1;
        Node current = hode;
        for(int i = 0; i<antall; i++){
            if(current.verdi==verdi){
                indeks = i;
                break;
            }
            current = current.neste;
        }
        return indeks;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);

        if (nyverdi == null){
            throw new IllegalArgumentException("Nyverdi er tom");
        }
        else {
            T erstattetVerdi = finnNode(indeks).verdi;

            finnNode(indeks).verdi = nyverdi;
            endringer++;

            return erstattetVerdi;
        }
    }

    @Override
    public boolean fjern(T verdi) {
        Node current = hode;
        if(indeksTil(verdi)==-1){
            return false;
        }
        if(indeksTil(verdi)==0){
            if(antall!=1){
                hode = hode.neste;
                hode.forrige=null;
            }else{
                hale=null;
                hode=null;
            }
            antall--;
            endringer++;
            return true;
        }else if(indeksTil(verdi)==antall-1){
            hale = hale.forrige;
            hale.neste=null;

            antall--;
            endringer++;
            return true;
        }else {
            for(int i = 0; i<antall; i++){
                if(current.verdi==verdi){
                    Node forrige = current.forrige;
                    Node neste = current.neste;
                    forrige.neste = neste;
                    neste.forrige = forrige;

                    current = null;

                    antall--;
                    endringer++;
                    return true;
                }
                current = current.neste;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        T slett = finnNode(indeks).verdi;
        if(indeks == 0){
            if(antall!=1){
                hode = hode.neste;
                hode.forrige=null;
            }else{
                hale=null;
                hode=null;
            }
            antall--;
            endringer++;

            return slett;
        }else if(indeks == antall-1){
            hale = hale.forrige;
            hale.neste = null;
            antall--;
            endringer++;

            return slett;
        }else {
            Node forrige = finnNode(indeks-1);
            Node neste = finnNode(indeks+1);
            forrige.neste = neste;
            neste.forrige=forrige;
            antall--;
            endringer++;

            return slett;
        }
    }

    @Override
    public void nullstill() {
        Node<T> current = hode.neste;
        for (int i=0; i<antall-1; i++){
            Node<T> temp = current.neste;

            current.forrige = current.neste = null;
            current.verdi = null;

            current = temp;
        }
        hode = null;

        endringer++;
        antall = 0;
    }
    //Metoden nullstillv2 er treigere enn metoden nullstill.
    /*
    public void nullstillv2(){
        for(int i = 0; i<antall; ++i){
            fjern(i);
        }
        endringer = endringer-antall+1;
        antall = 0;
    }*/

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
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
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
            denne = finnNode(indeks);// p starter på indeksen i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer!=endringer){
                throw new ConcurrentModificationException("Endringer samsvarer ikke med iteratorendringer.");
            }
            if(hasNext()){
                fjernOK=true;
            }else throw new NoSuchElementException("Finnes ikke noen neste.");
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
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



