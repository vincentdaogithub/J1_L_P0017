package Persistance;

import java.util.HashMap;
import Persistance.Config.IReadConfigFile;
import Persistance.Config.ReadConfigFile;
import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class FilePath implements IFilePath
{
    // path to config file
    private final String CONFIG_PATH = "config.dat";


    // paths to files
    private String pathToAccountsFile = null;
    private String pathToDealersFile = null;
    
    
    // initiate config file and get file paths
    @Override
    public void InitiateFilePath()
    {
        IReadConfigFile configFile = new ReadConfigFile();
        HashMap<DatabaseType, String> filePaths = configFile.Read(CONFIG_PATH);
        
        
        // check file paths
        if (filePaths == null)
        {
            // error cannot read config file
            Message.showMessage("Error. Cannot read config file\n", Color.RED);
            return;
        }
        
        for (HashMap.Entry<DatabaseType, String> item : filePaths.entrySet())
        {
            switch (item.getKey())
            {
                case Accounts -> pathToAccountsFile = item.getValue();
                
                case Dealers -> pathToDealersFile = item.getValue();
                
                default ->
                {
                    // error unknown value
                        Message.showMessage("Error. Unknown file type\n", Color.RED);
                }
            }
        }
    }
    
    
    // get paths
    @Override
    public String GetPath(DatabaseType type)
    {
        switch (type)
        {
            case Accounts ->
            {
                return pathToAccountsFile;
            }
            
            case Dealers ->
            {
                return pathToDealersFile;
            }
            
            default ->
            {
                // unknown path
                Message.showMessage("Error. Unknown file path\n", Color.RED);
                return null;
            }
        }
    }
    
    
    // check paths
    @Override
    public Boolean CheckPaths()
    {
        return this.pathToAccountsFile != null && this.pathToDealersFile != null;
    }
}
