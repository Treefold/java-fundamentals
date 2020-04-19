package collections.list.linkedlist;

import java.util.LinkedList;

public class Ex2 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
//        using element and pop throws: Exception in thread "main" java.util.NoSuchElementException
//        System.out.println(list.element());
//        System.out.println(list.pop());
//        using peek and poll returns null when the first element doesn't exists
        System.out.println(list.peek());
        System.out.println(list.poll());

        list.add("a");
        list.offer("aa");
        list.offerFirst("A");

        System.out.println(list.element()); //returns head
        System.out.println(list.peek()); // returns head
        System.out.println(list); // deletes & returns head
        System.out.println(list.poll());
        System.out.println(list);
        System.out.println(list.poll());
        System.out.println(list);
    }
}
