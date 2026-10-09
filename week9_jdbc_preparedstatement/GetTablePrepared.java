
// Program to retrieve Data from Database-using prepared statement
import java.sql.*;

public class GetTablePrepared {

    public static void main(String args[]) {
        try {
            // Linux / MariaDB. For Windows / MySQL use com.mysql.cj.jdbc.Driver.
            Class.forName("org.mariadb.jdbc.Driver");
            // Windows / MySQL alternative: comment the line above and uncomment:
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // Linux / MariaDB connection.
            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

            // Windows / MySQL alternative: comment the connection above and use:
            // Connection con = DriverManager.getConnection(
            // "jdbc:mysql://localhost:3306/jdbc_demo", "root", "your-MySQL-root-password");

            String qry = "select * from products";
            PreparedStatement ps = con.prepareStatement(qry);
            ResultSet rs = ps.executeQuery();
            System.out.println("The Product Details are \n");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getBlob(3) + ":" + rs.getInt(4));
            }
            System.out.println("Data Retrieved Successfully");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
