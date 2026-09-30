import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {

    private static final String URL = "jdbc:mariadb://localhost:3306/jdbc_demo";
    private static final String USER = "eremika";
    private static final String PASSWORD = "Mikasa";

    public static boolean checkUser(String email1, String pass1) {
        boolean isValid = false;
        String query = "SELECT 1 FROM users WHERE email = ? AND password = ?";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email1);
                ps.setString(2, pass1);

                try (ResultSet rs = ps.executeQuery()) {
                    isValid = rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
