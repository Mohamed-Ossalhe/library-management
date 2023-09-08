package org.libraryManagment.controllers;
import org.libraryManagment.assets.Colors;
import org.libraryManagment.config.SessionData;
import org.libraryManagment.models.Librarian;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class LibrarianController {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Object> sessionData = SessionData.getInstance();
    public void index() {
        BookController bookController = new BookController();
        TransactionController transactionController = new TransactionController();
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        do {
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|          Library Management System        |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|                  Main Menu                |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("#      1. Main Menu                         #%n");
            System.out.printf("#      2. Transactions                      #%n");
            System.out.printf("#      3. View All Books                    #%n");
            System.out.printf("#      4. Borrowed Books                    #%n");
            System.out.printf("#      5. Search for Book                   #%n");
            System.out.printf("#      6. Statistics                        #%n");
            System.out.printf("#      0. Exit                              #%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("# Enter Number >>> ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> index();
                case "2" -> transactionController.index();
                case "3" -> bookController.index();
                case "0" -> {
                    System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
                    System.out.printf("|               You Logged Out!             |%n");
                    System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
                }
                default -> {
                    System.out.printf(Colors.RED + "---------------------------------------------%n");
                    System.out.printf("|           Please Choose a Number          |%n");
                    System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
                }
            }
        }while (!choice.equals("0"));
    }
    public void register() {
        try {
            Librarian librarian = new Librarian();
            System.out.printf("# > Enter Name: ");
            librarian.setName(this.scanner.nextLine());
            System.out.printf("# > Enter CIN: ");
            librarian.setCin(this.scanner.nextLine());
            System.out.printf("# > Enter Address: ");
            librarian.setAddress(this.scanner.nextLine());
            System.out.printf(Colors.GREEN + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", librarian.store());
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            librarian.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void login() {
        try {
            Librarian librarian = new Librarian();
            System.out.printf("# > username: ");
            librarian.setName(this.scanner.nextLine());
            System.out.printf("# > password: ");
            librarian.setPassword(this.scanner.nextLine());
            System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", librarian.show());
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            sessionData.put("user_id", librarian.getId());
            if (librarian.getId() != 0) {
                index();
            }
            librarian.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateLibrarian() {}
    public void deleteLibrarian() {}
}
