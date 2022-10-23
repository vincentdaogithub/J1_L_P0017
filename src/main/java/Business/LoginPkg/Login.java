package Business.LoginPkg;

import Presentation.Tool.Color;
import Presentation.Tool.Message;
import Tool.PatternCheck;
import Tool.ReadInput;


public class Login implements ILogin
{
    // format
    private final String USERNAME_FORMAT = "^E\\d{3}$";
    private final String PASSWORD_FORMAT = "^[\\D]";


    // read user login
    @Override
    public String[] ReadLogin()
    {
        String username;
        String password;
        
        
        // read user input
        try
        {
            System.out.print("Enter username: ");
            username = ReadInput.Read();

            System.out.print("Enter password: ");
            password = ReadInput.Read();

            
            // check validity of login
            if (CheckPassword(password) != true || CheckUsername(username) != true)
            {
                // error (reported)
                return null;
            }

            return new String[] {username, password};   // 0. username  1. password
        }
        catch (Exception e)
        {
            Message.showMessage("Error. Cannot get user login\n", Color.RED);
            return null;
        }
    }


    // check validity of username
    private Boolean CheckUsername(String username)
    {
        if (PatternCheck.Check(USERNAME_FORMAT, username) != true)
        {
            Message.showMessage("Error. Invalid username\n", Color.RED);
            return false;
        }

        return true;
    }


    // check validity of password
    private Boolean CheckPassword(String password)
    {
        if (PatternCheck.Check(PASSWORD_FORMAT, password) != true)
        {
            Message.showMessage("Error. Invalid password\n", Color.RED);
            return false;
        }

        return true;
    }
}
