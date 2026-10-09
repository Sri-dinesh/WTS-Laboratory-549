
// Program to Delete a Record in Table-using PreparedStatement
import java.sql.*;
import java.util.*;

public class DeleteTablePrepared {

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

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the record to be deleted");
            int pid = sc.nextInt();
            String qry = "DELETE FROM products WHERE product_no=?";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, pid);
            ps.executeUpdate();
            System.out.println("Row Effected / Deleted");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
