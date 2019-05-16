package FFF;

/**
 *  control interface
 *  @author Wang Zeshan
 *  @version 1.0
 */
public interface  Bank_Control {

    public void addCustomer();
    public void confirmCreditStatus();
    public void clearFunds(long x);
    public void GiveNotice();
    public void Withdrawal(long accountNumber);
    public void suspend(long accountNumber);
    public void restate(long accountNumber);
    public void closeAccount(long accountNumber);


}
