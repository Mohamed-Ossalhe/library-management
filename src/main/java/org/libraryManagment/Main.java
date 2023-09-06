package org.libraryManagment;

import org.libraryManagment.controllers.LibrarianController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        LibrarianController librarianController = new LibrarianController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|          Library Management System        |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|                  Main Menu                |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("#      1. Main Menu                         #%n");
            System.out.printf("#      2. Borrow a Book                     #%n");
            System.out.printf("#      3. View All Books                    #%n");
            System.out.printf("#      4. Borrowed Books                    #%n");
            System.out.printf("#      5. Search for Book                   #%n");
            System.out.printf("#      6. Statistics                        #%n");
            System.out.printf("#      0. Exit                              #%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("# Enter Number >>> ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> mainMenu();
                case 2 -> System.out.printf("Hi %n");
                case 3 -> librarianController.index();
                default -> {
                    System.out.printf("---------------------------------------------%n");
                    System.out.printf("|               You Logged Out!             |%n");
                    System.out.printf("---------------------------------------------%n");
                }
            }
        }while (choice != 0);
    }
}