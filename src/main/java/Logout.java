import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Logout {
    public Logout(Database a, HikariDataSource connectionPool){}
    public void logout(Database a, HikariDataSource connectionPool){
        /*
        try {
            a.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         */
        System.out.println("Bye!");
        System.exit(0);
    }
}
