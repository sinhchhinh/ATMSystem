import java.util.ArrayList;
import java.util.Date;

public class Transaction {
  
  private double amount;
  private Date timestamp;
  private String memo;
  /**
   * The account that the transaction performed
   */
  private Account inAccount;
  
  public Transaction (double amount, Account inAccount){
    this.amount = amount;
    this.inAccount = inAccount;
    this.timestamp = new Date();
    this.memo = "";
  }
  /**
   * Create a new transaction
   * @param amount the transaction amount
   * @param memo
   * @param inAccount the account the transaction belongs to 
   */
  public Transaction (double amount, String memo, Account inAccount){
    //calling two-arg constructor first
    this(amount, inAccount);
    //Setting the memo
    this.memo = memo;
  
  }
  /**
   * Get the amount of the transaction
   * @return the amount
   */
  public double getAmount () {
    return this.amount;
  }
  
  /**
   * Get a string summarizing the transaction
   * @return the summary string
   */
  public String getSummaryLine() {
    
    return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
        -this.amount,this.memo );
  }
}
