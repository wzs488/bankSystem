package FFF;
/**
 *  * @author
 *  * @version 1.0
 *
 *  login your account
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    public Login() {
        Scanner in = new Scanner(System.in);
        long x =in.nextLong();
        int pin = in.nextInt();
        String acc = x+".txt";
        String ac = x+"";
        checkID(ac,pin);
        Account account=new Account(x);
    }

    /**
     * check your pin
     * @param ac
     * @param pin
     */
    public void checkID(String ac,int pin){

        //System.out.println(readFile(ac));
        if (pin==readFile(ac)){
            System.out.println("right");
        }

    }

    /**
     * read the pin of your account
     * @param x
     * @return
     */
    public int readFile(String x){//#################读取
        int TxtNum=0;
        try {
            File file = new File(x+".txt");
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                TxtNum=Integer.parseInt(br.readLine());
                br.close();
                return TxtNum;
            } else {
                System.out.println("no exist");
            }
        } catch (Exception e) {
            System.out.println("wrong");
        }
        return TxtNum;
    }
}
