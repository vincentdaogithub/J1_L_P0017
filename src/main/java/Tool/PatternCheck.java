package Tool;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternCheck
{
    public static Boolean Check(String format, String inputToCheck)
    {
        // check args
        if (format == null || inputToCheck == null)
        {
            // null arg pass to the method
            Message.showMessage("Error. Null arguments\n", Color.RED);
            return false;
        }
        
        
        // check
        try
        {
            Pattern pattern = Pattern.compile(format);
            Matcher matcher = pattern.matcher(inputToCheck);

            return matcher.find() == true;
        }
        catch (Exception e)
        {
            // error generating pattern check
            Message.showMessage("Error. Cannot generate pattern to check\n", Color.RED);
            return false;
        }
    }
}
