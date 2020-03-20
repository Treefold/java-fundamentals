package Bookstore;

// c
public class BookstoreCheck {
    public static int duplicates (Book[] books, Book duplicate) {
        int s = 0;
        for (Book book: books) {
            if (duplicate.equals(book)) {s += 1;}
        }
        return s;
    }
    public static Book cmp (Book book1, Book book2) {
        return  (book1.getPageCount() > book2.getPageCount()) ? book1 : book2;
    }
}
