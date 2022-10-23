package Persistance.ReadFile;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Persistance.Entity.Dealer.Dealer;
import Persistance.Entity.Dealer.DealerBuilder;
import Persistance.Tool.IReadFile;
import Persistance.Tool.ReadFile;
import Persistance.Tool.ReadFileTool;
import Presentation.Tool.Color;
import Presentation.Tool.Message;
import Tool.PatternCheck;


public class ReadDealerFile implements IReadDatabase<Dealer>
{
    // const format
    private final String ID_FORMAT = "^D\\d{3}$";
    private final String PHONE_NUMBER_FORMAT = "^\\d{9}$|^\\d{11}$";
    private final String DEALER_HOUSENUMBER = "[[\\W_]&&[^/]]";
    private final String TRUE = "^true$";
    private final String FALSE = "^false$";
    
    
    // const delim
    private final String DELIM = ";";
    
    
    // read dealers file
    @Override
    public ArrayList<Dealer> ReadDatabase(String filePath)
    {
        // cannot get file path
        if (filePath == null)
        {
            // cannot get accounts file
            Message.showMessage("Error. Cannot get dealers file!", Color.RED);
            System.exit(1);
        }
        

        // get file content as array list of string
        IReadFile readFile = new ReadFile();
        ArrayList<String> fileContent = readFile.Read(filePath);
        
        if (fileContent == null)
        {
            // error file is empty
            Message.showMessage("Error. Empty file\n", Color.RED);
            return null;
        }
        
        
        // convert strings into dealer object
        try
        {
            String line = ReadFileTool.GetNextLine(fileContent);
            
            if (line == null)
            {
                // error file is empty
                Message.showMessage("Error. Empty file\n", Color.RED);
                return null;
            }
            
            ArrayList<Dealer> dealers = new ArrayList<>();
            
            while (line != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(line, DELIM);
                
                String[] dealerInformation = new String[]
                    {
                        ReadFileTool.GetNextToken(tokenizer),   // 0. id
                        ReadFileTool.GetNextToken(tokenizer),   // 1. name
                        ReadFileTool.GetNextToken(tokenizer),   // 2. homeNum
                        ReadFileTool.GetNextToken(tokenizer),   // 3. street
                        ReadFileTool.GetNextToken(tokenizer),   // 4. phoneNum
                        ReadFileTool.GetNextToken(tokenizer),   // 5. is continuing ?
                    };
                
                if (CheckDealer(dealerInformation) == false)
                {
                    // error wrong account format
                    line = ReadFileTool.GetNextLine(fileContent);
                    continue;
                }
                
                
                // convert dealer status to boolean
                Boolean dealerStatusBoolean = PatternCheck.Check(TRUE, dealerInformation[5]) == true;
                
                
                // add dealer
                Dealer dealer = new DealerBuilder().
                    SetDealerID(dealerInformation[0]).
                    SetDealerName(dealerInformation[1]).
                    SetDealerHouseNumber(dealerInformation[2]).
                    SetDealerStreetName(dealerInformation[3]).
                    SetDealerNumber(dealerInformation[4]).
                    SetDealerStatus(dealerStatusBoolean).
                    Build();
                
                dealers.add(dealer);

                line = ReadFileTool.GetNextLine(fileContent);
            }
            
            
            // sort the dealers, then check is there is duplicate
            Collections.sort(dealers, (dealer1, dealer2) ->
                {
                    return dealer1.getDealerID().compareTo(dealer2.getDealerID());
                });
            
            if (CheckDuplicateDealer(dealers) != true)
            {
                // has duplicate dealer
                Message.showMessage("Error. Duplicate dealer. Reject database...\n", Color.RED);
                return null;
            }
            
            return dealers;
        }
        catch (Exception e)
        {
            // error cannot read file
            Message.showMessage("Error. Cannot read file\n", Color.RED);
            return null;
        }
    }
    
    
    // check validity of dealer before adding to database
    private Boolean CheckDealer(String[] dealer)
    {
        Boolean isNullOrBlank = false;
        Boolean isDealerIDValid = false;
        Boolean isDealerHouseNumberValid = false;
        Boolean isDealerPhoneNumberValid = false; 
        Boolean isDealerContinuingValid = false; 
              
        try
        {
            isDealerIDValid = PatternCheck.Check(ID_FORMAT, dealer[0]) == true;
            isDealerPhoneNumberValid = PatternCheck.Check(PHONE_NUMBER_FORMAT, dealer[4]) == true;
            isDealerContinuingValid = PatternCheck.Check(TRUE, dealer[5]) == true
                || PatternCheck.Check(FALSE, dealer[5]) == true;
            
            isDealerHouseNumberValid = PatternCheck.Check(DEALER_HOUSENUMBER, dealer[2]) != true;
            
            for (String item : dealer)
            {
                if (item == null || item.isEmpty())
                {
                    isNullOrBlank = true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            // error null pointer
            Message.showMessage("Error. Null arguments\n", Color.RED);
        }
        
        return isNullOrBlank == false
            && isDealerIDValid == true
            && isDealerPhoneNumberValid == true
            && isDealerContinuingValid == true
            && isDealerHouseNumberValid == true;
    }


    // check duplicate dealer
    private Boolean CheckDuplicateDealer(ArrayList<Dealer> dealers)
    {
        Iterator<Dealer> deliveriesIterator = dealers.iterator();

        String checkID = deliveriesIterator.hasNext() == true ?
            deliveriesIterator.next().getDealerID() : null;
        
        while (checkID != null)
        {
            String afterCheckID = deliveriesIterator.hasNext() == true ?
                deliveriesIterator.next().getDealerID() : null;
            
            if (checkID.equals(afterCheckID) == true)
            {
                // has duplicate
                return false;
            }
            
            checkID = afterCheckID;
        }
        
        return true;
    }
}
