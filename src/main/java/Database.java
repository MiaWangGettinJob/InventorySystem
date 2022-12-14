import java.sql.*;
import java.util.Properties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Scanner;

public class Database {
    private String Username;

    public HikariDataSource setUpPool(){
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please Enter Username, " +
                "which is strings before '@' of your e-mail adress:\n" +
                "For example, enter 'simiaowang1' if your address is 'simiaowang1@gmail.com'");
        Username = input1.next();

        // Set up URL parameters
        String jdbcURL = String.format("jdbc:mysql:///%s", "inventory");
        Properties connProps = new Properties();
        // TODO: we should read it from environment instead of hardcoding
        connProps.setProperty("user", Username);
        connProps.setProperty("sslmode", "disable");
        connProps.setProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        connProps.setProperty("cloudSqlInstance", "ferrous-upgrade-371505:us-central1:inventory");
        connProps.setProperty("enableIamAuth", "true");

        // Initialize connection pool
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcURL);
        config.setDataSourceProperties(connProps);
        config.setConnectionTimeout(10000); // 10s
        HikariDataSource connectionPool = new HikariDataSource(config);
        //String tableName = String.format("books_%s", UUID.randomUUID().toString().replace("-", ""));

        return connectionPool;
    }

    /*public Statement connect() throws SQLException {
        //load driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully load driver!");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail to load driver!");
            throw new RuntimeException(e);
        }
        //connect database
        String url = "jdbc:mysql:///inventory?cloudSqlInstance=inventory&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root\n";
        //String url = "jdbc:google:mysql://198.244.105.161:inventory?user=root";
        //String user = "root";
        //String password = "123456";
        Connection con = DriverManager.getConnection(url);
        System.out.println(
                "Connection Established successfully");
        Statement stmt = con.createStatement();
        return stmt;
    }

*/

    // Deleting from database
    public void delete(String id, HikariDataSource connectionPool){
        try (Connection conn = connectionPool.getConnection()) {
            String stmt = "DELETE from goods WHERE id = '" + id +
                    "'";
            boolean x;
            try (PreparedStatement deleteStatement = conn.prepareStatement(stmt)) {
                x = deleteStatement.execute();
            }
            System.out.println("Product Successfully Deleted");

        }
        catch(Exception e)
        {
            System.out.println(e);}
    }


    public void insert(String id, String group_No, String product_size,
                       String	location, String product_type, Double cost,HikariDataSource connectionPool) {
        try (Connection conn = connectionPool.getConnection()) {
            String stmt = "insert into goods values('" + id + "', '" + group_No +
                    "', '" + product_size + "', '" + location + "', '" + product_type + "','" + cost + "')";
            boolean x;
            try (PreparedStatement insertStmt = conn.prepareStatement(stmt)) {
                x = insertStmt.execute();
            }
                System.out.println("Successfully Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

    /*public void close() throws SQLException {
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
}*/
