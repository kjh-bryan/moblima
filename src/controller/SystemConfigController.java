package controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemConfigController {
    private static final String SEPARATOR = "|";
	private static final String databaseTableName = "database/price.txt";
    private final static Logger logger = Logger.getLogger(CinemaShowTimeController.class.getName());
    public static void updateBasePrice(int newPrice) {
        try{
            UserInputValidationController.createDatabaseTableFile(databaseTableName);
            deleteAll();
			PrintWriter out = new PrintWriter(new FileWriter(databaseTableName, true));
			out.append(newPrice + "\n");
			out.close();
			}
            catch(Exception e)
            {
                logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
            }
        }

			

            public static void deleteAll() {
                try {
                    UserInputValidationController.createDatabaseTableFile(databaseTableName);
                        
                    new FileWriter(databaseTableName, false).close();
        
                    }
                    catch(Exception e)
                    {
                        logger.log(Level.SEVERE, "deleteAll() exception occured : " + e.getLocalizedMessage());
                    }
                    
            }	

            public static int getPrice(){
                
                    UserInputValidationController.createDatabaseTableFile(databaseTableName);
                    Scanner sc = null;
                    try {
                        sc = new Scanner(new FileInputStream(databaseTableName));
                        while (sc.hasNextLine()) {
                            int price = sc.nextInt();
                            sc.close();
                            return price;
                        }
                        return 0;
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "getPrice() exception occured : " + e.getLocalizedMessage());
                        return 0;
                    }
            
                    



    }
}
   


