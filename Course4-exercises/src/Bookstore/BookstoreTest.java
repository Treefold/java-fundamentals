package Bookstore;
// a
public class BookstoreTest {
    public static void main (String[] arr) {
        Book book1 = new Book ("title1", "author1", "publisher1", 1);
        Book book2 = new Book ("title2", "author2", "publisher2", 2);
        Book book3 = new Book ("title3", "author3", "publisher3", 3);
        Book book4 = new Book ("title4", "author4", "publisher4", 4);

        System.out.println("book1.auth = " + book1.getAuthor());
        System.out.println("book2.title = " + book2.getTitle());
        System.out.println("book3.page = " + book3.getPageCount());
        System.out.println("book4.publisher = " + book4.getPublisher());

        book2.setTitle("title5");
        System.out.println("book2.title = " + book2.getTitle());
        book3.setPageCount(-10);
        System.out.println("book3.page = " + book3.getPageCount());

        book4 = null;
        System.out.println("book4=" + book4);
        book4 = new Book();
        System.out.println(book4);

        Book[] books = new Book[10];
        String str;
        for (int i = 0; i < 10; ++i) {
            str = Integer.toString(i%7 + 1);
            books[i] = new Book (str, str, str, i%7 + 1);
            System.out.println(books[i]);
        }

        System.out.println(BookstoreCheck.duplicates(books, books[0])); // 2
        System.out.println(BookstoreCheck.duplicates(books, books[6])); // 1
        System.out.println(BookstoreCheck.duplicates(books, book1)); // 0

        System.out.println(BookstoreCheck.cmp(books[0], books[4]));
        System.out.println(BookstoreCheck.cmp(books[3], books[2]));
    }
}
