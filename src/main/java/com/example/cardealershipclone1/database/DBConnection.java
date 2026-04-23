package com.example.cardealershipclone1.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mvc_project",
                    "root",
                    "123456"
            );

            System.out.println("CONNECTED");
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
