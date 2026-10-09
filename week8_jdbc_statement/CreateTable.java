
// Program to Create EmployeeTable in MySQL
import java.sql.*;

public class CreateTable {

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
            st.executeUpdate(
                    "create table IF NOT EXISTS emp1( no integer(10), name varchar(30), salary integer(10), department varchar(40))");

            System.out.println("Table Created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
