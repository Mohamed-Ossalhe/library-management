package org.libraryManagment.entities;

public class Emprunt {

    // properties
    private String name;
    private String cin;
    private String address;

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
}
