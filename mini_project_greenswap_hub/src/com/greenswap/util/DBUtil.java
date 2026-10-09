package com.greenswap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {

    private static volatile String url = DBConfig.DEFAULT_URL;
    private static volatile String user = DBConfig.DEFAULT_USER;
    private static volatile String password = DBConfig.DEFAULT_PASSWORD;
    private static volatile String driver = DBConfig.DEFAULT_DRIVER;

    private DBUtil() {
    }

    public static void configure(String jdbcUrl, String jdbcUser, String jdbcPassword, String jdbcDriver) {
        url = jdbcUrl;
        user = jdbcUser;
        password = jdbcPassword;
        driver = jdbcDriver;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC driver not found: " + driver, e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
