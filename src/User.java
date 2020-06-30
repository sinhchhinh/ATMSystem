import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
  private String fname;
  private String lname;
  private String uuid;
  /**
   * The MD5 hasing user pin number
   */
  private byte pinHash []; 

  /**
   * The list of accounts for a user
   */
  private ArrayList<Account> acounts;

  /**
   * Create a new user 
   * @param fname the user's first name
   * @param lname the user's last name
   * @param pin the user's account pin number
   * @param bank the Bank object that the user is a customer of 
   */
  public User (String fname, String lname, String pin, Bank bank) {
    this.fname = fname;
    this.lname = lname;

    //Hashing the pin using MD5 algorithm
    try
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      this.pinHash = md.digest(pin.getBytes());
    } 
    catch (NoSuchAlgorithmException e) 
    {
      // TODO Auto-generated catch block
      System.out.println("Error, caught NoSouchAlogirthmException");
      e.printStackTrace();
      System.exit(1);
    }

    //Get a new, unique universal ID for the user
    this.uuid = bank.getNewUserUUID();

    //Create empty list of accounts
    this.acounts = new ArrayList<Account>();

    //Print log message
    System.out.printf("New user %s, %s with ID %s created.\n", lname,fname, this.uuid);


  }

  /**
   * Add an account for the user
   * @param anAcct
   */
  public void addAccount (Account anAcct){
    this.acounts.add(anAcct);
  }
  public String getUUID() {
    return uuid;
  }

  /**
   * Check whether a given pin matches the true User pin
   * @param pin the pin to check
   * @return whether the pin is validate
   */
  public boolean validatePin(String pin) {

    try 
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);

    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      System.out.println("Error, caught NoSouchAlogirthmException");
      e.printStackTrace();
    }

    return false;
  }
  
  /**
   * Return the user first name
   * @return the first name
   */
  public String getFname () {
    return fname;
  }
  
  public void printAccSummary () {
    System.out.printf("\n\n%s's accounts summary\n", this.fname);
    for (int idx = 0 ; idx < this.acounts.size(); idx++){
      System.out.printf("%d) %s\n", idx+1,this.acounts.get(idx).getSummaryLine());
    }
  }
  
  /**
   * Get the number of accounts  of the user
   * @return the number of accounts
   */
  public int numAccounts () {
    return this.acounts.size();
  }

  /**
   * Print transaction history for a particular account
   * @param accIdx - the index of the account to use
   */
  public void printTransHistory (int accIdx){
    this.acounts.get(accIdx).printTransHistory ();
  }
  
  /**
   * Get the balance of a particular account
   * @param acctIdx the index of the account to use
   * @return the balance of the account
   */
  public double getAccountBalance (int acctIdx){
    return this.acounts.get(acctIdx).getBalance();
  }
  
  /**
   * Get the UUID of a particular account
   * @param acctIdx the index of the account to use
   * @return  the UUID of the account
   */
  public String getAcctUUID(int acctIdx){
    return this.acounts.get(acctIdx).getUUID();
  }
  
  /**
   * Add a transaction to a particular account
   * @param acctIdx the index of the account
   * @param amount  the amount of the transaction
   * @param memo    the memo of the transaction
   */
  public void addAccTransaction(int acctIdx, double amount, String memo){
    this.acounts.get(acctIdx).addTransaction(amount, memo);
  }
}

