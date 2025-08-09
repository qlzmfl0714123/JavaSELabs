package mylab.library.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private String name;
    private final List<Book> books = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            System.out.println("도서가 추가되었습니다: " + book.getTitle());
        }
    }

    public Book findByTitle(String title) {
        if (title == null) return null;
        for (Book b : books) {
            if (b.getTitle() != null && b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        for (Book b : books) {
            if (b.getTitle() != null && b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return b;
            }
        }
        return null;
    }

    public List<Book> findByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor() != null && b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public Book findByISBN(String isbn) {
        if (isbn == null) return null;
        for (Book b : books) {
            if (isbn.equals(b.getIsbn())) return b;
        }
        return null;
    }

    public boolean checkOutBook(String isbn) {
        Book b = findByISBN(isbn);
        if (b == null) return false;
        return b.checkOut();
    }

    public boolean returnBook(String isbn) {
        Book b = findByISBN(isbn);
        if (b == null) return false;
        b.returnBook();
        return true;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) result.add(b);
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    public int getTotalBooks() {
        return books.size();
    }
    public int getAvailableBooksCount() {
        int count = 0;
        for (Book b : books) if (b.isAvailable()) count++;
        return count;
    }
    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}