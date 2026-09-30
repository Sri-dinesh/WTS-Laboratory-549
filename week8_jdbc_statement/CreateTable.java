
//Program to Create EmployeeTable in MySQL
import java.sql.*; // first step

public class CreateTable {
    public static void main(String args[]) {
        try {
            // org.mariadb.jdbc.Driver
            Class.forName("org.mariadb.jdbc.Driver"); // second step
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc_demo", "eremika",
                    "Mikasa"); // third step
            // step
            Statement st = con.createStatement(); // fourth step
            st.executeUpdate(
                    "create table IF NOT EXISTS emp1( no integer(10),name varchar(30), salary integer(10),department varchar(40))"); // execute
                                                                                                                                     // a
                                                                                                                                     // query
            System.out.println("Table Created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
