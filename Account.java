package FFF;
/**
 * @author
 * @version 1.0
 * Entity Class
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.exit;
public class Account implements Bank_Control {


    int accountNumber;
    double balance;
    double overdraftLimit;
    boolean isSuspended;
    boolean isActive;
    boolean noticeNeeded;

    /**
     *Choose what you want to do
     *
     * @param accountNumber
     */
    public Account(long accountNumber) {
        System.out.println("asada");
        int a[] = Reader1(accountNumber+".txt");
        CheckState(a[2],accountNumber);
        if(a[1]==0){
            System.out.println("this is a Saver account");
        }
        Scanner in = new Scanner(System.in);
        int Acc = Integer.parseInt(in.next());
        switch (Acc){
            case 1:
                addDeposit(accountNumber);
                break;
            case 2:
                clearFunds(accountNumber);
                break;
            case 3:
                Withdrawal(accountNumber);
                break;
            case 4:
                suspend(accountNumber);
                break;
            case 5:
                closeAccount(accountNumber);
                break;
            case 6:
                exit(0);
                break;
        }

    }

    /**
     *Check if your account is suspended
     *
     * @param x
     * @param accountNumber
     */
    protected  void CheckState(int x,long accountNumber){
        if (x==1){
            System.out.println("your account is suspended");
            Scanner in = new Scanner(System.in);
            int state = in.nextInt();
            if (state==1){
                restate(accountNumber);
            }
           else {
               System.out.println("you can not");
            }
        }else {
            System.out.println("your account is available");
        }

    }
    /**
     *Add deposit and choose whether deposit is cleared
     *
     * @param accountNumber
     */
    protected void addDeposit(long accountNumber) {
        int acc[]= Reader1(accountNumber+".txt");
        Scanner in = new Scanner(System.in);
        int deposit = in.nextInt();
        int x = in.nextInt();
        if (x == 1) {
            acc[3]=acc[3]+deposit;
        }else if (x==2){
            acc[4]=acc[4]+deposit;
        }
        for (int i =0; i<6;i++){
            System.out.println(acc[i]);
        }
        write1(acc,accountNumber);
    }

    @Override
    public void confirmCreditStatus(){

    }

    @Override
    /**
     *Close your account
     *
     * @param accountNumber
     */
    public void closeAccount(long accountNumber) {
        String de = accountNumber+".txt";
        delete(de);
    }
    /**
     *delete the file
     *
     * @param fileName
     * @return
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     *delete the file
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("de" + fileName + "s");
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

    /**
     *delete the file directory
     *
     * @param dir
     * @return
     */
    public static boolean deleteDirectory(String dir) {
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);

        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("de" + dir + "no");
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = Account.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            else if (files[i].isDirectory()) {
                flag = Account.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("de e");
            return false;
        }
        if (dirFile.delete()) {
            System.out.println("de" + dir + "s");
            return true;
        } else {
            return false;
        }
    }

    /**
     *write array[] into the file
     *
     * @param array
     * @param accountNum
     */
    public static void write1(int array[],long accountNum){//##############写入

        try {

            File file = new File(accountNum+".txt");
            if(file.exists()){
                FileWriter fw = new FileWriter(file,false);
                BufferedWriter bw = new BufferedWriter(fw);

                for (int i = 0; i < 6; i++) {
                    String content = Integer.toString(array[i]);
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
     *read file into the array[]
     *
     * @param name
     * @return
     */
    public static int[] Reader1(String name) {

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
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            String s = arrayList.get(i);
            array[i] = Integer.parseInt(s);
        }

        return array;
    }
    @Override
    public void addCustomer() {

    }

    /**
     * Clear this account's funds
     *
     * @param accountNumber
     */
    @Override
    public void clearFunds(long accountNumber) {
        int acc[]= Reader1(accountNumber+".txt");
        acc[3]=acc[3]+acc[4];
        acc[4]=0;
        write1(acc,accountNumber);
    }

    @Override
    /**
     *Withdrawal your account
     *
     * @param accountNumber
     */
    public void Withdrawal(long accountNumber) {
        int acc[]= Reader1(accountNumber+".txt");
        Scanner in = new Scanner(System.in);
        int withdrawal = in.nextInt();
        acc[5]=acc[5]+withdrawal;
        acc[3]=acc[3]-withdrawal;
        if (acc[1]==2&&acc[3]<0){
            System.out.println("you can not");
            exit(1);
        }
        if (acc[1]==0){
            System.out.println("you must let the bank know before one day");
            exit(1);
        }
        if (acc[1]==1){
            int limit = 50000;
            if (acc[5]>50000){
                System.out.println(" you can not");
            }
           else {
               int limit2 = limit -acc[5];
               System.out.println("your limit is"+limit2+"now");
                write1(acc,accountNumber);
            }
        }
    }

    /**
     * Suspend the account
     * @param accountNumber
     */
    @Override
    public void suspend(long accountNumber) {
        int acc[]= Reader1(accountNumber+".txt");
        acc[2]=1;
        write1(acc,accountNumber);
    }

    /**
     * recover your account
     *
     * @param accountNumber
     */
    @Override
    public void restate(long accountNumber) {
        int acc[]= Reader1(accountNumber+".txt");
        acc[2]=0;
        write1(acc,accountNumber);
    }
    @Override
    public void GiveNotice() {

    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
