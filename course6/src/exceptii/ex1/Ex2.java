package exceptii.ex1;

public class Ex2 {
    public static void main(String[] args) {
        System.out.println("In Main");
        try {
            m1();
            System.out.println("Done with m1");
        } catch (ArithmeticException e) {
            System.out.print(e);
            System.out.println(" in Main");
        }  catch (Exception e) {
            System.out.print(e);
            System.out.println(" in Main");
        } finally {
            System.out.println("Out Main");
        }
    }
    static int m1 () {
        int i = 5/0;
        System.out.println(i);
        return i;
    }
}
