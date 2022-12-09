import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Logout {
    public Logout(Database a, Statement smtm){}
    public void logout(Database a, Statement smtm){
        try {
            a.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Bye!");
        System.exit(0);
    }
}
