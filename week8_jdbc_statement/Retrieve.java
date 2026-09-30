
//Program to Retrieve Data from Database
import java.sql.*; // first step

public class Retrieve {
    public static void main(String args[]) {
        try {
            // org.mariadb.jdbc.Driver
            Class.forName("org.mariadb.jdbc.Driver"); // second step
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc_demo", "eremika",
                    "Mikasa"); // third step
            // step
            Statement st = con.createStatement(); // fourth step
            String qry = "select * from emp1";
            ResultSet rs = st.executeQuery(qry);
            System.out.println("The Employee Details are\n");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getInt(3) + ":" + rs.getString(4));
            }
            System.out.println("Data Retrieved Successfully");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
