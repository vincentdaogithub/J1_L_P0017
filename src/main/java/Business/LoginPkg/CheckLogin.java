package Business.LoginPkg;

import Persistance.Database;
import Persistance.Entity.Account.Account;
import Persistance.Entity.Account.AccountType;
import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class CheckLogin implements ICheckLogin
{
    // check login using database
    @Override
    public AccountType Check(String[] login)
    {
        for (Account account : Database.GetDatabase().GetAccountDatabase())
        {
            if (account.getUsername().equals(login[0]) && account.getPassword().equals(login[1]))
            {
                Message.showMessage("Login successful!\n", Color.GREEN);
                return account.getAccountType();
            } 
        }

        Message.showMessage("Error. Account does not exist\n", Color.RED);
        return null;
    }
}
