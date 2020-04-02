package exceptii.ex3;

public class Ex2 {
    public static void main(String[] args) {
        System.out.println ("In Main");
        try (R1 r1 = new R1()) {
            // some logic
        }
        System.out.println ("Out Main");

    }
}
