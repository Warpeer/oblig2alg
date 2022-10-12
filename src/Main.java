import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String [] a = {"A", "B", "C", "D", "E", "A", "B", "C", "D", "E"};
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(a);

        System.out.println(liste.toString());
        System.out.println(liste.endringer() +" "+  liste.antall());
        liste.fjern("A");
        System.out.println(liste.endringer() +" "+ liste.antall());
        liste.nullstill();
        System.out.println(liste.endringer() +" "+ liste.antall());
    }
}
