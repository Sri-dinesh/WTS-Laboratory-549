import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private static final String URL = System.getProperty(
            "employee.db.url", "jdbc:mariadb://localhost:3306/employee2");
    private static final String USER = System.getProperty("employee.db.user", "eremika");
    private static final String PASSWORD = System.getProperty("employee.db.password", "Mikasa");

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException("MariaDB JDBC driver not found.", exception);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}