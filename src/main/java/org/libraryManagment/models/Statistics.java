package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Statistics extends DB {
    // total books
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
    // total members
}
