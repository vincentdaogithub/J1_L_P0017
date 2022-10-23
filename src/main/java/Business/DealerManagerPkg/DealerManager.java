package Business.DealerManagerPkg;

import Presentation.Menu.DealerMenu;
import Presentation.Menu.PrintMenu;
import Presentation.Tool.Color;
import Presentation.Tool.Message;
import Tool.ClearConsole;
import Tool.ReadInput;


public class DealerManager implements IDealerManager
{
    // menu + get user input
    @Override
    public void DealerManagerMenu()
    {
        DealerMenu dealerMenu = new DealerMenu();
        DealerManagerDelegate dealerDelegate = new DealerManagerDelegate();

        
        while (true)
        {   
            PrintMenu.Print(dealerMenu.getMenuList());

            System.out.print("Enter option: ");
            String userInput = ReadInput.Read();

            if (userInput == null || userInput.isBlank() == true)
            {
                // invalid input
                Message.showMessage("Invalid input\n", Color.RED);
                continue;
            }

            System.out.println();


            // convert user input into enum type
            DealerFunctionType functionType = switch (userInput)
                {
                    case "1" -> DealerFunctionType.Add;
                
                    case "2" -> DealerFunctionType.Search;

                    case "3" -> DealerFunctionType.Remove;

                    case "4" -> DealerFunctionType.Update;

                    case "5" -> DealerFunctionType.PrintAll;

                    case "6" -> DealerFunctionType.PrintContinuing;

                    case "7" -> DealerFunctionType.PrintUncontinuing;

                    case "8" -> DealerFunctionType.WriteToFile;

                    default -> null;
                };

            
            // exit dealer manager menu
            if (functionType == null)
            {
                ClearConsole.Clear();
                break;
            }

            
            // delegate
            dealerDelegate.Delegate(functionType);
        }
    }
}
