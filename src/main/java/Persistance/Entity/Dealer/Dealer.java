package Persistance.Entity.Dealer;


public class Dealer
{
    // dealer info
    private final String dealerID;
    private final String dealerName;
    private final String dealerHouseNumber;
    private final String dealerStreetName;
    private final String dealerNumber;
    private final Boolean isContinuing;

    
    // constructor
    Dealer(String dealerID, String dealerName, String dealerHouseNumber, String dealerStreetName, String dealerNumber, Boolean isCotinuing)
    {
        this.dealerID = dealerID;
        this.dealerName = dealerName;
        this.dealerHouseNumber = dealerHouseNumber;
        this.dealerStreetName = dealerStreetName;
        this.dealerNumber = dealerNumber;
        this.isContinuing = isCotinuing;
    }


    // get
    public String getDealerID()
    {
        return dealerID;
    }

    
    public String getDealerName()
    {
        return dealerName;
    }

    
    public String getDealerHouseNumber()
    {
        return dealerHouseNumber;
    }

    
    public String getDealerStreetName()
    {
        return dealerStreetName;
    }

    
    public String getDealerNumber()
    {
        return dealerNumber;
    }

    
    public Boolean getIsContinuing()
    {
        return isContinuing;
    }
}
