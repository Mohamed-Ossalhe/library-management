package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.*;

public class Book extends DB {
    // properties
    private int id;
    private String title;
    private String author;
    private String ISBN_num;
    private String status;
    private final String table = "books";
    private String message = null;
    private ResultSet books = null;
    private ResultSet book = null;

    // constructor
    public Book() {
    }

    // id setters & getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //  title getters & setters
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // author getters & setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    // ISBN_num getters & setters
    public void setISBN_num(String ISBN_num) {
        this.ISBN_num = ISBN_num;
    }

    public String getISBN_num() {
        return ISBN_num;
    }

    // status getters & setters
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    // display list of books
    public ResultSet index() {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table);
            this.books = preparedStatement.executeQuery();
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.books;
    }

    // store new book
    public String store() {
        try {
            ResultSet book = this.search(getISBN_num());
            if (!book.next()) {
                setStatus("available");
                PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO " + table + " (title, author, ISBN, status) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, getTitle());
                preparedStatement.setString(2, getAuthor());
                preparedStatement.setString(3, getISBN_num());
                preparedStatement.setString(4, getStatus());
                preparedStatement.execute();
                this.message = "Created Successfully";
            }else {
                this.message = "ISBN already Exists!";
            }
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.message;
    }

    // display specific book
    public ResultSet filter(String status) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE status = ?");
            preparedStatement.setString(1, status);
            this.books = preparedStatement.executeQuery();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.books;
    }

    // search specific book
    public ResultSet search(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE ISBN LIKE ?");
            preparedStatement.setString(1, ISBN);
            this.book = preparedStatement.executeQuery();
            if (this.book.next()) {
                setId(this.book.getInt(1));
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.book;
    }

    // check if book is borrowed
    public ResultSet checkAvailable(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE ISBN = ? AND status = 'available'");
            preparedStatement.setString(1, ISBN);
            this.book = preparedStatement.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return this.book;
    }

    // update specific book
    public String update(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE " + table + " SET title = ?, author = ?, status = ? WHERE ISBN = ?");
            preparedStatement.setString(1, getTitle());
            preparedStatement.setString(2, getAuthor());
            preparedStatement.setString(3, getStatus());
            preparedStatement.setString(4, ISBN);
            preparedStatement.execute();
            this.message = "Updated Successfully";
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.message;
    }

    // remove specific book
    public String destroy(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM " + table + " WHERE ISBN = ?");
            preparedStatement.setString(1, ISBN);
            preparedStatement.execute();
            this.message = "Deleted Successfully";
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.message;
    }

    // show total books
    public int showTotalBooks() {
        int total = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT COUNT(*) FROM books");
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            total = data.getInt(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // total available books
    public int showAvailableBooks() {
        int total = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT COUNT(*) FROM books WHERE status = 'available'");
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            total = data.getInt(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    // total borrowed books
    public int showBorrowedBooks() {
        int total = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT COUNT(*) FROM books WHERE status = 'borrowed'");
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            total = data.getInt(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    // total lost books
    public int showLostBooks() {
        int total = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT COUNT(*) FROM books WHERE status = 'lost'");
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            total = data.getInt(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
