
// Program to Create EmployeeTable in MySQL
import java.sql.*;

public class CreateTable {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

            Statement st = con.createStatement();
            st.executeUpdate(
                    "create table IF NOT EXISTS emp1( no integer(10), name varchar(30), salary integer(10), department varchar(40))");

            System.out.println("Table Created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
