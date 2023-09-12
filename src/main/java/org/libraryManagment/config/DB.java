package org.libraryManagment.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String dbname = "library_management";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    // connection getters & setters


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    // constructor
    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            setConnection(DriverManager.getConnection(this.url + this.dbname, this.username, this.password));
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // close connection to db
    public void closeConnection() throws Exception {
        getConnection().close();
    }
}
