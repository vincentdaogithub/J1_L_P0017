package Persistance.SaveFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import Persistance.Database;
import Persistance.Entity.Dealer.Dealer;
import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class SaveDealerFile
{
    public void Save(String filePath)
    {
        try
        {
            // create buffered writer
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            
            // read dealers from dealer database, then write to dealer file
            for (Dealer dealer : Database.GetDatabase().GetDealerDatabase())
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dealer.getDealerID());
                stringBuilder.append(";");
                stringBuilder.append(dealer.getDealerName());
                stringBuilder.append(";");
                stringBuilder.append(dealer.getDealerHouseNumber());
                stringBuilder.append(";");
                stringBuilder.append(dealer.getDealerStreetName());
                stringBuilder.append(";");
                stringBuilder.append(dealer.getDealerNumber());
                stringBuilder.append(";");
                stringBuilder.append(dealer.getIsContinuing());
                stringBuilder.append("\n");

                bufferedWriter.write(stringBuilder.toString());
            }

            
            Message.showMessage("Save completed!\n", Color.GREEN);
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            // cannot save to file
            Message.showMessage("error. Cannot save\n", Color.RED);
        }
    }
}
