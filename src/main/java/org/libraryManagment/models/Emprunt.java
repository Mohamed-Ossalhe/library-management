package org.libraryManagment.models;

import org.libraryManagment.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Emprunt extends DB {

    // properties
    private String name;
    private String cin;
    private String address;

    private final String table = "emprunts";

    private ResultSet emprunts;
    private ResultSet emprunt;

    // constructor
    public Emprunt() {
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
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO " + table + " (name, cin, address) VALUES (?, ?, ?)");
            preparedStatement.setString(1, getName());
            preparedStatement.setString(2, getCin());
            preparedStatement.setString(3, getAddress());
            preparedStatement.executeQuery();
            message = "Created Successfully";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // display specific emprunt
    public ResultSet show(String ISBN) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM " + table + " WHERE ISBN = ?");
            preparedStatement.setString(1, ISBN);
            this.emprunt = preparedStatement.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return this.emprunt;
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
