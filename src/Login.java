import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    //set up Username and password
    private String Username = "User1";
    private String Password = "123";

    public void enter(){

        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter Username : ");
        String username = input1.next();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter Password : ");
        String password = input2.next();



        //check Username and Password
        if (username.equals(Username) && password.equals(Password)) {
            System.out.println("Access Granted! Welcome!");
            Database a = new Database();
            Statement stmt = null;
            try {
                stmt = a.connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Homepage newpage = new Homepage(a, stmt);
            newpage.givechoice(a, stmt);
        }
        else if (username.equals(Username)) {
            System.out.println("Invalid Password!Please try again!");
            enter();
        } else if (password.equals(Password)) {
            System.out.println("Invalid Username!Please try again!");
            enter();
        } else {
            System.out.println("Invalid Username & Password!Please try again!");
            enter();
        }
    }
}
