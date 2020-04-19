package collections.list.linkedlist;

import java.util.LinkedList;

public class Ex1 {
    public static void main(String[] args) {
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("aa");
        list1.addLast("ddd");
        list1.addLast("ddd");
        list1.add(2, "bb");
        list1.add("aa");
        list1.addLast("ddd");
        System.out.println(list1);

//        list1.remove("aa");
//        System.out.println(list1);
//        list1.remove(1);
//        System.out.println(list1);
//        System.out.println(list1.removeLast());
//        System.out.println(list1);
//        System.out.println(list1.removeLast());
//        System.out.println(list1);

        list1.removeFirstOccurrence("ddd");
        list1.removeLastOccurrence("ddd");
        System.out.println(list1);
        System.out.println(list1.removeLastOccurrence("ffff"));
    }
}
