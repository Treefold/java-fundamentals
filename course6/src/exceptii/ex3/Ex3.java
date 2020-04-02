package exceptii.ex3;

import java.util.Arrays;
import java.util.SortedMap;

public class Ex3 {
    public static void main(String[] args) {
        System.out.println("In Main");
        try (R2 r1 = new R2 ("r1");
             R2 r2 = new R2 ("r2")) {
            throw new Exception ("exception in try block");
        } catch (Exception e) {
            System.out.println(e);
            // suppressed exception
            Throwable[] suppressedExceptions = e.getSuppressed();
            System.out.println (Arrays.toString(suppressedExceptions));
        } finally {
            System.out.println("Out Main");
        }
    }
}
