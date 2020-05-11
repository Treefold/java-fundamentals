package ex2;

import java.util.List;

public class Persoana {
    private String nume;
    private String prenume;
    private int varsta;

    public Persoana(String nume, String prenume, int varsta) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta=" + varsta +
                '}';
    }

    static void printAll (List<Persoana> pers) {
        System.out.println("\nAll Elements:");
        pers.stream().forEach(System.out::println);
    }

    static void printAllSortByPrenume (List<Persoana> pers) {
        System.out.println("\nAll Elements Sorted by Prenume:");
        pers.stream().sorted((o1, o2) -> o1.prenume.compareToIgnoreCase(o2.prenume)).forEach(System.out::println);
    }

    static void printAllStartWith (List<Persoana> pers, char c) {
        System.out.println("\nAll Elements with the nume that start with " + c + ":");
        pers.stream().filter(p->Character.toLowerCase(p.nume.charAt(0))==Character.toLowerCase(c)).forEach(System.out::println);
    }

    static void printAllSortByVarsta (List<Persoana> pers) {
        System.out.println("\nAll Elements Sorted by Varsta:");
        pers.stream().sorted((o1, o2) -> Integer.compare(o1.varsta, o2.varsta)).forEach(System.out::println);
    }

    static void printYoungest (List<Persoana> pers) {
        System.out.print("\nThe Youngest Person: " + pers.stream().min((o1, o2) -> Integer.compare(o1.varsta, o2.varsta)).get());
    }
}
