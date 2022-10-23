package Business.DealerManagerPkg.Tool;

import Persistance.Database;
import Persistance.Entity.Dealer.Dealer;
import Tool.CheckNullOrBlank;
import Tool.PatternCheck;

public class CheckDealer
{
    // const format
    private static final String DEALER_ID = "^D\\d{3}$";
    private static final String DEALER_PHONENUMBER = "^\\d{9}$|^\\d{11}$";
    private static final String DEALER_HOUSENUMBER = "[[\\W_]&&[^/]]";
    private static final String TRUE = "^true$";
    private static final String FALSE = "^false$";


    // check validity of dealer ID
    public static Boolean CheckDealerID(String dealerID)
    {
        Boolean isNullOrBlank = CheckNullOrBlank.Check(dealerID);
        Boolean isDealerIDFormatValid = PatternCheck.Check(DEALER_ID, dealerID) == true;
        
        return isNullOrBlank != true && isDealerIDFormatValid == true;
    }


    // check validity of dealer house number
    public static Boolean CheckDealerHouseNumber(String dealerHouseNumber)
    {
        Boolean isNullOrBlank = CheckNullOrBlank.Check(dealerHouseNumber);
        Boolean isDealerHouseNumberFormatValid = PatternCheck.Check(DEALER_HOUSENUMBER, dealerHouseNumber) != true;

        return isNullOrBlank != true && isDealerHouseNumberFormatValid == true;
    }


    // check validity of dealer phone number
    public static Boolean CheckDealerPhoneNumber(String dealerPhoneNumber)
    {
        Boolean isNullOrBlank = CheckNullOrBlank.Check(dealerPhoneNumber);
        Boolean isDealerPhoneNumberValid = PatternCheck.Check(DEALER_PHONENUMBER, dealerPhoneNumber) == true;

        return isNullOrBlank != true && isDealerPhoneNumberValid == true;
    }


    // check validity of dealer status
    public static Boolean CheckDealerStatus(String dealerStatus)
    {
        Boolean isNullOrBlank = CheckNullOrBlank.Check(dealerStatus);
        Boolean isDealerStatusValid = PatternCheck.Check(TRUE, dealerStatus)
            || PatternCheck.Check(FALSE, dealerStatus);

        return isNullOrBlank != true && isDealerStatusValid == true;
    }
    
    
    // check for the existence of dealer before adding
    public static Boolean CheckDealerAlreadyExist(String dealerID)
    {
        for (Dealer dealer : Database.GetDatabase().GetDealerDatabase())
        {
            if (dealer.getDealerID().equals(dealerID) == true)
            {
                // duplicate dealer
                return true;
            }
        }
        
        return false;
    }
}