
// Program to Update a field in Database
import java.sql.*;
import java.util.*;

public class Update {

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
            System.out.println("Enter the Employee Id to Update Salary");
            int empno = sc.nextInt();
            System.out.println("Enter the Salary to be updated");
            int esal = sc.nextInt();
            String qry = "UPDATE emp1 SET salary='" + esal + "' WHERE no=" + empno + "";
            st.executeUpdate(qry);
            System.out.println("Data Updated Successfully");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
