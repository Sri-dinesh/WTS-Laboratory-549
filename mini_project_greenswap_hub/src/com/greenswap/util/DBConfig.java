package com.greenswap.util;

public final class DBConfig {

    // Linux / MariaDB defaults.
    public static final String DEFAULT_URL = "jdbc:mariadb://localhost:3306/jdbc_demo";
    public static final String DEFAULT_USER = "eremika";
    public static final String DEFAULT_PASSWORD = "Mikasa";
    public static final String DEFAULT_DRIVER = "org.mariadb.jdbc.Driver";

    // Windows / MySQL alternative: use this complete set instead of the four values
    // above.
    // public static final String DEFAULT_URL =
    // "jdbc:mysql://localhost:3306/jdbc_demo";
    // public static final String DEFAULT_USER = "root";
    // public static final String DEFAULT_PASSWORD = "your-MySQL-root-password";
    // public static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DBConfig() {
    }
}
