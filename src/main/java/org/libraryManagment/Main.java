package org.libraryManagment;

import org.libraryManagment.assets.Colors;
import org.libraryManagment.controllers.LibrarianController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        LibrarianController librarianController = new LibrarianController();
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        do {
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|          Library Management System        |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|                   Welcome                 |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("#      1. Login                             #%n");
            System.out.printf("#      2. Register                          #%n");
            System.out.printf("#      0. Exit                              #%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("# Enter Number >>> ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> librarianController.login();
                case "2" -> librarianController.register();
                case "0" -> {
                    System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
                    System.out.printf("|                    Exit                   |%n");
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
}