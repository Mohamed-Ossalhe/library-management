package org.libraryManagment.controllers;
import org.libraryManagment.models.Book;

import java.sql.ResultSet;
import java.util.Scanner;



public class LibrarianController {
    private Scanner scanner = new Scanner(System.in);

    // display all books
    public void index() {
        try {
            String choice;
            do {
                booksList();
                System.out.printf("#   1. Add new Book                      %n");
                System.out.printf("#   2. Show a Book                       %n");
                System.out.printf("#   3. Edit a Book                       %n");
                System.out.printf("#   4. Delete a Book                     %n");
                System.out.printf("#   0. Main Menu                         %n");
                choice = this.scanner.nextLine();
                switch (choice) {
                    case "1" -> addBook();
                    case "2" -> showBook();
                    case "3" -> updateBook();
                    case "4" -> System.out.println("delete");
                }
            }while(!choice.equals("0"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Books List
    public void booksList() {
        try {
            System.out.printf("---------------------------------------------------------------------------------------------%n");
            System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", "Title", "Author", "ISBN", "Status");
            System.out.printf("---------------------------------------------------------------------------------------------%n");
            Book book = new Book();
            ResultSet resultBooks = book.index();
            while (resultBooks.next()) {
                System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", resultBooks.getString("title"), resultBooks.getString("author"), resultBooks.getString("ISBN"), resultBooks.getString("status"));
            }
            System.out.printf("---------------------------------------------------------------------------------------------%n");
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
            book.setTitle(this.scanner.nextLine());
            System.out.printf("# > Enter Book Author: ");
            book.setAuthor(this.scanner.nextLine());
            System.out.printf("# > Enter Book ISBN: ");
            book.setISBN_num(this.scanner.nextLine());
            System.out.printf("# > Enter Book Status: ");
            book.setStatus(this.scanner.nextLine());

            System.out.printf("---------------------------------------------%n");
            System.out.printf("             %13s          %n", book.store());
            System.out.printf("---------------------------------------------%n");
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // display book
    public void showBook() {
        try {
            Book book = new Book();
            String ISBN = null;
            do {
                System.out.printf("# > Enter Book ISBN [0 to exit]: ");
                ISBN = this.scanner.nextLine().strip();
                if (!ISBN.equals("0")) {
                    ResultSet resultBook = book.search(ISBN);
                    System.out.printf("---------------------------------------------------------------------------------------------%n");
                    System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", "Title", "Author", "ISBN", "Status");
                    System.out.printf("---------------------------------------------------------------------------------------------%n");
                    while (resultBook.next()) {
                        System.out.printf("# %-20s | %-20s | %-20s | %-20s #%n", resultBook.getString("title"), resultBook.getString("author"), resultBook.getString("ISBN"), resultBook.getString("status"));
                    }
                    System.out.printf("---------------------------------------------------------------------------------------------%n");
                }
            }while (!ISBN.equals("0"));
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
            String ISBN = this.scanner.nextLine();
            System.out.printf("# (Edit)> Enter Book Title: ");
            book.setTitle(this.scanner.nextLine());
            System.out.printf("# (Edit)> Enter Book Author: ");
            book.setAuthor(this.scanner.nextLine());
            System.out.printf("# (Edit)> Enter Book ISBN: ");
            book.setISBN_num(this.scanner.nextLine());
            System.out.printf("# (Edit)> Enter Book Status: ");
            book.setStatus(this.scanner.nextLine());

            System.out.printf("---------------------------------------------%n");
            System.out.printf("             %13s          %n", book.update(ISBN));
            System.out.printf("---------------------------------------------%n");
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
