package org.libraryManagment.controllers;

import org.libraryManagment.assets.Colors;
import org.libraryManagment.models.Book;

import java.sql.ResultSet;
import java.util.Scanner;

public class BookController {
    private Scanner scanner = new Scanner(System.in);

    // display all books
    public void index() {
        try {
            int choice;
            do {
                booksList();
                System.out.printf("#   1. Add new Book                      %n");
                System.out.printf("#   2. Show a Book                       %n");
                System.out.printf("#   3. Filter Books                      %n");
                System.out.printf("#   4. Edit a Book                       %n");
                System.out.printf("#   5. Delete a Book                     %n");
                System.out.printf("#   0. Main Menu                         %n");
                System.out.printf("# > Enter a number: ");
                choice = this.scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> showBook();
                    case 3 -> filterBooks();
                    case 4 -> updateBook();
                    case 5 -> deleteBook();
                    default -> {
                        System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
                        System.out.printf("|            Please Choose a Number         |%n");
                        System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
                    }
                }
            }while(choice != 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Books List
    public void booksList() {
        try {
            System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
            System.out.printf(Colors.BOLD + "# %-20s | %-20s | %-20s | %-20s #%n" + Colors.RESET_FONT, "Title", "Author", "ISBN", "Status");
            System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
            Book book = new Book();
            ResultSet resultBooks = book.index();
            while (resultBooks.next()) {
                System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", resultBooks.getString("title"), resultBooks.getString("author"), resultBooks.getString("ISBN"), resultBooks.getString("status"));
            }
            System.out.printf("---------------------------------------------------------------------------------------------%n" + Colors.RESET_COLOR);
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // add new book
    public void addBook() {
        try {
            Book book = new Book();
            System.out.printf("# > Enter Book Title: ");
            book.setTitle(this.scanner.nextLine().strip());
            System.out.printf("# > Enter Book Author: ");
            book.setAuthor(this.scanner.nextLine().strip());
            System.out.printf("# > Enter Book ISBN: ");
            book.setISBN_num(this.scanner.nextLine().strip());
            book.setStatus("available");

            System.out.printf(Colors.GREEN + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", book.store());
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // display book
    public Book showBook() {
        Book book = new Book();
        try {
            System.out.printf("# > Enter Book ISBN: ");
            String ISBN = this.scanner.nextLine();
            ResultSet resultBook = book.search(ISBN);
            // set book
            book.setTitle(resultBook.getString("title"));
            book.setAuthor(resultBook.getString("author"));
            book.setISBN_num(resultBook.getString("ISBN"));
            book.setStatus(resultBook.getString("status"));
            System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
            System.out.printf(Colors.BOLD + "# %-20s | %-20s | %-20s | %-20s #%n" + Colors.RESET_FONT, "Title", "Author", "ISBN", "Status");
            System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
//            while (resultBook.next()) {
                System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", resultBook.getString("title"), resultBook.getString("author"), resultBook.getString("ISBN"), resultBook.getString("status"));
//            }
            System.out.printf("---------------------------------------------------------------------------------------------%n"+ Colors.RESET_COLOR);
//            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // filter books by status
    public void filterBooks() {
        try {
            Book book = new Book();
            String status = null;
            do {
                System.out.printf("# > Enter Book Status [available, borrowed, lost][0 to exit]: ");
                status = this.scanner.nextLine().strip();
                if (!status.equals("0")) {
                    ResultSet resultBook = book.filter(status);
                    System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
                    System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", "Title", "Author", "ISBN", "Status");
                    System.out.printf("---------------------------------------------------------------------------------------------%n");
                    while (resultBook.next()) {
                        System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", resultBook.getString("title"), resultBook.getString("author"), resultBook.getString("ISBN"), resultBook.getString("status"));
                    }
                    System.out.printf("---------------------------------------------------------------------------------------------%n" + Colors.RESET_COLOR);
                }
            }while (!status.equals("0"));
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update book
    public void updateBook() {
        try {
            Book book = new Book();
            System.out.printf("# > Enter Book ISBN: ");
            String ISBN = this.scanner.nextLine().strip();
            System.out.printf("# (Edit)> Enter Book Title: ");
            book.setTitle(this.scanner.nextLine().strip());
            System.out.printf("# (Edit)> Enter Book Author: ");
            book.setAuthor(this.scanner.nextLine().strip());
            System.out.printf("# (Edit)> Enter Book ISBN: ");
            book.setISBN_num(this.scanner.nextLine().strip());
            System.out.printf("# (Edit)> Enter Book Status: ");
            book.setStatus(this.scanner.nextLine().strip());

            System.out.printf(Colors.GREEN + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", book.update(ISBN));
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete a book
    public void deleteBook() {
        try {
            Book book = new Book();
            System.out.printf("# > Enter Book ISBN: ");
            String ISBN = this.scanner.nextLine().strip();
            System.out.printf(Colors.GREEN + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", book.destroy(ISBN));
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
