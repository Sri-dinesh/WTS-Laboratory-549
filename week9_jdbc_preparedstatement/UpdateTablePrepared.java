
// Program to update a field in Database-using Prepared Statement
import java.sql.*;
import java.util.*;

public class UpdateTablePrepared {

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
            System.out.println("Enter the Product Id to Update price");
            int pid = sc.nextInt();
            System.out.println("Enter the Price to be updated");
            int sprice = sc.nextInt();
            String qry = "UPDATE products SET price=? WHERE product_no=?";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, sprice);
            ps.setInt(2, pid);
            ps.executeUpdate();
            System.out.println("Row Updated");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
