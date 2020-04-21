package Ex3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> arr = new ArrayList<>();
        String[] strs = {"ana", "are", "multe", "mere", "are", "ana"};
        for (String str : strs) {
            arr.add (new Student(str, 0));
        }
        for (Student s1 : arr) {
            System.out.println(s1);
            for (Student s2 : arr) {
                System.out.println("\t" + s2 + " " + s1.equals(s2) + " " + ((Object) s1).equals(s2));
                // the Student.equals(String s) is more specialized than Student.equals(Object o) so it has priority when called
                // the ((Object) Student).equals was overridden by Students.equals so that one is called
            }
        }
    }
}
