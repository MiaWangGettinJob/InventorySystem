import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Goods {
    public Goods(Database a, HikariDataSource connectionPool) {}
    public void InformationAll(Database a, HikariDataSource connectionPool){
        List blogs = new LinkedList();
        String query = "SELECT * from goods";
        System.out.println("******************************************" );
        try (Connection conn = connectionPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.print("id: " + rs.getString(1));
                System.out.print(", group_No: " + rs.getString(2));
                System.out.print(", product_size: " + rs.getString(3) );
                System.out.print(", location: " + rs.getString(4) );
                System.out.print(", product_type: " + rs.getString(5) );
                System.out.print(", cost: " + rs.getString(6) + "\n");
            }
        }
        catch(Exception e){
            System.out.println(e);}
        System.out.println("******************************************" );
        Homepage newpage = new Homepage(a, connectionPool);
        newpage.givechoice(a, connectionPool);
    }

    public void oneInformation(Database a, HikariDataSource connectionPool) {
        System.out.println("Please enter the group_No. that you want to enquiry.");
        Scanner input1 = new Scanner(System.in);
        String group_No = input1.next();
        String q2 = "SELECT group_No, product_size, location, COUNT(*) from goods " +
                "WHERE group_No = '" + group_No +"' GROUP BY group_No, product_size, location";
        System.out.println("******************************************" );
        try (Connection conn = connectionPool.getConnection()){
            PreparedStatement ps = conn.prepareStatement(q2);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.print("group_No: " + rs.getString(1));
                System.out.print("; product_size: " + rs.getString(2));
                System.out.print("; location: " + rs.getString(3));
                System.out.println("; In Stock: " + rs.getString(4));
            }
        }
        catch(Exception e){
            System.out.println(e);}
        System.out.println("******************************************" );
        Homepage newpage = new Homepage(a, connectionPool);
        newpage.givechoice(a, connectionPool);
    }
    public void plus(Database a, HikariDataSource connectionPool){
        while(true) {
            Scanner input1 = new Scanner(System.in);
            System.out.println("Please enter the information of id, group_No, " +
                    "product_size, location,product_type and cost." +
                    "Please separate each data with ';'.\n" +
                    "For exmple:WSH119;SH1;M;Seattle, WA;shorts;40.00\n");
            String str = input1.nextLine();
            String res[] = str.split(";");
            String id = res[0];
            String group_No = res[1];
            String product_size = res[2];
            String location = res[3];
            String product_type = res[4];
            Double cost = Double.parseDouble(res[5]);
            a.insert(id, group_No,product_size, location,product_type,cost, connectionPool);

            System.out.println("Please enter 'done' if there is no more to register. Else, enter anything.");
            String result = input1.next();
            if (result.equals("done")) {
                break;
            }
        }
        Homepage newpage = new Homepage(a, connectionPool);
        newpage.givechoice(a, connectionPool);
    }
    public void sell(Database a, HikariDataSource connectionPool){
        while(true) {
            System.out.println("Please enter the id of product sold.\n " +
                    "If there is no more to register,Please enter 'done'.");
            Scanner input1 = new Scanner(System.in);
            String id = input1.next();
            if (id.equals("done")) {
                break;
            }
            a.delete(id, connectionPool);
        }
        Homepage newpage = new Homepage(a, connectionPool);
        newpage.givechoice(a, connectionPool);
    }

    public void exchange(Database a, HikariDataSource connectionPool){
        //delete data of product that is going out of warehouse
        while(true){
            System.out.println("Please put in the id of product ex-warehousing. " +
                    "If there is no more to register,Please enter 'done'.");
            Scanner input1 = new Scanner(System.in);
            String id = input1.next();
            if (id.equals("done")) {
                break;
            }
            a.delete(id, connectionPool);
        }

        //insert data of product that is going back to warehouse
        System.out.println("Please put in the information of product in-warehousing");
        plus(a, connectionPool);
    }

    public void refund(Database a, HikariDataSource connectionPool){
        plus(a, connectionPool);
    }
}
