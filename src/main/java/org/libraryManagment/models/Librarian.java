package org.libraryManagment.models;

public class Librarian {
    // properties
    private String name;

    private String table = "librarians";

    // constructor
    public Librarian() {
    }

    // name getters & setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // display all librarians
    public void index() {}

    // store new librarian
    public void store() {}

    // display a librarian
    public void show() {}

    // update librarian
    public void update() {}

    // delete a librarian
    public void destroy() {}
}
