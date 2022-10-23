package Persistance.Tool;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class ReadFile implements IReadFile
{
    // read file relative to the jar/working dir
    @Override
    public ArrayList<String> Read(String filePath)
    {
        try
        {
            // create buffered reader
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        
            
            // try reading first lien to check empty file
            String line = bufferedReader.readLine();
            
            if (line == null || line.isBlank())
            {
                Message.showMessage("Error. Empty file\n", Color.RED);
                
                bufferedReader.close();
                return null;
            }
            
            
            // start reading file
            ArrayList<String> fileContent = new ArrayList<>();
            
            while (line != null)
            {
                fileContent.add(line.trim());
                line = bufferedReader.readLine();
            }
            
            
            bufferedReader.close();
            return fileContent;
        }
        catch (Exception e)
        {
            Message.showMessage("Error. Cannot read file\n", Color.RED);
            return null;
        }
    }
}
