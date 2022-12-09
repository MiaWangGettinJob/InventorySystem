import java.sql.*;
import java.util.Scanner;

public class Database {
    public Statement connect() throws SQLException {
        //load driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully load driver!");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail to load driver!");
            throw new RuntimeException(e);
        }
        //connect database
        String url = "jdbc:mysql://localhost:3306/inventory?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "NEU2022fall";
        Connection con = DriverManager.getConnection(url,user,password);
        System.out.println(
                "Connection Established successfully");
        Statement stmt = con.createStatement();
        return stmt;
    }

    public void delete(String id, Statement stmt){
        try
        {
            // Deleting from database
            String q1 = "DELETE from goods WHERE id = '" + id +
                    "'";
            int x = stmt.executeUpdate(q1);
            if (x > 0)
                System.out.println("Product Successfully Deleted");
            else
                System.out.println("ERROR OCCURRED :(");
        }
        catch(Exception e)
        {
            System.out.println(e);}
    }
    public void insert(String id, String group_No, String product_size,
                       String	location, String product_type, Double cost,Statement stmt){
        try
        {
        String q1 = "insert into goods values('" +id+ "', '" +group_No+
                "', '" +product_size+ "', '" +location+ "', '"+product_type+"','"+cost+"')";
        int x = stmt.executeUpdate(q1);
        if (x > 0)
            System.out.println("Successfully Inserted");
        else
            System.out.println("Insert Failed");}
        catch(Exception e)
        {
            System.out.println(e);}

    }

    public void close() throws SQLException {
        //load driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail to load driver!");
            throw new RuntimeException(e);
        }
        //connect database
        String url = "jdbc:mysql://localhost:3306/inventory?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        String user = "root";
        String password = "NEU2022fall";
        Connection con = DriverManager.getConnection(url,user,password);
        //close connection
        con.close();
    }
}
