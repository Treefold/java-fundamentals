package Ex2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // Without comparator: "java.lang.ClassCastException: Ex2.Student cannot be cast to java.lang.Comparable" even if the equals is overridden
        // if there is a comparator <=> no need for equals in set
        // in the map equals, otherwise 2 identical Students will be seen as 2 completely different entities
        Set<Student> s = new TreeSet<>(new CmpStudents());
        Map <Integer, Student> map = new HashMap<Integer, Student>();
        String[] strs = {"ana", "are", "multe", "mere", "are", "ana"};
        for (String str : strs) {
            Student st = new Student(str, 0);
            System.out.println(st);
            // b
            try {
                if (s.contains(st)) {
                    throw new RuntimeException("Duplicate in set: " + st);
                } else {
                    s.add(st);
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            // c
            try {
                if (map.containsValue(st)) {
                    throw new RuntimeException("Duplicate in map: " + st);
                } else {
                    map.put(st.hashCode(), st);
                }
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
