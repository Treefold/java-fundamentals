package Ex1;

import java.util.Set;
import java.util.TreeSet;

public class Ex1 {

    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        String[] strs = {"ana", "are", "multe", "mere", "are", "ana"};
        for (String str : strs) {
            try {
                if (s.contains(str)) {
                    throw new RuntimeException("Duplicate " + str);
                } else {
                    s.add(str);
                }
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
