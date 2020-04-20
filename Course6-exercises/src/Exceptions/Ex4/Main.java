package Exceptions.Ex4;

public class Main {
    public static double div(double a, double b) {
        try {
            return a / b;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            // it executes despite the return in order to close used resources
            System.out.println("Done dividing");
        }
    }
    public static void main(String[] args) {
        System.out.println(div(10, 3));
        System.out.println(div(10, 0));
    }
}
