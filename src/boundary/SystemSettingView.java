package boundary;

import controller.UserInputValidationController;
import controller.SystemConfigController;

public class SystemSettingView {
    public static void system_setting_view(){
        System.out.println("------------------------------");
        System.out.println("System Configuration");
        System.out.println("------------------------------");
        System.out.println("1. Change Ticket Price");
        System.out.println("2. Add Holiday Dates");
        System.out.println("3. Exit");
        
        int userChoice = UserInputValidationController.validateNumberFromUser();
        switch(userChoice){
            case 1:
            
            int cur = SystemConfigController.getPrice();
            System.out.println("Current Price: " + cur);
            System.out.println("Enter a new base price: ");
                int newPrice = UserInputValidationController.validateNumberFromUser();
                SystemConfigController.updateBasePrice(newPrice);
                break;
            case 2:
                System.out.println("Add Holiday Dates: ");
                break;
            case 3:
                return;
            default:
                System.out.println("Incorrect Option");
                break;
        }


    }
}
