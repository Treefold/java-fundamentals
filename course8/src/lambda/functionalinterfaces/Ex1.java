package lambda.functionalinterfaces;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Ex1 {
    public static void main(String[] args) {
        Consumer<String> c1 = x -> System.out.println(x);
        c1.accept("abc");

        Supplier <LocalDate> ld1 = () -> LocalDate.now();
        System.out.println(ld1.get());

        Function<String, Integer> f1 = s -> s.length();
        System.out.println(f1.apply("123456789"));

        Predicate<Integer> p1 = x -> x % 2 == 0;
        System.out.println(p1.test(10));
    }
}
