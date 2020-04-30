package optional;

import java.util.Optional;

public class Ex3 {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("java");
        System.out.println(optional);
        String s1 = optional.get();
        System.out.println(s1);

        Optional<String> opt = Optional.empty();
//        System.out.println(opt.get());
//        System.out.println(opt.isPresent());
        opt.ifPresent(System.out::println);

        Optional<Integer> i1 = Optional.of(1);
        i1.ifPresent(System.out::println);
    }
}
