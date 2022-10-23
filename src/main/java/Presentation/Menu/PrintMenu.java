package Presentation.Menu;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import java.util.ArrayList;


public class PrintMenu
{
    public static void Print (ArrayList<String> menuList)
    {
        // check if arg is null
        if (menuList == null)
        {
            Message.showMessage("Error. Null argument\n", Color.RED);
            return;
        }
        
        
        // Print all String in menuList
        for (String string : menuList)
        {
            System.out.println(string);
        }
    }
}
