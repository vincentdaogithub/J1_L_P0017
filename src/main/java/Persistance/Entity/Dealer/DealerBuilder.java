package Persistance.Entity.Dealer;


public class DealerBuilder
{
    // dealer info
    private String dealerID;
    private String dealerName;
    private String dealerHouseNumber;
    private String dealerStreetName;
    private String dealerNumber;
    private Boolean isContinuing;
    
    
    // build the dealer
    public Dealer Build()
    {
        return new Dealer(dealerID,
            dealerName,
            dealerHouseNumber,
            dealerStreetName,
            dealerNumber,
            isContinuing);
    }
    
    
    // set dealer information
    public DealerBuilder SetDealerID(String dealerID)
    {
        this.dealerID = dealerID;
        return this;
    }
    
    
    public DealerBuilder SetDealerName(String dealerName)
    {
        this.dealerName = dealerName;
        return this;
    }
    
    
    public DealerBuilder SetDealerHouseNumber(String dealerHouseNumber)
    {
        this.dealerHouseNumber = dealerHouseNumber;
        return this;
    }
    
    
    public DealerBuilder SetDealerStreetName(String dealerStreetName)
    {
        this.dealerStreetName = dealerStreetName;
        return this;
    }
    
    
    public DealerBuilder SetDealerNumber(String dealerNumber)
    {
        this.dealerNumber = dealerNumber;
        return this;
    }
    
    
    public DealerBuilder SetDealerStatus(Boolean isContinuing)
    {
        this.isContinuing = isContinuing;
        return this;
    }
}
