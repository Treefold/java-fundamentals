package streamsource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Ex2 {

    public static void main(String[] args) {

        Stream<String> streamOnArray = Stream.of("aa", "aaaa", "bbb");
        streamOnArray.forEach(System.out::println);

        int[] ints = {1, 3, 5, 6, 8, 9, 3, 5};
        Set<Integer> set = new HashSet<>(Arrays.asList(33, 44, 55, 77, 44, 88, 33));
        Stream.of(set).forEach(System.out::println);
        Stream<int[]> streamOfIntsArray = Stream.of(ints);
        streamOfIntsArray.forEach(System.out::println);
        Arrays.stream(ints, 2, 5).forEach(System.out::println);

        // IntStream, DoubleStream, LongStream
        IntStream is1 = Arrays.stream(ints);
        System.out.println(is1.sum());
        IntStream is2 = Arrays.stream(ints);
        System.out.println(is2.count());

        LongStream ls1 = LongStream.range(6, 13); // [6, 13)
        ls1.forEach(System.out::println);
        LongStream ls2 = LongStream.rangeClosed(6, 13); // [6, 13]
        ls2.forEach(System.out::println);

        Random random = new Random();
        DoubleStream ds1 = random.doubles().limit(3);
        ds1.forEach(System.out::println);
        DoubleStream ds2 = random.doubles(3, 6, 13);
        ds2.forEach(System.out::println);

        Stream<String> stringStream = Stream.generate(() -> "element").limit(2);
        stringStream.forEach(System.out::println);

        // UnaryOperator
        Stream<Integer> integerStream = Stream.iterate(0, i -> i + 2).limit(10);
        integerStream.forEach(System.out::println);
    }
}
