package Persistance;

import java.util.ArrayList;
import Persistance.Entity.Account.Account;
import Persistance.Entity.Dealer.Dealer;
import Persistance.ReadFile.*;
import Persistance.SaveFile.SaveDealerFile;
import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class Database
{
    // private Database object
    private static volatile Database database = null;
    
    
    // file paths
    private final IFilePath filePaths = new FilePath();
    
    
    // database
    private ArrayList<Account> accountDatabse;
    private ArrayList<Dealer> dealerDatabase;
    
    
    public static Database GetDatabase()
    {
        Database local = database;

        if (local == null)
        {
            synchronized(Database.class)
            {
                if (local == null)
                {
                    local = database = new Database();
                }
            }
        }

        return local;
    }


    // private constructor
    private Database()
    {
        // initiate data
        InitializeData.Initiate();


        // get file paths
        filePaths.InitiateFilePath();
        
        
        // check file paths
        if (filePaths.CheckPaths() != true)
        {
            // file paths not found
            Message.showMessage("Error. Cannot get file paths\n", Color.RED);
            System.exit(1);
        }
        

        // read account file
        IReadDatabase<Account> readAccountFile = new ReadAccountFile();
        accountDatabse = readAccountFile.ReadDatabase(filePaths.GetPath(DatabaseType.Accounts));
        
        if (accountDatabse == null)
        {
            Message.showMessage("Empty accounts database. Create new one...\n", Color.RED);
            accountDatabse = new ArrayList<>();
        }
        

        // read dealers file
        IReadDatabase<Dealer> readDealerFile = new ReadDealerFile();
        dealerDatabase = readDealerFile.ReadDatabase(filePaths.GetPath(DatabaseType.Dealers));
        
        if (dealerDatabase == null)
        {
            Message.showMessage("Empty dealers database. Create new one...\n", Color.RED);
            dealerDatabase = new ArrayList<>();
        }
    }
    
    
    // get
    public ArrayList<Account> GetAccountDatabase()
    {
        return accountDatabse;
    }


    public ArrayList<Dealer> GetDealerDatabase()
    {
        return dealerDatabase;
    }


    // save
    public void SaveAccountFile()
    {
        SaveDealerFile saveDealer = new SaveDealerFile();
        saveDealer.Save(filePaths.GetPath(DatabaseType.Dealers));
    }
}
