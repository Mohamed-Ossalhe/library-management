package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Librarian extends DB {
    // properties
    private int id;
    private String name;
    private String cin;
    private String address;
    private String password;

    private String table = "librarians";

    // constructor
    public Librarian() {
    }

    // name getters & setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // name getters & setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // cin getters & setters
    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    // address getters & setters
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    // address getters & setters
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // display all librarians
    public void index() {}

    // store new librarian
    public String store() {
        String message = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO " + table + " (name, password, cin, address) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, getName());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, getCin());
            preparedStatement.setString(4, getAddress());
            preparedStatement.executeUpdate();
            ResultSet id = preparedStatement.getGeneratedKeys();
            id.next();
            setId(id.getInt(1));
            message = "Created Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // display a librarian
    public String show() {
        String message = null;
        try {
            String query = "SELECT * FROM " + table + " WHERE name = ? AND password = ?;";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, getName());
            preparedStatement.setString(2, getPassword());
            ResultSet keys = preparedStatement.executeQuery();
            if (keys.next()) {
                setId(keys.getInt(1));
                message = "Logged in Successfully";
            }else {
                message = "Wrong Credentials";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // update librarian
    public void update() {}

    // delete a librarian
    public void destroy() {}
}
