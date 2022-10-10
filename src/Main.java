import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] s1 = {null,"A",null,"B",null,"C", null, "D"};
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(s1);
        System.out.println(liste.toString());
        liste.leggInn("Ecekwndlkncd");
        liste.leggInn("F");
        System.out.println(liste.toString());

    }
}
