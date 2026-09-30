
//Program to Update a field in Database
import java.sql.*; // first step
import java.util.*;

public class Update {
    public static void main(String args[]) {
        try {
            // org.mariadb.jdbc.Driver
            Class.forName("org.mariadb.jdbc.Driver"); // second step
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc_demo", "eremika",
                    "Mikasa"); // third step
            // step
            Statement st = con.createStatement(); // fourth step
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
