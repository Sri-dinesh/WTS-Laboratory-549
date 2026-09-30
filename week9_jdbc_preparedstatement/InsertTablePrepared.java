
// Program to insert Product Data - using Prepared Statement
import java.io.*;
import java.sql.*;
import java.util.*;

public class InsertTablePrepared {

    public static void main(String args[]) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/jdbc_demo",
                    "eremika",
                    "Mikasa");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Product No: ");
            int productNo = sc.nextInt();
            System.out.print("Enter Product Name: ");
            String productName = sc.next();
            System.out.print("Enter Image File Path: ");
            String imagePath = sc.next();
            System.out.print("Enter Price: ");
            int price = sc.nextInt();

            String qry = "INSERT INTO products(product_no,name,image,price) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, productNo);
            ps.setString(2, productName);
            InputStream in = new FileInputStream(imagePath);
            ps.setBlob(3, in);
            ps.setInt(4, price);
            ps.executeUpdate();
            System.out.println("Row Inserted");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
