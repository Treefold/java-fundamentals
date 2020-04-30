package streamsource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Ex1 {

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 4, 7, 234, 46);

        // using iterations
        for (int i : intList) {
            if (i%2 == 0) {
                System.out.println(i);
            }
        }

        // using Streams
        Stream<Integer> s1 = intList.stream();
        s1.filter(i -> i%2 == 0).forEach(System.out::println);

        // empty()
        System.out.println(streamOf(new HashSet<>()).count());

        // builder()
//        Stream<String> streamWithBuilder = Stream.<String>builder().add("aaa");
    }

    public static Stream<String> streamOf (Set<String> set) {
        return set == null || set.isEmpty() ? Stream.empty() : set.stream();
    }
}
