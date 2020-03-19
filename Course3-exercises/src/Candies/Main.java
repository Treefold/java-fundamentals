package Candies;

public class Main {
    public static void main (String[] args) {
        Lindt l1 = new Lindt("a", "b", 1, 2, 3);
        Lindt l2 = new Lindt("a", "b", 1, 2, 5);
        Lindt l3 = new Lindt("c", "b", 1, 2, 3);
        Lindt l4 = new Lindt("a", "b", 1, 2, 3);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l1.equals(l1)); // true
        System.out.println(l2.equals(l1)); // false
        System.out.println(l3.equals(l1)); // false
        System.out.println(l4.equals(l1)); // true

        Milka m1 = new Milka("c", "d", 1, 3);
        Milka m2 = new Milka("c", "d", 1, 5);
        Milka m3 = new Milka("e", "d", 1, 3);
        Milka m4 = new Milka("c", "d", 1, 3);

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
        System.out.println(m1.equals(m1)); // true
        System.out.println(m2.equals(m1)); // false
        System.out.println(m3.equals(m1)); // false
        System.out.println(m4.equals(m1)); // true

        Heidi h1 = new Heidi("g", "h", 3);
        Heidi h2 = new Heidi("g", "h", 5);
        Heidi h3 = new Heidi("i", "h", 3);
        Heidi h4 = new Heidi("g", "h", 3);

        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
        System.out.println(h4);
        System.out.println(h1.equals(h1)); // true
        System.out.println(h2.equals(h1)); // false
        System.out.println(h3.equals(h1)); // false
        System.out.println(h4.equals(h1)); // true

        CandyBag bag = new CandyBag(8);
        bag.printInside();// empty
        bag.addCandy(l1); // added
        bag.addCandy(l2); // adds
        bag.addCandy(l3); // added
        bag.addCandy(l4); // already in bag
        bag.addCandy(m1); // added
        bag.addCandy(m2); // added
        bag.addCandy(m3); // added
        bag.addCandy(m4); // already in bag
        bag.addCandy(h1); // added
        bag.addCandy(h2); // added
        bag.addCandy(h4); // already in bag (even if the bag is full)
        bag.addCandy(h3); // bag full
        bag.printInside();// 8 elements
    }
}
