package exceptii.ex3;

public class Ex1 {
    public static void main(String[] args) {
        R1 r1 = null;
        try {
            r1 = new R1();
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            if (r1 != null) {
                try {
                    r1.close();
                } catch (RuntimeException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
