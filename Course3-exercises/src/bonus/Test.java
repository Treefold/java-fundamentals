package bonus;

import java.util.Random;

public class Test {
    private static void simpleTest () {
        MyArrayList arr = new MyArrayList();
        System.out.println(arr);
        for (int i = 0; i < 3; ++i) {
            arr.add(i);
        }
        System.out.println(arr);
        for (int i = 0; i < 3; ++i) {
            System.out.println(arr.get(i));
        }
    }

    private static void scenaryTest () {
        MyArrayList arr = new MyArrayList(5);
        Random rand = new Random();
        int i, r;
        for (i = 0; i < 10; ++i) {
            arr.add(i);
        }
        System.out.println(arr);
        for (i = 0; i < 5; ++i) {
            r = rand.nextInt(1000) % 10;
            System.out.println("At index " + r + " is: " + arr.get(r));
        }
        for (i = 0; i < 5; ++i) {
            r = rand.nextInt(1000) % 10;
            System.out.println("Removing " + r);
            arr.remove(r);
            System.out.println(arr);
        }

    }

    public static void main (String[] args) {
//        simpleTest();
        scenaryTest();
    }
}

