package Persistance.ReadFile;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Persistance.Entity.Account.Account;
import Persistance.Entity.Account.AccountType;
import Persistance.Tool.IReadFile;
import Persistance.Tool.ReadFile;
import Persistance.Tool.ReadFileTool;
import Presentation.Tool.Color;
import Presentation.Tool.Message;
import Tool.CheckNullOrBlank;
import Tool.PatternCheck;


public class ReadAccountFile implements IReadDatabase<Account>
{
    // const format
    private final String USERNAME_FORMAT = "^E\\d{3}$";
    private final String PASSWORD_FORMAT = "^[\\D]";   // password does not start with number
    
    
    // delim
    private final String DELIM = ";";
    
    
    // read accounts file
    @Override
    public ArrayList<Account> ReadDatabase(String filePath)
    {
        // check file path
        if (filePath == null)
        {
            // cannot get accounts file
            Message.showMessage("Error. Cannot get accounts file", Color.RED);
            System.exit(1);
        }
        
        
        // read file as array list of string
        IReadFile readFile = new ReadFile();
        ArrayList<String> fileContent = readFile.Read(filePath);
        
        if (fileContent == null)
        {
            // error file is empty
            Message.showMessage("Error. Empty file\n", Color.RED);
            return null;
        }
        
        
        // convert string into account object
        ArrayList<Account> accounts = new ArrayList<>();
        
        try
        {
            String line = ReadFileTool.GetNextLine(fileContent);
            
            
            // check empty file
            if (line == null)
            {
                // error file is blank
                Message.showMessage("Error. Empty file\n", Color.RED);
                return null;
            }
                        
            
            // start reading
            while (line != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(line, DELIM);
                String[] account = new String[]
                    {
                        ReadFileTool.GetNextToken(tokenizer),    // 0. username
                        ReadFileTool.GetNextToken(tokenizer),    // 1. password
                        ReadFileTool.GetNextToken(tokenizer)     // 2. account type
                    };
                
                
                // check account format
                if (CheckAccount(account) == false)
                {
                    // error wrong account format
                    line = ReadFileTool.GetNextLine(fileContent);
                    continue;
                }
                
                
                // translate account role
                switch (account[2])
                {
                    case "Boss" -> accounts.add(new Account(account[0], account[1], AccountType.Boss));
                    
                    case "Dealer" -> accounts.add(new Account(account[0], account[1], AccountType.Dealer));
                    
                    case "Delivery" -> accounts.add(new Account(account[0], account[1], AccountType.Delivery));
                    
                    default ->
                    {
                        // error account format is wrong
                    }
                }
                
                line = ReadFileTool.GetNextLine(fileContent);
            }
        }
        catch (Exception e)
        {
            // error reading the file
            Message.showMessage("Error. Cannot read file\n", Color.RED);
            return null;
        }
        
        
        // sort, check, then return accounts database
        Collections.sort(accounts, (account1, account2) ->
            {
                return account1.getUsername().compareTo(account2.getUsername());
            });
        
        if (CheckDuplicateAccount(accounts) != true)
        {
            // has duplicate account
            return null;
        }
        
        return accounts;
    }
    
    
    // check account
    private Boolean CheckAccount(String[] account)
    {
        Boolean isNullOrBlank = false;
        Boolean isAccountUsernameValid = false;
        Boolean isAccountPasswordValid = false;
        
        try
        {
            isAccountUsernameValid = PatternCheck.Check(USERNAME_FORMAT, account[0]) == true;
            isAccountPasswordValid = PatternCheck.Check(PASSWORD_FORMAT, account[1]) == true;
            
            for (String item : account)
            {
                if (CheckNullOrBlank.Check(item) == true)
                {
                    isNullOrBlank = true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            // error null pointer
        }
        
        return isNullOrBlank == false
            && isAccountUsernameValid == true
            && isAccountPasswordValid == true;
    }

    
    // check for duplicate account
    private Boolean CheckDuplicateAccount(ArrayList<Account> accounts)
    {
        Iterator<Account> deliveriesIterator = accounts.iterator();
        String checkID = deliveriesIterator.hasNext() == true ?
            deliveriesIterator.next().getUsername() : null;
        
        while (checkID != null)
        {
            String afterCheckID = deliveriesIterator.hasNext() == true ?
                deliveriesIterator.next().getUsername() : null;
            
            if (checkID.equals(afterCheckID) == true)
            {
                // has duplicate
                return false;
            }
            
            checkID = afterCheckID;
        }
        
        // no duplicate
        return true;
    }
}
