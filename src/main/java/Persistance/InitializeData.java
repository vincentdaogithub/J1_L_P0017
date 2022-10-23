package Persistance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import Presentation.Tool.Color;
import Presentation.Tool.Message;


public class InitializeData
{
    // initialize data
    public static void Initiate()
    {
        // check whether the Data folder exists
        File destinationDirectory = new File("Data");

        if (destinationDirectory.exists() == true)
        {
            // Data folder already exist
            return;
        }


        // if Data folder not exist, copy Data folder from resources
        destinationDirectory.mkdir();
        
        IFilePath filePath = new FilePath();
        filePath.InitiateFilePath();

        
        // create files in Data folder
        File accountFile = new File(filePath.GetPath(DatabaseType.Accounts));
        File dealerFile = new File(filePath.GetPath(DatabaseType.Dealers));
        
        try
        {
            accountFile.createNewFile();
            dealerFile.createNewFile();
        }
        catch (Exception e)
        {
            Message.showMessage("Error. Cannot create database files\n", Color.RED);
            System.exit(1);
        }
        
        
        // copy files from resources to Data
        CopyFile(filePath.GetPath(DatabaseType.Accounts));
        CopyFile(filePath.GetPath(DatabaseType.Dealers));
    }

    @SuppressWarnings("ConvertToTryWithResources")
    private static void CopyFile(String filePath)
    {
        try
        {
            // load source file in jar
            ClassLoader classLoader = InitializeData.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);


            // create file outside jar
            File destinationFile = new File(filePath);
            destinationFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(destinationFile);


            // copy file
            byte[] buffer = new byte[1024];
            int bufferLength = inputStream.read(buffer);

            while (bufferLength >= 0)
            {
                outputStream.write(buffer, 0, bufferLength);
                bufferLength = inputStream.read(buffer);
            }

            outputStream.close();
        }
        catch (Exception e)
        {
            // error copying file
            Message.showMessage("Error. Cannot initiate data from template database\n", Color.RED);
        }
    }
}