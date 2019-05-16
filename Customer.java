package FFF;
/**
 *   @author
 *   @version 1.0
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static java.lang.System.exit;

public class Customer implements Bank_Control {
    protected String name;
    protected String address;
    protected String dob;
    protected boolean creditStatus;
    protected long accountNumber;
    public int pin;

    public Customer() {
        addCustomer();
        addAccount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }


    public void addAccount(){

        String read[]=Reader1("Customer.txt");
        int date= Integer.parseInt(read[2]);
        long an = Calendar.getInstance().getTimeInMillis();
        accountNumber =  an;
        System.out.println("your account number is "+an);

        String PI = String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(4, 8);
        System.out.println("your account Pin is"+PI);
        pin = Integer.parseInt(PI);
        writeToFile(PI,accountNumber);
        System.out.println("please choose what kinds of account you want to open");
        System.out.println("1.Saver Account 2.Current Account 3.Junior Account");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        if(x==1){
            writeToFile(an,date);
            writeToFile("0",accountNumber);//0=saver
            writeToFile("0",accountNumber);//suspend
            writeToFile("0",accountNumber);//balance
            writeToFile("0",accountNumber);//deposit-unclear
            writeToFile("0",accountNumber);//withdrawal

            SaverAccount saverAcount = new SaverAccount(accountNumber);
        }
        else if (x==2){
            writeToFile(an,date);
            writeToFile("1",accountNumber);//1=Current
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);

            CurrentAccount currentAccount = new CurrentAccount(accountNumber);
        }
        else if (x==3){
            String read1[]=Reader1("Customer.txt");
            int date1= Integer.parseInt(read[2]);
            int d=date1/10000;
            if ((2019-d)>16){
                System.out.println("you can not");
                deleteFile(accountNumber+".txt");
                exit(1);
            }
            writeToFile("2",accountNumber);//2=junior
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            writeToFile("0",accountNumber);
            JuniorAccount juniorAccount = new JuniorAccount(accountNumber);
        }

    }

    /**
     * delete the account if your condition is not suitable
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("delete" + fileName + "success");
                System.out.println("delete the account successfully");
                return true;
            } else {
                System.out.println("de" + fileName + "e");
                return false;
            }
        } else {
            System.out.println("de" + fileName + "no");
            return false;
        }
    }
    @Override
    /**
     * check your credit status
     */
    public void confirmCreditStatus(){
        if(creditStatus==true){}
        else if (creditStatus==false){
            System.out.println("your credit status is not good, sorry");
            exit(1);
        }
    }
    @Override
    public void closeAccount(long accountNumber) {
    }

    /**
     * add a customer
     */
    @Override
    public void addCustomer() {
        Scanner in = new Scanner(System.in);
        String array[] = new String[4];
        System.out.println("please input your name:");
        String name = in.next();
        System.out.println("please input your address:");
        String address = in.next();
        System.out.println("please input your dob:(the only one format is xxxxxxxx)(yyyymmdd)");
        String dob =  in.next();
        int date= Integer.parseInt(dob);

            if (date/10000<2019&&(((date%10000)/100))<12&&(date%100)<31){
                System.out.println("please input your creditStatus:");
                boolean creditStatus = in.nextBoolean();
                array[0]=name;
                array[1]=address;
                array[2]=dob;
                array[3]=creditStatus+"";
                String inform = name+address+dob+creditStatus;
                System.out.println("ensure your information :"+inform);
                writeToFile(dob);
                write1(array,date);
                write1(array);

            }
            else {
                System.out.println("please check your date of birth and rejoin my bank ");
                exit(1);
            }


    }

    /**
     * write your information to a file
     * @param a
     */
    public static void writeToFile(String a){

        try {
            File file = new File(a+".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("gg");
        }
    }

    /**
     * write your information to a file
     * @param array
     * @param dob
     */
    public static void write1(String array[],int dob){
        try {
            File file = new File(dob+".txt");
            if(file.exists()){
                try (FileWriter fw = new FileWriter(file, true)) {
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (int i = 0; i < 4; i++) {
                        String content = array[i];
                        bw.write(content + System.getProperty("line.separator"));
                    }
                    bw.close();
                    fw.close();
                }
                System.out.println("done!");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("gg");
        }
    }

    /**
     * write your information to a file
     * @param array
     */
    public static void write1(String array[]){//##############写入

        try {
            File file = new File("Customer.txt");
            if(file.exists()){
                FileWriter fw = new FileWriter(file,false);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < 4; i++) {
                    String content = array[i];
                    bw.write(content+System.getProperty("line.separator"));
                }
                bw.close();
                fw.close();
                System.out.println("done!");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("gg");
        }
    }

    /**
     * write your information to a file
     * @param a
     * @param b
     */
    public static void writeToFile(String a,long b){

        try {
            File file = new File(b+".txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            writer.write(a+System.getProperty("line.separator"));
            writer.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("gg");
        }
    }

    /**
     * write your information to a file
     * @param a
     * @param b
     */
    public static void writeToFile(long a,int b){

        try {
            File file = new File(b+".txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            writer.write(a+System.getProperty("line.separator"));
            writer.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("gg");
        }
    }

    /**
     * read your account information
     * @param name
     * @return
     */
    public static String[] Reader1(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = arrayList.size();
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            String s = arrayList.get(i);
            array[i] = s;
        }
        return array;
    }


    @Override
    public void clearFunds(long accountNumber) {

    }

    @Override
    public void GiveNotice() {

    }

    @Override
    public void Withdrawal(long accountNumber) {

    }

    @Override
    public void suspend(long accountNumber) {

    }

    @Override
    public void restate(long accountNumber) {

    }


}
