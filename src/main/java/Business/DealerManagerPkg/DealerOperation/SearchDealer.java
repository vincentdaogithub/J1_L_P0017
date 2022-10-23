package Business.DealerManagerPkg.DealerOperation;

import Business.DealerManagerPkg.Tool.PrintDealer;
import java.util.ArrayList;
import Business.DealerManagerPkg.Tool.CheckDealer;
import Persistance.Database;
import Persistance.Entity.Dealer.Dealer;
import Presentation.Tool.Color;
import Presentation.Tool.Message;
import Tool.AskForInput;


public class SearchDealer implements IOperation
{
    // get user input to search for dealer
    @Override
    public void Operate()
    {
        // check if database is empty
        ArrayList<Dealer> tmp = Database.GetDatabase().GetDealerDatabase();
        
        if (tmp == null || tmp.isEmpty() == true)
        {
            // empty database
            Message.showMessage("Empty database!\n", Color.RED);
            return;
        }
        

        Message.showMessage("SEARCH A DEALER BY ID", Color.YELLOW_BACKGROUND);
        

        // get dealer ID
        String dealerID = AskForInput.Ask("Enter dealer's ID: ");
        
        if (CheckDealer.CheckDealerID(dealerID) != true)
        {
            // input is empty
            Message.showMessage("Invalid input\n", Color.RED);
            return;
        }


        // search for dealer
        SearchDealerFromDatabase(dealerID);
    }
    
    
    // search from database
    private void SearchDealerFromDatabase(String dealerID)
    {
        for (Dealer dealer : Database.GetDatabase().GetDealerDatabase())
        {
            // if found
            if (dealer.getDealerID().equals(dealerID) == true)
            {
                Message.showMessage("Found!\n", Color.GREEN);
                PrintDealer.Print(dealer);
                return;
            }
        }
        
        // if cannot found
        Message.showMessage("Cannot found\n", Color.RED);
    }
}
