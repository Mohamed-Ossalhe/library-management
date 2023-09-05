package org.libraryManagment.entities;

public class Transaction {
    // properties
    private int user_id;
    private int emprunt_id;
    private int book_id;
    private String start_date;
    private String return_date;

    // constructor
    public Transaction() {
    }

    // user_id getters & setters
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    // emprunt_id getters & setters
    public void setEmprunt_id(int emprunt_id) {
        this.emprunt_id = emprunt_id;
    }

    public int getEmprunt_id() {
        return emprunt_id;
    }

    // book_id getters & setters
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    // start_date getters & setters
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_date() {
        return start_date;
    }

    // return_date getters & setters
    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getReturn_date() {
        return return_date;
    }
}
