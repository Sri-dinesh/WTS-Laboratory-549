
//Program to Insert Employee Data
import java.sql.*;
import java.util.*;

public class InsertTable {
    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc_demo", "eremika",
                    "Mikasa");
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
