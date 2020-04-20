package Exceptions.Ex3;

public class Main{
    public static void outOfM () {
        long[] l = new long[Integer.MAX_VALUE];
    }
    public static void stackOver(String s) {
        stackOver(s + " ");
        stackOver(s + " ");
    }
    public static void main(String[] args) {
        try {
            outOfM();
        } catch (OutOfMemoryError e) {
            System.out.println(e);
        }
        System.out.println("No problem");
        /*
            in c++:
                static allocated => compile error
                dynamic allocated => I wouldn't receive the requested space (I can deal with this error)
         */
        try {
            stackOver("StackOverflowError");
        } catch (StackOverflowError e) {
            System.out.println(e);
        }
        System.out.println("No problem");
        /*
            in c++:
                As far as I know, you need a watchdog to restart the program
                (behaviour unknown)
         */
    }
}
//