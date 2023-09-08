package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction extends DB {
    // properties
    private int user_id;
    private int emprunt_id;
    private int book_id;
    private String start_date;
    private String return_date;
    private String table = "transactions";

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


    // display all transactions
    public ResultSet index() {
        ResultSet transactions = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT `librarians`.`name`, `emprunts`.`name`, `emprunts`.`cin`, `books`.`title`,`transactions`.`start_date`, `transactions`.`return_date` FROM `transactions` INNER JOIN `librarians` ON `librarians`.`id` = " + table + ".`user_id` INNER JOIN `emprunts` ON `emprunts`.`id` = " + table + ".`emprunt_id` INNER JOIN `books` ON `books`.`id` = " + table + ".`book_id`;");
            transactions = preparedStatement.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // store new transaction
    public String store() {
        String message = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO " + table + " (user_id, emprunt_id, book_id, start_date, return_date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, getUser_id());
            preparedStatement.setInt(2, getEmprunt_id());
            preparedStatement.setInt(3, getBook_id());
            preparedStatement.setString(4, getStart_date());
            preparedStatement.setString(5, getReturn_date());
            preparedStatement.executeUpdate();
            message = "Created Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // display a transaction
    public ResultSet show(int emprunt_id) {
        ResultSet transaction = null;
        try {
//            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT `emprunts`.`cin` FROM `transactions` INNER JOIN `emprunts` ON `emprunts`.`id` = `transactions`.`emprunt_id` WHERE `emprunts`.`cin` = ?;");
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE emprunt_id = ?");
            preparedStatement.setInt(1, emprunt_id);
            transaction = preparedStatement.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    // update transaction
    public String update(int emprunt_id) {
        String message = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE " + table + " SET return_date = ? WHERE emprunt_id = ?");
            preparedStatement.setString(1, getReturn_date());
            preparedStatement.setInt(2, emprunt_id);
            preparedStatement.executeUpdate();
            message = "Updated Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // delete a transaction
    public String destroy(int id) {
        String message = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM " + table + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            message = "Deleted Successfully";
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return message;
    }
}
