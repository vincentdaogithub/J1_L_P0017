import Business.Business;
import Business.LoginPkg.*;
import Persistance.Entity.Account.AccountType;
import Presentation.Tool.*;
import Tool.CheckNullOrBlank;
import Tool.ClearConsole;
import Tool.ReadInput;


public class Program
{
    public static void main(String[] args)
    {
        ClearConsole.Clear();

        Business business = new Business();
        
        while (true)
        {
            Message.showMessage("Welcome to Dealer Management Program\n", Color.GREEN);

            
            // login menu
            System.out.println("===============================================");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your option: ");

            
            // get user input
            String userInput = ReadInput.Read();

            if (CheckNullOrBlank.Check(userInput) == true)
            {
                Message.showMessage("No input was founded\n", Color.RED);
                continue;
            }

            
            // translate user input
            switch (userInput)
            {
                // login option
                case "1" ->
                {
                    ILogin userLogin = new Login();
                    String[] userLoginInformation = userLogin.ReadLogin();

                    if (userLoginInformation == null)
                    {
                        // get login failed (reported)
                        Message.showMessage("Cannot get user login\n", Color.RED);
                        continue;
                    }

                    ClearConsole.Clear();
                    
                    ICheckLogin checkLogin = new CheckLogin();
                    AccountType accountType = checkLogin.Check(userLoginInformation);

                    if (accountType == null)
                    {
                        // account does not exist (reported)
                        Message.showMessage("Account does not exist\n", Color.RED);
                        continue;
                    }

                    switch (accountType)
                    {
                        case Boss ->
                        {
                            Message.showMessage("To be implemented\n", Color.BLUE);
                        }

                        case Dealer ->
                        {
                            business.BusinessDelegate(AccountType.Dealer);
                        }

                        case Delivery ->
                        {
                            Message.showMessage("To be implemented\n", Color.BLUE);
                        }

                        default ->
                        {
                            Message.showMessage("Unknown account type!\n", Color.RED);
                        }
                    }
                }

                
                // quit option
                case "2" ->
                {
                    Message.showMessage("Exiting program...\n", Color.GREEN);
                    System.exit(0);
                }

                
                // invalid input
                default ->
                {
                    Message.showMessage("Invalid input\n", Color.RED);
                    continue;
                }
            }
        }
    }
}
