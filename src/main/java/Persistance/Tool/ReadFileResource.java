package Persistance.Tool;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Tool.CheckNullOrBlank;


public class ReadFileResource implements IReadFile
{
    // read files from resources folder in jar
    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<String> Read(String filePath)
    {
        try
        {
            // get class loader to get input stream
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            
            // get buffered reader
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
            
            // try reading first line to check empty file
            String line = bufferedReader.readLine();
            
            if (CheckNullOrBlank.Check(line) == true)
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
