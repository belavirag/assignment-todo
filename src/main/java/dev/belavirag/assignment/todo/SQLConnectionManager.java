package dev.belavirag.assignment.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionManager {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/todoit", "root", "1234");
        } catch (SQLException e) {
            System.err.println("Could not connect to database");
            throw new RuntimeException(e);
        }
    }
}
