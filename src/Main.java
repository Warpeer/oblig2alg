import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String [] a = {"A", "B", "D", "E"};
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(a);
        liste.leggInn(0, "C");
        System.out.println(liste.toString());
        System.out.println(liste.omvendtString());
    }
}
