
// Program to delete a Record in Table
import java.sql.*;
import java.util.*;

public class Delete {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

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
