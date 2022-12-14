import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws SQLException {

        Database a = new Database();
        HikariDataSource connectionPool = a.setUpPool();
        Homepage b= new Homepage(a, connectionPool);
        b.givechoice(a, connectionPool);
    }

}

