package Tool;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.util.Scanner;


public class ReadInput
{
    private static Scanner readInput = new Scanner(System.in);


    // read input as string
    public static String Read()
    {
        try
        {
            return readInput.nextLine().trim();
        }
        catch (Exception e)
        {
            // cannot read
            Message.showMessage("Error. Invalid input\n", Color.RED);
            readInput = new Scanner(System.in);
            return null;
        }
    }


    // convert input to int
    public static Integer ConvertToInteger(String input)
    {
        try
        {
            return Integer.parseInt(input);
        }
        catch (Exception e)
        {
            // cannot convert
            Message.showMessage("Error. Cannot convert into integer\n", Color.RED);
            return null;
        }
    }


    // convert input
    public static Float ConvertToFloat(String input)
    {
        try
        {
            return Float.parseFloat(input);
        }
        catch (Exception e)
        {
            // cannot convert
            Message.showMessage("Error. Cannot convert into float\n", Color.RED);
            return null;
        }
    }
}
