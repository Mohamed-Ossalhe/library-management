package org.libraryManagment.controllers;

import org.libraryManagment.assets.Colors;
import org.libraryManagment.config.SessionData;
import org.libraryManagment.models.Book;
import org.libraryManagment.models.Transaction;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;


public class TransactionController {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Object> sessionData = SessionData.getInstance();
    private final EmpruntController empruntController = new EmpruntController();
    private final static String table = "transactions";

    private int user_id;
    private int emprunt_id;
    private int book_id;
    private String start_date;
    private String return_date;

    // user_id setters & getters

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }

    // emprunt_id setters & getters
    public void setEmprunt_id(int emprunt_id) {
        this.emprunt_id = emprunt_id;
    }
    public int getEmprunt_id() {
        return emprunt_id;
    }

    // book_id setters & getters
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public int getBook_id() {
        return book_id;
    }

    // start date setters & getters
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    // return date setters & getters
    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public void index() {
        try {
            int choice = 0;
            do {
                transactionsList();
                System.out.printf("#   1. Borrow Book                       %n");
                System.out.printf("#   2. Edit Transaction                  %n");
                System.out.printf("#   0. Main Menu                         %n");
                System.out.printf("# > Enter a number: ");
                choice = this.scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addTransaction();
                    case 2 -> updateTransaction();
                    case 0 -> System.out.printf("");
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

    public void transactionsList() {
        try {
            Transaction transaction = new Transaction();
            System.out.printf(Colors.BLUE + "-------------------------------------------------------------------------------------------------------------------------------------------%n");
            System.out.printf(Colors.BOLD + "# %-20s | %-20s | %-20s | %-20s | %-20s | %-20s #%n" + Colors.RESET_FONT, "User", "Emprunt", "Emprunt CIN", "Book", "Start Date", "Return Date");
            System.out.printf(Colors.BLUE + "-------------------------------------------------------------------------------------------------------------------------------------------%n");
            ResultSet transactions = transaction.index();
            while (transactions.next()) {
                System.out.printf("# %-20s | %-20s | %-20s | %-20s | %-20s | %-20s #%n", transactions.getString("librarians.name"), transactions.getString("emprunts.name"), transactions.getString("emprunts.cin"), transactions.getString("books.title"), transactions.getString("transactions.start_date"), transactions.getString("transactions.return_date"));
            }
            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------%n" + Colors.RESET_COLOR);
            transaction.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTransaction() {
        try {
            Transaction transaction = new Transaction();
            Book book = new Book();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            Object user_id = sessionData.get("user_id");
            transaction.setUser_id((int)user_id);
            transaction.setEmprunt_id(this.empruntController.addEmprunt());

            System.out.printf("# > Enter Book ISBN: ");
            book.setISBN_num(this.scanner.nextLine().strip());
            book.search(book.getISBN_num());
            transaction.setBook_id(book.getId());

            // System.out.printf("# > Enter Borrow Date: ");
            transaction.setStart_date(dateFormat.format(new Date()));

            System.out.printf("# > Enter Days: ");
            calendar.add(Calendar.DATE, this.scanner.nextInt());
            this.scanner.nextLine();
            transaction.setReturn_date(dateFormat.format(calendar.getTime()));

            if (book.checkAvailable(book.getISBN_num()).next()) {
                System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
                System.out.printf("             %13s          %n", "Book not available!");
                System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            } else if (!transaction.show(transaction.getEmprunt_id()).next()) {
                System.out.printf(Colors.GREEN + "---------------------------------------------%n");
                System.out.printf("             %13s          %n", transaction.store());
                System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            }else {
                System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
                System.out.printf("             %13s          %n", "Already Exists!");
                System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTransaction() {
        try {
            System.out.println();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction() {
        try {
            Transaction transaction = new Transaction();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(new Date());
            transaction.setEmprunt_id(this.empruntController.addEmprunt());

            System.out.printf("# > Enter More Days: ");
            calendar.add(Calendar.DATE, this.scanner.nextInt());
            this.scanner.nextLine();
            transaction.setReturn_date(dateFormat.format(calendar.getTime()));

            // transaction.setReturn_date(this.scanner.nextLine());
            System.out.printf(Colors.GREEN + "---------------------------------------------%n");
            System.out.printf("             %13s          %n", transaction.update(transaction.getEmprunt_id()));
            System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction() {
        try {
            System.out.println();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
