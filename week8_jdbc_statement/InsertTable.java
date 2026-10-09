
// Program to Insert Employee Data
import java.sql.*;
import java.util.*;

public class InsertTable {

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
            System.out.println("Enter the employee details");
            System.out.print("Enter Employee No: ");
            int eno = sc.nextInt();
            System.out.print("Enter Employee Name: ");
            String ename = sc.next();
            System.out.print("Enter Employee Salary: ");
            int esal = sc.nextInt();
            System.out.print("Enter Employee Department: ");
            String edept = sc.next();
            String qry = "INSERT INTO emp1(no,name,salary,department) VALUES (" + eno + ",'" + ename + "'," + esal
                    + ",'" + edept + "')";
            st.executeUpdate(qry);
            System.out.println("Row Inserted");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
