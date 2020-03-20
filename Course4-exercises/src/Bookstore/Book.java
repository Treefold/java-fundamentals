package Bookstore;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

// a
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int pageCount;

    public Book(String title, String author, String publisher, int pageCount) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        // b
        if (pageCount < 1) {this.pageCount = 1;}
        else {this.pageCount = pageCount;}
    }

    // b
    public Book() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNew book:");
        System.out.print("\tTitle:  ");
        title = scanner.nextLine();
        System.out.print("\tAuthor:  ");
        author = scanner.nextLine();
        System.out.print("\tPublisher:  ");
        publisher = scanner.nextLine();
        while (true) {
            System.out.print("\tPageCount:  ");
            pageCount = scanner.nextInt();
            if (pageCount > 0) {break;}
            System.out.println("\tThe PageCount must be a positive integer!");
        }
        System.out.println("End of new book");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        // b
        if (pageCount < 1) {this.pageCount = 1;}
        else {this.pageCount = pageCount;}
    }

    // d
    @Override
    public String toString() {
        return "\nBOOK_TITLE:" + title.toUpperCase() +
                "\nBOOK_AUTHOR:" + author +
                "\nBOOK_PUBLISHER:" + publisher.toLowerCase() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher);
    }
}
