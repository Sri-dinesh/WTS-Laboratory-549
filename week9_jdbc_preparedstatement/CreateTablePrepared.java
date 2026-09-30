
// Program to Create Table in Mysql - using Prepared Statement
import java.sql.*;

public class CreateTablePrepared {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

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
