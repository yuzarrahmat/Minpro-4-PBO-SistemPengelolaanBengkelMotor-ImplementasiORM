package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiglrgn {
    private static final String URL = "jdbc:mysql://localhost:3306/bengkel_motor_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Isikan password MySQL kamu jika ada

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] Driver MySQL JDBC tidak ditemukan: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}