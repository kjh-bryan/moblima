package boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import controller.SystemSettingController;
import controller.UserInputValidationController;
import entity.CinemaClass;
import entity.MovieType;
import entity.TicketDay;
import entity.TicketType;

public class ConfigureSystemSettingView {
	
	
	public static void configure_system_setting_view()
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
			System.out.println("0: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Update Base Ticket Price
					update_base_ticket_price_view();
					break;
				case 2:
					update_cinema_class_price_view();
					//Update Cinema Class Price
					break;
				case 3:
					//Update Holiday Price
					update_holiday_price_view();
					break;
				case 4:
					// Update Movie Type Price
					update_movie_type_price_view();
					break;
				case 5:
					// Update Weekend/Weekday Ticket Price
					update_ticket_day_price_view();
					break;
				case 6:
					// Update Ticket Type Price
					update_ticket_type_price_view();
					break;
				case 7:
					// Add Holiday
					add_holiday_view();
					break;
					
				case 0:
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	
	
	public static void update_base_ticket_price_view()
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
	
	public static void update_cinema_class_price_view()
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
	
	
	public static void update_holiday_price_view()
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
	
	
	public static void update_movie_type_price_view()
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
	
	public static void update_ticket_type_price_view()
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
	
	
	public static void update_ticket_day_price_view()
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
	
	public static void add_holiday_view()
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
}
