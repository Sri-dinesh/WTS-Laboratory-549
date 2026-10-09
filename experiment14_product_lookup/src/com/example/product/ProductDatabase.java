package com.example.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ProductDatabase {
    private ProductDatabase() {
    }

    public static Connection getConnection() throws SQLException {
        // Linux / MariaDB defaults.
        String url = System.getProperty("product.db.url",
                "jdbc:mariadb://localhost:3306/product_catalog");
        String user = System.getProperty("product.db.user", "eremika");
        String password = System.getProperty("product.db.password", "Mikasa");
        try {
            // Windows / MySQL alternative: use jdbc:mysql, root, your MySQL password,
            // and com.mysql.cj.jdbc.Driver together.
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException("MariaDB JDBC driver not found.", exception);
        }
        return DriverManager.getConnection(url, user, password);
    }
}