package org.libraryManagment.controllers;

import org.libraryManagment.assets.Colors;
import org.libraryManagment.models.Book;

import java.util.Scanner;

public class StatisticsController {
    private Scanner scanner = new Scanner(System.in);

    public void Statistics() {
        Book book = new Book();
        try {
            int choice = 0;
            do {
                System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
                System.out.printf(Colors.BOLD + "# %-20s | %-20s | %-20s | %-20s #%n" + Colors.RESET_FONT, "Total Books", "Available Books", "Borrowed Books", "Lost Books");
                System.out.printf(Colors.BLUE + "---------------------------------------------------------------------------------------------%n");
                System.out.printf("# %-20d | %-20d | %-20d | %-20d #%n", book.showTotalBooks(), book.showAvailableBooks(), book.showBorrowedBooks(), book.showLostBooks());
                System.out.printf("---------------------------------------------------------------------------------------------%n"+ Colors.RESET_COLOR);
                System.out.printf("# > Enter [1] to exit: ");

                choice = this.scanner.nextInt();
                scanner.nextLine();

            }while (choice != 1);
            book.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
