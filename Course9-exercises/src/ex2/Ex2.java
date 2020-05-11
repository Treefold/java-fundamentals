package ex2;

import java.util.Arrays;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        List<Persoana> pers = Arrays.asList(
                new Persoana("a", "a", 19),
                new Persoana("c", "c", 15),
                new Persoana("e", "e", 7),
                new Persoana("a", "b", 12),
                new Persoana("f", "f", 10),
                new Persoana("a", "d", 13));
        Persoana.printAll(pers);
        Persoana.printAllSortByPrenume(pers);
        Persoana.printAllStartWith(pers, 'a');
        Persoana.printAllSortByVarsta(pers);
        Persoana.printYoungest(pers);
    }
}
