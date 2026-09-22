import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * JDBC Registration Demo — everything in one file so it's easy to read top to bottom.
 *
 * Flow:
 *   1. main() connects to the DB ONCE at startup (and closes it at the end).
 *   2. Every menu action just reuses that single shared connection.
 *
 * Run:  java -cp "DRIVER_JAR:out" RegistrationApp
 */
public class RegistrationApp {

    // ---- 1. Database settings (change here if your setup differs) ----
    private static final String URL      = "jdbc:mariadb://localhost:3306/jdbc_demo";
    private static final String USER     = "eremika";
    private static final String PASSWORD = "Mikasa";

    // One shared connection for the whole app (set in main, used everywhere)
    private static Connection con;

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JDBC Registration Demo ===");

        // Connect ONCE before anything else. If this fails, stop the app.
        try {
            con = connect();
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("1. Register a new user");
            System.out.println("2. List all users");
            System.out.println("3. Find user by email");
            System.out.println("4. Delete user by email");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> register();
                    case "2" -> listAll();
                    case "3" -> findByEmail();
                    case "4" -> deleteByEmail();
                    case "5" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } finally {
                if (choice.equals("5")) close(con);
            }
        }
    }

    // ---- 2. Connection helpers ----

    /** Opens the single connection used by the whole app. */
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /** Closes the connection when the app exits. */
    private static void close(Connection c) {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println("Warning: could not close connection cleanly.");
        }
    }

    // ---- 3. Menu actions (all reuse the shared 'con') ----

    /** INSERT with PreparedStatement (the ? placeholders keep it SQL-injection safe). */
    private static void register() throws SQLException {
        System.out.print("Name     : ");
        String name = in.nextLine().trim();
        System.out.print("Email    : ");
        String email = in.nextLine().trim();
        System.out.print("Password : ");
        String password = in.nextLine().trim();
        System.out.print("City     : ");
        String city = in.nextLine().trim();
        System.out.print("Phone    : ");
        String phone = in.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Name, email and password are required.");
            return;
        }
        if (findEmailExists(email)) {
            System.out.println("That email is already registered.");
            return;
        }

        String sql = "INSERT INTO users (name, email, password, city, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, city);
            ps.setString(5, phone);
            ps.executeUpdate();
            System.out.println("Registered successfully!");
        }
    }

    /** SELECT many rows, read them back with ResultSet. */
    private static void listAll() throws SQLException {
        String sql = "SELECT * FROM users ORDER BY id";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean any = false;
            while (rs.next()) {
                any = true;
                System.out.printf("%-3d | %-15s | %-25s | %-12s | %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city"),
                        rs.getString("phone"));
            }
            if (!any) System.out.println("No users yet.");
        }
    }

    /** SELECT one row by email. */
    private static void findByEmail() throws SQLException {
        System.out.print("Email: ");
        String email = in.nextLine().trim();

        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.printf("Found: id=%d, name=%s, city=%s, phone=%s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("city"), rs.getString("phone"));
            } else {
                System.out.println("No user with that email.");
            }
            }
        }
    }

    /** DELETE by email; executeUpdate() returns how many rows were affected. */
    private static void deleteByEmail() throws SQLException {
        System.out.print("Email: ");
        String email = in.nextLine().trim();

        String sql = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            if (ps.executeUpdate() > 0) {
                System.out.println("Deleted.");
            } else {
                System.out.println("No user with that email.");
            }
        }
    }

    /** Small helper used by register() to check for duplicate emails. */
    private static boolean findEmailExists(String email) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
