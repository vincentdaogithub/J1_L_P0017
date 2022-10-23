package Tool;


public class CheckNullOrBlank
{
    // check null or blank input
    public static Boolean Check(String inputToCheck)
    {
        return inputToCheck == null || inputToCheck.isBlank() == true;
    }
}
