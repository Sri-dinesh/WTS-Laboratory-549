
// Program to delete a Record in Table
import java.sql.*;
import java.util.*;

public class Delete {

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

            Statement st = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the record to be deleted");
            int eno1 = sc.nextInt();
            String qry = "DELETE FROM emp1 WHERE no=" + eno1 + "";
            st.executeUpdate(qry);
            System.out.println("Record deleted Successfully");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
