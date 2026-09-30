
// Program to retrieve Data from Database-using prepared statement
import java.sql.*;

public class GetTablePrepared {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

            String qry = "select * from products";
            PreparedStatement ps = con.prepareStatement(qry);
            ResultSet rs = ps.executeQuery();
            System.out.println("The Product Details are \n");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getBlob(3) + ":" + rs.getInt(4));
            }
            System.out.println("Data Retrieved Successfully");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
