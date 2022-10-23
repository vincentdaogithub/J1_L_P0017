package Business;

import Business.DealerManagerPkg.*;
import Persistance.Entity.Account.AccountType;
import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class Business
{
    // delegate business
    public void BusinessDelegate(AccountType type)
    {
        switch (type)
        {
            case Boss ->
            {
                Message.showMessage("To be implemented\n", Color.BLUE);
            }

            case Dealer ->
            {
                IDealerManager dealerManager = new DealerManager();
                dealerManager.DealerManagerMenu();
            }

            case Delivery ->
            {
                Message.showMessage("To be implemented\n", Color.BLUE);
            }
        }
    }
}
