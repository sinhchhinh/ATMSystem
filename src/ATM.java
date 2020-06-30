import java.awt.EventQueue;
import java.util.Scanner;

public class ATM 
{


  public static void main(String[] args) {
    // TODO Auto-generated method stub
    EventQueue.invokeLater(new Runnable() 
    {
      @Override
      public void run() 
      {
        try 
        {
          ATMUI window = new ATMUI();
          window.frame.setVisible(true);

        } catch (Exception e) 
        {
          e.printStackTrace();
        }
      }

    });

    Scanner sc = new Scanner(System.in);

    //Init bank
    Bank theBank = new Bank("Bank of America");

    //Add a user to the bank
    User user = theBank.addUser("Sinhchhinh", "Lor", "1234");

    //Adding a checking account for the user
    Account checkingAcc = new Account("Checking", user, theBank);

    //Adding the checking account to the user and the bank
    user.addAccount(checkingAcc);
    theBank.addAccount(checkingAcc);

    ///Creating a login prompt
    User curUsr;
    while (true){
      ///stay in the login prompt until successful login 
      curUsr = ATM.mainMenuPrompt (theBank, sc);

      ///stay in the mmain menu until user quit
      ATM.printUsrMenu(curUsr, sc);
    }

  }

  /**
   * Print the ATM's login menu
   * @param theBank the Bank object whose account to use
   * @param sc the Scanner object to use for user input
   * @return
   */
  public static User mainMenuPrompt (Bank theBank, Scanner sc){
    //init
    String userID;
    String pin;
    User authUsr;

    //Prompt the user for user ID/pin combo until a correct one is reached 
    do 
    {
      System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
      System.out.println("Enter user ID");
      userID = sc.nextLine();
      System.out.printf("Enter pin:\n");
      pin = sc.nextLine();

      //try to get the user object corresponding to  the ID and pin combo
      authUsr = theBank.userLogin(userID, pin);
      if (authUsr == null) {
        System.out.println("Incorrect user ID/pin combination" + "Plese try again");
      }

    } while (authUsr == null); //continue looping until successful login 
    return authUsr;
  }

  public static void printUsrMenu (User theUsr, Scanner sc) {
    //print a summary of the user's accounts
    theUsr.printAccSummary();

    //init
    int choice;

    //user menu

    do {
      System.out.printf("Welcome %s, what would you like to do?\n",
          theUsr.getFname());
      System.out.println("1) SHow account transaction History");
      System.out.println("2) Withdraw");
      System.out.println("3) Deposit");
      System.out.println("4) Transfer");
      System.out.println("5) Quit");
      System.out.println();
      System.out.println("Enter choice: ");
      choice = sc.nextInt();

      if (choice < 1 || choice > 5 ) {
        System.out.println("Invalid choicje.Please choose 1-5");

      }

    } while (choice < 1 || choice > 5);

    //Processing the choice
    switch (choice){
      case 1 :
        ATM.showTransHistory(theUsr, sc);
        break;

      case 2:
        ATM.widthdrawFunds(theUsr, sc);
        break;

      case 3:
        ATM.depositFunds(theUsr, sc);
        break;
      case 4:
        ATM.transferFunds(theUsr, sc);
        break;
      case 5:
        sc.nextLine();
        break;

    }
    // redisplay the menu unless the user wants to quit
    if (choice != 5 ){
      ATM.printUsrMenu(theUsr, sc);
    }
  }

  /**
   * Show the transaction history for an account
   * @param theUsr the logged in User object
   * @param sc     the Scanner object used for user input
   */
  public static void showTransHistory(User theUsr, Scanner sc){
    int theAcc;
    do {
      // get account whose transaction history to look at
      System.out.printf("Enter the nummber (1-%d) of the account" 
          + "whose transactions you want to see: ",
          theUsr.numAccounts() );
      theAcc = sc.nextInt()-1;
      if(theAcc < 0 || theAcc >= theUsr.numAccounts()){
        System.out.println("Invalid account. Please try agains");
      }
    } while (theAcc < 0 || theAcc >= theUsr.numAccounts());

    //Print the transaction history
    theUsr.printTransHistory(theAcc);
  }


