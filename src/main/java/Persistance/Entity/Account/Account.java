package Persistance.Entity.Account;


public class Account
{
    // account info
    private final String username;
    private final String password;
    private final AccountType accountType;

    
    // constructor
    public Account(String username, String password, AccountType accountType)
    {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    
    // get
    public String getUsername()
    {
        return username;
    }

    
    public String getPassword()
    {
        return password;
    }

    
    public AccountType getAccountType()
    {
        return accountType;
    }

}
