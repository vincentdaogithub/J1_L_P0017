package Business.DealerManagerPkg.DealerOperation;

import java.util.Collections;

import Business.DealerManagerPkg.Tool.CheckDealer;
import Persistance.Database;
import Persistance.Entity.Dealer.Dealer;
import Persistance.Entity.Dealer.DealerBuilder;
import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.util.ArrayList;
import Tool.AskForInput;
import Tool.CheckNullOrBlank;
import Tool.PatternCheck;


public class UpdateDealer implements IOperation
{
    // const format
    private final String TRUE = "^true$";
    
    
    // get user input to update
    @Override
    public void Operate()
    {
        // check if database is empty
        ArrayList<Dealer> tmp = Database.GetDatabase().GetDealerDatabase();
        
        if (tmp == null || tmp.isEmpty() == true)
        {
            // empty database
            Message.showMessage("Error. Empty database\n", Color.RED);
            return;
        }


        Message.showMessage("UPDATE A DEALER BY ID", Color.YELLOW_BACKGROUND);


        // get dealer ID
        String dealerID = AskForInput.Ask("Enter dealer's ID: ");

        if (CheckDealer.CheckDealerID(dealerID) != true)
        {
            // invalid dealerID
            Message.showMessage("Invalid dealer's ID", Color.RED);
            return;
        }
        else if (CheckDealer.CheckDealerAlreadyExist(dealerID) != true)
        {
            // dealer does not exist to update
            Message.showMessage("Dealer does not exist!", Color.RED);
            return;
        }
        

        Message.showMessage("Found dealer!", Color.GREEN);
        Message.showMessage("Update initialized...\n", Color.GREEN);


        // get updated dealer ID
        String dealerIDUpdate = AskForInput.Ask("Enter dealer's ID: ");

        if (CheckDealer.CheckDealerID(dealerIDUpdate) != true)
        {
            // invalid dealerID
            Message.showMessage("Invalid dealer's ID\n", Color.RED);
            return;
        }
        

        // get updated dealer name
        String dealerNameUpdate = AskForInput.Ask("Enter dealer's name: ");
        
        if (CheckNullOrBlank.Check(dealerNameUpdate) == true)
        {
            // invalid dealer name
            Message.showMessage("Invalid dealer's name\n", Color.RED);
            return;
        }
        

        // get updated dealer house number
        String dealerHouseNumberUpdate = AskForInput.Ask("Enter dealer's house number: ");
        
        if (CheckDealer.CheckDealerHouseNumber(dealerHouseNumberUpdate) != true)
        {
            // invalid dealer house
            Message.showMessage("Invalid dealer's house number\n", Color.RED);
            return;
        }
        

        // get updated dealer street name
        String dealerStreetUpdate = AskForInput.Ask("Enter dealer's street name: ");
        
        if (CheckNullOrBlank.Check(dealerStreetUpdate) == true)
        {
            // invalid dealer street
            Message.showMessage("Invalid dealer's street\n", Color.RED);
            return;
        }


        // get updated dealer phone number
        String dealerPhoneNumberUpdate = AskForInput.Ask("Enter dealer's phone number: ");
        
        if (CheckDealer.CheckDealerPhoneNumber(dealerPhoneNumberUpdate) != true)
        {
            // invalid dealer phone number
            Message.showMessage("Invalid dealer's phone number\n", Color.RED);
            return;
        }


        // get updated dealer status
        String dealerStatusUpdate = AskForInput.Ask("Enter dealer's status: ");

        if (CheckDealer.CheckDealerStatus(dealerStatusUpdate) != true)
        {
            // invalid dealer status
            Message.showMessage("Invalid dealer's status\n", Color.RED);
            return;
        }


        // convert user status to boolean
        Boolean dealerStatusBoolean = PatternCheck.Check(TRUE, dealerStatusUpdate) == true;
        

        // add dealer
        Dealer dealer = new DealerBuilder().
            SetDealerID(dealerIDUpdate).
            SetDealerName(dealerNameUpdate).
            SetDealerHouseNumber(dealerHouseNumberUpdate).
            SetDealerStreetName(dealerStreetUpdate).
            SetDealerNumber(dealerPhoneNumberUpdate).
            SetDealerStatus(dealerStatusBoolean).
            Build();

        
        Boolean isUpdate = UpdateDealerToDatabase(dealer, dealerID);
        
        if (isUpdate == true)
        {
            // update successful
            Message.showMessage("Update successful!\n", Color.GREEN);
        }
        else
        {
            // failed to update
            Message.showMessage("Error. Cannot update\n", Color.RED);
        }
    }
    
    
    // add dealer to database
    private Boolean UpdateDealerToDatabase(Dealer dealer, String dealerIDToUpdate)
    {
        ArrayList<Dealer> dealers = Database.GetDatabase().GetDealerDatabase();

        for (Dealer traverseDealer : dealers)
        {
            if (traverseDealer.getDealerID().equals(dealerIDToUpdate) == true)
            {
                dealers.set(dealers.indexOf(traverseDealer), dealer);
                
                Collections.sort(dealers, (dealer1, dealer2) ->
                    {
                        return dealer1.getDealerID().compareTo(dealer2.getDealerID());
                    });
                
                return true;
            }
        }
        
        return false;
    }
}
