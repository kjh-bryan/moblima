package view;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Controller.HolidayController;

public class createHoliday {


		/**
		 * Function to check if a date entered is valid
		 * @param strDate: Date to be validated
		 * @return Boolean value depicting if the date is valid or not
		 */
		public static boolean validateJavaDate(String strDate)
		   {
			/* Check if date is 'null' */
			if (strDate.trim().equals(""))
			{
			    return true;
			}
			/* Date is not 'null' */
			else
			{
			    /*
			     * Set preferred date format,
			     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
			    SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
			    sdfrmt.setLenient(false);
			    /* Create Date object
			     * parse the string into date 
		             */
			    try
			    {
			        Date javaDate = sdfrmt.parse(strDate); 
			        //System.out.println(strDate+" is valid date format");
			    }
			    /* Date format is invalid */
			    catch (ParseException e)
			    {
			        //System.out.println(strDate+" is Invalid Date format");
			        return false;
			    }
			    /* Return true if date format is valid */
			    return true;
			}
		   }
		/**
		 * Driver function of the view
		 * @param args null argument used to start the view
		 * @throws Exception Throws Exception
		 */
		public static void main(String[] args) throws Exception {
			Scanner sc = new Scanner(System.in);
			String name, date;
			System.out.println("Enter name of the holiday:");
			name = sc.nextLine();
			System.out.println("Enter date in the following format: MM/dd/yyyy");
			date = sc.nextLine();
			
			while(!validateJavaDate(date)) {
				System.out.println("Enter date in the following format: MM/dd/yyyy");
				date = sc.nextLine();
			}
			HolidayController.writeHoliday(name,date);
			AdminFunctions.main(null);
		}

	}

