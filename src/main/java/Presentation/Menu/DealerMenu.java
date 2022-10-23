package Presentation.Menu;

import java.util.ArrayList;


public class DealerMenu
{
    // menu list
    private final ArrayList<String> menuList = new ArrayList<>();


    // public getMenu() return menuList
    public DealerMenu()
    {
        // Other for quit
        // Choose 1-8 to do the function
        menuList.add("1. Add new dealer");
        menuList.add("2. Search a dealer");
        menuList.add("3. Remove a dealer");
        menuList.add("4. Update a dealer");
        menuList.add("5. Print all dealers");
        menuList.add("6. Print continuing dealers");
        menuList.add("7. Print UN-continuing dealers");
        menuList.add("8. Save to file");
        menuList.add("0. Quit\n");
    }

    
    // DealerMenu constructor add menu to menuList
    public ArrayList<String> getMenuList()
    {
        return menuList;
    }
}
