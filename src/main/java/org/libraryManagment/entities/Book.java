package org.libraryManagment.entities;

import org.libraryManagment.config.DB;

import java.sql.*;

public class Book extends DB {
    // properties
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
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO " + table + " (title, author, ISBN, status) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, getTitle());
            preparedStatement.setString(2, getAuthor());
            preparedStatement.setString(3, getISBN_num());
            preparedStatement.setString(4, getStatus());
            if (preparedStatement.execute()) {
                this.message = "Created Successfully";
            }
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.message;
    }

    // display specific book
    public ResultSet show(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE ISBN = ?");
            preparedStatement.setString(1, ISBN);
            this.book = preparedStatement.executeQuery();
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.book;
    }

    // search specific book
    public ResultSet search(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE ISBN LIKE ?");
            preparedStatement.setString(1, ISBN);
            this.book = preparedStatement.executeQuery();
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.book;
    }

    // update specific book
    public String update(int ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE " + table + " SET title = ?, author = ?, ISBN = ?, status = ? WHERE ISBN = ?");
            preparedStatement.setString(1, getTitle());
            preparedStatement.setString(2, getAuthor());
            preparedStatement.setString(3, getISBN_num());
            preparedStatement.setString(4, getStatus());
            preparedStatement.setInt(5, ISBN);
            if (preparedStatement.execute()) {
                this.message = "Updated Successfully";
            }
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.message;
    }

    // remove specific book
    public String destroy(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM " + table + " WHERE ISBN = ?");
            preparedStatement.setString(1, ISBN);
            if (preparedStatement.execute()) {
                this.message = "Deleted Successfully";
            }
        }catch (Exception exception) {
            System.out.println("Statement Exception: " + exception);
        }
        return this.message;
    }
}
