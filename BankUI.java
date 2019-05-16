package FFF;
/**
 * @author
 * @version 1.0
 * the beginning of the whole project
 */

import java.util.Scanner;

public class BankUI {
    public static void main(String[] args) {
        System.out.println("Choose what you want to do:1.add a customer2.login 3.exit");
        Scanner in=new Scanner(System.in);
        int x = in.nextInt();
        if(x==1){
            Customer c = new Customer();
        }
        else if (x==2){
            Login l = new Login();
        }
        else if (x==3){
            System.exit(0);
        }

    }
}
