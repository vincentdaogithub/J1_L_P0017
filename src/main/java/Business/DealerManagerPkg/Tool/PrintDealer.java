package Business.DealerManagerPkg.Tool;

import Persistance.Entity.Dealer.Dealer;


public class PrintDealer
{
    private final static String FORMAT = "| %-4s | %-30s | %-10s | %-30s | %-15s | %-6s |";


    // print single dealer
    public static void Print(Dealer dealer)
    {
        System.out.println(String.format(
            FORMAT, 
            dealer.getDealerID(),
            dealer.getDealerName(),
            dealer.getDealerHouseNumber(),
            dealer.getDealerStreetName(),
            dealer.getDealerNumber(),
            dealer.getIsContinuing()));

        System.out.println();
    }
}
