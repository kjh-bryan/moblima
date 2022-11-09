package boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import controller.SystemSettingController;
import controller.UserInputValidationController;
import entity.CinemaClass;
import entity.MovieType;
import entity.TicketDay;
import entity.TicketType;

/**
 * This class represents Configure System Setting View which
 * is used by an Admin to change the ticket prices or adding a Holidayv
*/

public class ConfigureSystemSettingView {
	
	/**
	 * Display the choice for Admin
	 * Updates which corresponding category of ticket prices
	 * or Adding a holiday
	*/
	public static void configureSystemSettingView()
	{
		boolean selectedExit = false;
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("Configure System Settings");
			System.out.println("------------------------------");
			System.out.println("1: Update Base Ticket Price");
			System.out.println("2: Update Cinema Class Price");
			System.out.println("3: Update Holiday Price");
			System.out.println("4: Update Movie Type Price");
			System.out.println("5: Update Weekend/Weekday Ticket Price");
			System.out.println("6: Update Ticket Type Price");
			System.out.println("7: Add Holiday");
			System.out.println("8: Enable/Disable Top 5 Movie Filter");
			System.out.println("0: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Update Base Ticket Price
					updateBaseTicketPriceView();
					break;
				case 2:
					updateCinemaClassPriceView();
					//Update Cinema Class Price
					break;
				case 3:
					//Update Holiday Price
					updateHolidayPriceView();
					break;
				case 4:
					// Update Movie Type Price
					updateMovieTypePriceView();
					break;
				case 5:
					// Update Weekend/Weekday Ticket Price
					updateTicketDayPriceView();
					break;
				case 6:
					// Update Ticket Type Price
					updateTicketTypePriceView();
					break;
				case 7:
					// Add Holiday
					addHolidayView();
					break;
				case 8:
					// Enable/ Disable 
					updateTop5FilterSystem();
					
				case 0:
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	
	/**
	 * Updates the new base ticket price
	*/
	public static void updateBaseTicketPriceView()
	{
		System.out.println("====================================");
		System.out.println("===== Update Base Ticket Price =====");
		System.out.println("====================================");
		
		System.out.println();
		
		System.out.println("Enter a new base ticket price (Enter -1 to Go Back):");
		int baseTicketPrice = UserInputValidationController.validateNumberFromUser();
		
		if(baseTicketPrice == -1)
		{
			return;
		}
		else
		{
			SystemSettingController.updatePrice(baseTicketPrice, "base_ticket_price.txt");
			
		}
		
	}
	
	/**
	 * Updates the new CinemaClass price
	*/
	public static void updateCinemaClassPriceView()
	{
		System.out.println("===================================");
		System.out.println("==== Update Cinema Class Price ====");
		System.out.println("===================================");
		
		List<CinemaClass> cinemaClassList = new ArrayList<CinemaClass>(EnumSet.allOf(CinemaClass.class));
		
		boolean selectedExit = false;
		CinemaClass cinemaClass = null;
		while(!selectedExit)
		{
			for(CinemaClass c : cinemaClassList)
			{
				System.out.println("- : " +c);
			}
			System.out.println("0: Exit");
			System.out.println("Enter the Cinema Class to update");
			System.out.println();
			cinemaClass = UserInputValidationController.validateCinemaClassFromUser();
			if(cinemaClass == null)
			{
				selectedExit = true;
				return;
			}
			
		}
		
		System.out.println();
		
		System.out.println("Enter a new price for '" + cinemaClass.getCinemaType() + "' :");
		int cinemaClassPrice = UserInputValidationController.validateNumberFromUser();
		SystemSettingController.updateCinemaClassPrice(cinemaClassPrice, cinemaClass);
		
		
	}
	
	/**
	 * Updates the new Holiday Price
	*/
	public static void updateHolidayPriceView()
	{
		System.out.println("==============================");
		System.out.println("==== Update Holiday Price ====");
		System.out.println("==============================");
		
		
		System.out.println();
		
		System.out.println("Enter a new holiday price (Enter -1 to Go Back):");
		int holidayPrice = UserInputValidationController.validateNumberFromUser();
		
		if(holidayPrice == -1)
		{
			return;
		}
		else
		{
			SystemSettingController.updatePrice(holidayPrice, "holiday.txt");
		}
		
	}
	
	/**
	 * Updates the new Movie Type price
	*/
	
	public static void updateMovieTypePriceView()
	{
		System.out.println("=================================");
		System.out.println("==== Update Movie Type Price ====");
		System.out.println("=================================");
		
		List<MovieType> movieTypeList = new ArrayList<MovieType>(EnumSet.allOf(MovieType.class));
		
		boolean selectedExit = false;
		MovieType movieType = null;
		while(!selectedExit)
		{
			for(MovieType c : movieTypeList)
			{
				System.out.println("- : " +c);
			}
			System.out.println("0: Exit");
			System.out.println("Enter a Movie Type to update");
			System.out.println();
			movieType = UserInputValidationController.validateMovieTypeFromUser();
			if(movieType == null)
			{
				selectedExit = true;
				return;
			}
			
		}
		
		System.out.println();
		
		System.out.println("Enter a new price for '" + movieType.getMovieType() + "' :");
		int cinemaClassPrice = UserInputValidationController.validateNumberFromUser();
		SystemSettingController.updateMovieTypePrice(cinemaClassPrice, movieType);
		
		
	}
	
	/**
	 * Updates the new Ticket Type Price
	*/
	public static void updateTicketTypePriceView()
	{
		System.out.println("==================================");
		System.out.println("==== Update Ticket Type Price ====");
		System.out.println("==================================");
		
		List<TicketType> ticketTypeList = new ArrayList<TicketType>(EnumSet.allOf(TicketType.class));
		
		boolean selectedExit = false;
		TicketType ticketType = null;
		while(!selectedExit)
		{
			for(TicketType t : ticketTypeList)
			{
				System.out.println("- : " +t);
			}
			System.out.println("0: Exit");
			System.out.println("Enter a Ticket Type to update");
			System.out.println();
			ticketType = UserInputValidationController.validateTicketTypeFromUser();
			if(ticketType == null)
			{
				selectedExit = true;
				return;
			}
			
		}
		
		System.out.println();
		
		System.out.println("Enter a new price for '" + ticketType.getTicketType() + "' :");
		int ticketTypePrice = UserInputValidationController.validateNumberFromUser();
		SystemSettingController.updateTicketTypePrice(ticketTypePrice, ticketType);
		
	}
	
	/**
	 * Updates the new Ticket Day Price
	*/
	public static void updateTicketDayPriceView()
	{
		System.out.println("==================================");
		System.out.println("==== Update Ticket Day Price ====");
		System.out.println("==================================");
		
		List<TicketDay> ticketDayList = new ArrayList<TicketDay>(EnumSet.allOf(TicketDay.class));
		
		boolean selectedExit = false;
		TicketDay ticketDay = null;
		while(!selectedExit)
		{
			for(TicketDay t : ticketDayList)
			{
				System.out.println("- : " +t);
			}
			System.out.println("0: Exit");
			System.out.println();
			System.out.println("Enter a Ticket Day to update");
			System.out.println();
			ticketDay = UserInputValidationController.validateTicketDayFromUser();
			if(ticketDay == null)
			{
				selectedExit = true;
				return;
			}
			
		}
		
		System.out.println();
		
		System.out.println("Enter a new price for '" + ticketDay.getTicketDay() + "' :");
		int ticketDayPrice = UserInputValidationController.validateNumberFromUser();
		SystemSettingController.updateTicketDayPrice(ticketDayPrice, ticketDay);
		
	}
	
	/**
	 * Adds a new Holiday to the database
	*/
	public static void addHolidayView()
	{
		System.out.println("===========================");
		System.out.println("======= Add Holiday =======");
		System.out.println("===========================");
		
		
		System.out.println("Enter a date (yyyy-MM-dd): ");
		LocalDate holidayDate = LocalDate.parse(UserInputValidationController.validateLocalDateFromUser());
		
		System.out.println("Enter the holiday : ");
		String holiday = UserInputValidationController.validateStringFromUser();
		
		SystemSettingController.addHoliday(holiday, holidayDate);
		
	}
	
	/**
	 * Update the top 5 movies filter system
	*/
	public static void updateTop5FilterSystem()
	{
		System.out.println("=========================================");
		System.out.println("======= Update Top 5 Movie Filter =======");
		System.out.println("=========================================");
		
		Map<String,Boolean> filterSettings = SystemSettingController.getFilterSettings();
		
		System.out.println("Current Settings: ");
		List<String> filterList = new ArrayList<String>();
		for (Map.Entry<String,Boolean> entry : filterSettings.entrySet()) 
		{
			String enableDisable = entry.getValue() ? "Enabled" : "Disabled";
			System.out.println(filterList.size()+1 + ": " +entry.getKey()  + " : " +enableDisable);
			filterList.add(entry.getKey());
		}
		System.out.println("0: Go Back");
		
		System.out.println("Which filter do you want to enable?");
		int choice = UserInputValidationController.validateNumberFromUser();
		
		if(choice == 0)
		{
			return;
		}
		else if(choice > 0 && choice < filterList.size()+1)
		{
			SystemSettingController.updateFilterSystem(filterList.get(choice-1));
		}
		else
		{
			System.out.println("Invalid options!");
		}
		
	}
}
