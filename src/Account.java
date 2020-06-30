import java.util.ArrayList;

public class Account {
  
  private String accName;
  
  /**
   * Account ID
   */
  private String uuid;
  private User holder;
  private ArrayList<Transaction> transactions;
  
  public Account (String accName, User holder, Bank bank) {
    this.accName = accName;
    this.holder = holder;
    
    //Getting UUID for the account
    this.uuid = bank.getNewAccountUUID();
    
    //Initialize transaction
    this.transactions =  new ArrayList<Transaction>();
    
   
    
  }
  /**
   * Get summary line for the account 
   * @return the string summary 
   */
  public String getSummaryLine () {
    //get the account's balance
    double balance = this.getBalance();
    //format the summary line, depdending on whether the balance is negative
   
    if (balance >= 0) {
      return String.format("%s : $%.02f : %s", this.uuid, balance, this.accName);
    } else {
      return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.accName);

    }
    
  }
  
  /**
   * Get the balance of this account by adding the amounts of the transactions
   * @return the balance value
   */
  public double getBalance () {
    double balance = 0;
    for (Transaction t : this.transactions){
      balance +=t.getAmount ();
    }
    return balance;
  }
  

  public String getUUID(){
    return uuid;
  }
  
  /**
   * Print the transaction history of the account
   */
   public void printTransHistory(){
     System.out.printf("\nTransaction history for account %s\n", this.uuid);
     for (int t = this.transactions.size()-1; t >= 0; t--){
       System.out.println(this.transactions.get(t).getSummaryLine());
     }
     System.out.println();
   }
   
   /**
    * Add a new transaction in this account
    * @param amount the amount transacted
    * @param memo the transaction memo
    */
   public void addTransaction(double amount, String memo){
     //Create new transaction object and add it to our list
     Transaction newTrans = new Transaction(amount, memo, this); //this = the current bank
     this.transactions.add(newTrans);
   }
}
