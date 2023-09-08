package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Emprunt extends DB {

    // properties
    private int id;
    private String name;
    private String cin;
    private String address;

    private final String table = "emprunts";

    private ResultSet emprunts;
    private ResultSet emprunt;

    // constructor
    public Emprunt() {
    }

    // id getters & setters
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

    // display all emprunts
    public ResultSet index() {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table );
            this.emprunts = preparedStatement.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return this.emprunts;
    }

    // store new emprunte
    public String store() {
        String message = null;
        try {
            String query = "INSERT INTO " + table + " (name, cin, address) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, getName());
            preparedStatement.setString(2, getCin());
            preparedStatement.setString(3, getAddress());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            setId(keys.getInt(1));
            message = "Created Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // display specific emprunt
    public boolean show(String cin) {
        boolean message = false;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE cin = ?");
            preparedStatement.setString(1, cin);
            ResultSet keys = preparedStatement.executeQuery();
            if (keys.next()) {
                setId(keys.getInt(1));
                message = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // delete specific emprunt
    public String destroy(String ISBN) {
        String message = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM " + table + " WHERE ISBN = ?");
            preparedStatement.setString(1, ISBN);
            preparedStatement.executeQuery();
            message = "Deleted Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
