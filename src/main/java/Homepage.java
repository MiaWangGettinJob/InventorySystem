import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Homepage {
    public Homepage(Database a, HikariDataSource connectionPool) {
    }

    public void givechoice (Database a, HikariDataSource connectionPool) {
        System.out.println("Please put in the number of function you want to use:\n" +
                "1. Enquiry overall inventory information\n" +
                "2. Enquiry inventory information of a single product\n" +
                "3. Goods purchase \n" +
                "4. Goods selling \n" +
                "5. Exchange goods\n" +
                "6. Refund\n" +
                "7. Log out  ");
        Goods goods = new Goods(a, connectionPool);
        Scanner input1 = new Scanner(System.in);
        int choice = input1.nextInt();
        if (choice == 1){
            goods.InformationAll(a, connectionPool);
        }
        else if (choice == 2){
            goods.oneInformation(a, connectionPool);
        }
        else if (choice == 3){
            goods.plus(a, connectionPool);
        }
        if (choice == 4){
            goods.sell(a,connectionPool);
        }
        if (choice == 5){
            goods.exchange(a,connectionPool);
        }
        if (choice == 6){
            goods.refund(a,connectionPool);
        }
        if (choice == 7){
            Logout out = new Logout(a,connectionPool);
            out.logout(a,connectionPool);
        }
    }
}
