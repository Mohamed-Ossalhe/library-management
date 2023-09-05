package org.libraryManagment.entities;

public class Book {
    // properties
    private String title;
    private String author;
    private String ISBN_num;
    private String status;

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
}
