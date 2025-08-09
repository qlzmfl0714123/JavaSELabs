package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("�߾� ������");

        addSampleBooks(library);

        System.out.println("===== " + library.getName() + " =====");
        printLibraryStatus(library);
        System.out.println();

        testFindBook(library);
        System.out.println();

        testCheckOut(library);
        System.out.println();

        testReturn(library);
        System.out.println();

        displayAvailableBooks(library);
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("�ڹ� ���α׷���", "���ڹ�", "978-89-01-12345-6", 2022));
        library.addBook(new Book("��ü������ ��ǰ� ����", "����ȣ", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("�ڹ��� ����", "���ü�", "978-89-01-14077-4", 2019));
    }

    private static void printLibraryStatus(Library library) {
        System.out.println("��ü ���� ��: " + library.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
    }

    private static void testFindBook(Library library) {
        System.out.println("===== ���� �˻� �׽�Ʈ =====");

        System.out.println("�������� �˻� ���:");
        Book byTitle = library.findByTitle("�ڹ��� ����");
        if (byTitle != null) {
            System.out.println(byTitle);
        } else {
            System.out.println("�˻� ����� �����ϴ�.");
        }
        System.out.println();

        System.out.println("���ڷ� �˻� ���:");
        List<Book> byAuthor = library.findByAuthor("Robert C. Martin");
        if (!byAuthor.isEmpty()) {
            System.out.println(byAuthor.get(0));
        } else {
            System.out.println("�˻� ����� �����ϴ�.");
        }
    }

    private static void testCheckOut(Library library) {
        System.out.println("===== ���� ���� �׽�Ʈ =====");
        boolean ok = library.checkOutBook("978-89-01-14077-4");
        if (ok) {
            System.out.println("���� ���� ����!");
            System.out.println("����� ���� ����:");
            System.out.println(library.findByISBN("978-89-01-14077-4"));
        } else {
            System.out.println("���� ���� ����(�̹� ���� �� �Ǵ� �������� ����).");
        }

        System.out.println();
        System.out.println("������ ���� ����:");
        printLibraryStatus(library);
    }

    private static void testReturn(Library library) {
        System.out.println("===== ���� �ݳ� �׽�Ʈ =====");
        boolean ok = library.returnBook("978-89-01-14077-4"); 
        if (ok) {
            System.out.println("���� �ݳ� ����!");
            System.out.println("�ݳ��� ���� ����:");
            System.out.println(library.findByISBN("978-89-01-14077-4"));
        } else {
            System.out.println("���� �ݳ� ����(�������� ����).");
        }

        System.out.println();
        System.out.println("������ ���� ����:");
        printLibraryStatus(library);
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("===== ���� ������ ���� ��� =====");
        for (Book b : library.getAvailableBooks()) {
            System.out.println(b);
            System.out.println("------------------------");
        }
    }
}