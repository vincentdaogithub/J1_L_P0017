package Persistance.Tool;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class ReadFileTool
{
    // get next line in file
    public static String GetNextLine(ArrayList<String> fileContent)
    {
        // buffered reader
        return fileContent.isEmpty() == false ? fileContent.remove(0).trim() : null;
    }
    
    
    // get next token in StringTokenizer
    public static String GetNextToken(StringTokenizer tokenizer)
    {
        return tokenizer.hasMoreTokens() == true ? tokenizer.nextToken().trim() : null;
    }
}
