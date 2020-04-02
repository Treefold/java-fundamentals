package exceptii.ex1;

public class Ex1 {
    public static void main(String[] args) throws Exception {
        System.out.println("In main");
        try {
            m1();
        } catch (NullPointerException e) {
            System.out.print(e);
            System.out.println(" in main");
        } finally {
            System.out.println("Out main");
        }
    }

    static void m1 () throws Exception {
        System.out.println("In m1");
        // m2();
        throw new Exception();
        //System.out.println("Out m1");
    }

    static void m2 () {
        System.out.println("In m2");
        m3();
        System.out.println("Out m2");
    }

    static void m3() {
        System.out.println("In m3");
        Object o = null;
        o.toString(); // null pointer exception
        System.out.println("Out m3");
    }
}
