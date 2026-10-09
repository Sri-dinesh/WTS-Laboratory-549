
// Program to Create Table in Mysql - using Prepared Statement
import java.sql.*;

public class CreateTablePrepared {

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

            String qry = "create table IF NOT EXISTS products( product_no int(5), name varchar(20), image blob, price int(10) )";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
            System.out.println("Table Created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
