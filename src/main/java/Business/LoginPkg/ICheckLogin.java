package Business.LoginPkg;

import Persistance.Entity.Account.AccountType;


public interface ICheckLogin
{
    public AccountType Check(String[] login);
}
