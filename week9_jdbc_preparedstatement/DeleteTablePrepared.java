
// Program to Delete a Record in Table-using PreparedStatement
import java.sql.*;
import java.util.*;

public class DeleteTablePrepared {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

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
