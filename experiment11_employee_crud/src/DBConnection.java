import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    // Linux / MariaDB defaults.
    private static final String URL = System.getProperty(
            "employee.db.url", "jdbc:mariadb://localhost:3306/employee2");
    private static final String USER = System.getProperty("employee.db.user", "eremika");
    private static final String PASSWORD = System.getProperty("employee.db.password", "Mikasa");

    // Windows / MySQL alternative: replace the three defaults above with these
    // values.
    // URL: jdbc:mysql://localhost:3306/employee2
    // USER: root
    // PASSWORD: your-MySQL-root-password

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Linux / MariaDB driver. For Windows / MySQL use com.mysql.cj.jdbc.Driver.
            Class.forName("org.mariadb.jdbc.Driver");
            // Windows / MySQL alternative: comment the line above and uncomment:
            // Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException("MariaDB JDBC driver not found.", exception);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}