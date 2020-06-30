import java.util.ArrayList;
import java.util.Random;

public class Bank {

  private String name;
  private ArrayList<User> users;
  private ArrayList<Account> accounts;

  /**
   * Create a new Bank object with empty lists of users and accounts
   * @param name
   */
  public Bank(String name){
    this.name = name;
    this.users = new ArrayList<User>();
    this.accounts = new ArrayList<Account>();
    }
  
  public String getNewUserUUID (){
    String uuid;
    Random random = new Random();
    int len = 6;
    boolean nonUnique;

    //continue looping until the id is unique;
    do{
      //generating the number
      uuid = "";
      for (int idx = 0; idx < len; idx++){
        uuid += ((Integer)random.nextInt(10)).toString();
      }

      //check if uuid is unique
      nonUnique = false;
      for (User currUsr : this.users){
        if (uuid.compareTo(currUsr.getUUID()) == 0){
          nonUnique = true;
          break;
        }
      }
    } while (nonUnique) ;
    return uuid;
  }

  public String getNewAccountUUID (){
    String accUUID;
    int len = 10;
    Random newAccRandom = new Random();
    boolean isAccUUIDunique;

    do 
    {
      accUUID = "";
      for (int idx = 0; idx < len; idx++){
        accUUID += ((Integer)newAccRandom.nextInt(10)).toString();
      }
      isAccUUIDunique = false;
      for (Account eachAccUUID : this.accounts){
        if (accUUID.compareTo(eachAccUUID.getUUID()) == 0){
          isAccUUIDunique = true;
          break;
        }
      }
    } while (isAccUUIDunique);


    return accUUID;
  }

  public void addAccount (Account anAcct){
    this.accounts.add(anAcct);
  }

/***
 * Create a new user of the bank
 * @param fname the user's first name
 * @param lname the user's last name
 * @param pin the user's pin
 * @return the new User object
 */
  public User addUser(String fname, String lname, String pin){
    //create a new User object and add it to the list
    User newUser = new User(fname, lname, pin, this);
    this.users.add(newUser);
    
    ///Create a saving account for the user
    Account newAccount = new Account("Savings", newUser, this );
    newUser.addAccount(newAccount); //Using this to add the same account its not  a copy (for holder, bank) in each list
    this.accounts.add(newAccount); //this = bank
    return newUser;
  }
  
  public User userLogin (String userID, String pin){
    
    //search through list of users
    for (User eachUsr : this.users){
      //check user ID is correct
      if(eachUsr.getUUID().compareTo(userID) == 0 && eachUsr.validatePin(pin)){
        return eachUsr;
      }
    }
    return null;
  }
  /**
   * Getting the bank name
   * @return the bank name
   */
  public String getName (){
    return this.name;
  }
  
}
