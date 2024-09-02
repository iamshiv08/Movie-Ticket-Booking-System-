package com.ticket2cinema;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    String url = "jdbc:mysql://localhost:3306/ticket2cinema";
    String user = "root";
    String password = "";
    static Connection con;

    public DBConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);

    }

    public static Connection getCon() {
        return con;
    }
}
