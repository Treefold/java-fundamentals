package intermediaryops;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Ex1 {

    public static void main(String[] args) {
        // limit, filter, skip, sorted, peek, map, flatmap

        IntStream is1 = IntStream.rangeClosed(1, 10).skip(6);
        is1.forEach(System.out::println);
        System.out.println();

        List<Integer> integers = Arrays.asList(1, 1, 30, 1, 1, 1, 5, 9, 1, 7, 1, 1, 10);
        integers.stream().distinct().forEach(System.out::println);
        System.out.println();
        integers.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
