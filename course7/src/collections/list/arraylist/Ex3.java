package collections.list.arraylist;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex3 {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(2, 3, 66, 5, 99, 7, 9, 0, 10);
        System.out.println(ints);
        Object[] arrayOfInt = ints.toArray();
        System.out.println(Arrays.toString(arrayOfInt));

//        Collections.sort(ints); System.out.println(ints);
        System.out.println(Collections.binarySearch(ints, 99));
        System.out.println(Collections.binarySearch(ints, -1));
    }
}
