package Tool;

import Presentation.Tool.Color;
import Presentation.Tool.Message;

public class AskForInput
{
    public static String Ask(String message)
    {
        if (message == null || message.isBlank() == true)
        {
            Message.showMessage("Error. No message was found to ask\n", Color.RED);
            return null;
        }

        System.out.print(message);
        return ReadInput.Read();
    }
}