  public static void transferFunds (User theUsr, Scanner sc){
    //inits
    int fromAcc;
    int toAcc;
    double amount;
    double accBal;

    // get the account to transfer from 
    do {
      System.out.printf("Enter the number (1-%d) of the account\n" +
          "to deposit in: ");
      fromAcc = sc.nextInt()-1;
      if (fromAcc < 0 || fromAcc >= theUsr.numAccounts()){
        System.out.println("Invalid amount. Please try agains");
      }
    } while (fromAcc < 0 || fromAcc >= theUsr.numAccounts());
    accBal = theUsr.getAccountBalance(fromAcc);

    //Get the account to transfer to 
    do {
      System.out.printf("Enter the number (1-%d) of the account\n" +
          "to transfer from: ");
      toAcc = sc.nextInt()-1;
      if (toAcc < 0 || toAcc >= theUsr.numAccounts()){
        System.out.println("Invalid amount. Please try agains");
      }
    } while (toAcc < 0 || toAcc >= theUsr.numAccounts());
    accBal = theUsr.getAccountBalance(toAcc);

    //Get the amount to transfer 
    do {
      System.out.printf("Enter the amount to transfer (max $%.02f): $", accBal);
      amount = sc.nextDouble();
      if (amount < 0 )
      {
        System.out.println("Amount must be greater than zero");
      } else if (amount > accBal)
      {
        System.out.printf("Amount must not be greater than\n"+
            "balance of $%.02f.\n", accBal);
      }

    } while (amount < 0 || amount > accBal);

    //finally, do the transfer
    theUsr.addAccTransaction (fromAcc,
        -1* amount, 
        String.format("Transfer to account %s", theUsr.getAcctUUID(toAcc)));

    theUsr.addAccTransaction (toAcc,
        -1* amount, 
        String.format("Transfer to account %s", theUsr.getAcctUUID(fromAcc)));
  }

  /**
   * Process a fund withdraw from an account
   * @param theUsr the logged-in User object
   * @param sc the Scanner object user for user input
   */
  public static void widthdrawFunds (User theUsr, Scanner sc){

    int fromAcc;
    int toAcc;
    double amount;
    double accBal;
    String memo;

    // get the account to withdraw from 
    do {
      System.out.printf("Enter the number (1-%d) of the account\n" +
          "to widraw from: ", theUsr.numAccounts());
      fromAcc = sc.nextInt()-1;
      if (fromAcc < 0 || fromAcc >= theUsr.numAccounts()){
        System.out.println("Invalid amount. Please try agains");
      }
    } while (fromAcc < 0 || fromAcc >= theUsr.numAccounts());
    accBal = theUsr.getAccountBalance(fromAcc);

    //Get the amount to withdraw 
    do 
    {
      System.out.printf("Enter the amount to withdraw (max $%.02f): $", accBal);
      amount = sc.nextDouble();

      if (amount < 0 ) {
        System.out.println("Amount must be greater than zero");
      } else if (amount > accBal){
        System.out.printf("Amount must not be greater than\n"+
            "balance of $%.02f.\n", accBal);
      }

    } while (amount < 0 || amount > accBal);

    sc.nextLine();
    System.out.println("Enter a memo: ");
    memo = sc.nextLine();
    theUsr.addAccTransaction(fromAcc, -1 * amount, memo);

  }

  /**
   * Process a fund deposit to an account
   * @param theUsr the logged-in User object
   * @param sc     the Scanner object used for user input
   */
  public static void  depositFunds (User theUsr, Scanner sc) {
    //init
    int toAcc;
    double amount;
    double accBal;
    String memo;

    // get the account to deposit to 
    do 
    {
      System.out.printf("Enter the number (1-%d) of the account\n" +
          "to deposit to: ", theUsr.numAccounts());
      
      toAcc = sc.nextInt()-1;
      if (toAcc < 0 || toAcc >= theUsr.numAccounts())
      {
        System.out.println("Invalid amount. Please try agains");
      }
      
    } while (toAcc < 0 || toAcc >= theUsr.numAccounts());
    accBal = theUsr.getAccountBalance(toAcc);

    //Get the amount to transfer 
    do 
    {
      System.out.printf("Enter the amount to deposit (max $%.02f): $", accBal);
      amount = sc.nextDouble();
      if (amount < 0 ){
        System.out.println("Amount must be greater than zero");
      } 

    } while (amount < 0 );

    //gobble up rest of the previous input
    sc.nextLine();
    System.out.println("Enter a memo: ");
    memo = sc.nextLine();
    theUsr.addAccTransaction(toAcc,  amount, memo);
  }

}
