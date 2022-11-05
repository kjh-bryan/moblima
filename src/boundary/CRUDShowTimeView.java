package boundary;
import controller.UserInputValidationController;
import entity.CinemaShowTime;
import controller.UserInputValidationController;
import java.time.LocalDateTime;
import java.time.Month;

import controller.CinemaShowTimeController;
public class CRUDShowTimeView {
    public static void crud_showtime_view(){
        boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("Create, Update, Remove Showtime");
			System.out.println("------------------------------");
			System.out.println("1: Create Showtime");
			System.out.println("2: Update Showtime");
			System.out.println("3: Remove Showtime");
			System.out.println("4: Exit");
			
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					System.out.println("Enter ShowTime ID: ");
					int id = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter Cinema ID: ");
					String cineId = UserInputValidationController.validateStringFromUser();
					System.out.println("Enter movie ID: ");
					int movId = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter Start Time (Hour): ");
					int hour = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter Start Time (Minute): ");
					int minute = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter End Time (Hour): ");
					int hour_end = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter End Time (Minute): ");
					int minute_end = UserInputValidationController.validateNumberFromUser();
					System.out.println( "Enter Date: ");
					int date = UserInputValidationController.validateNumberFromUser();
					System.out.println("Enter Month: ");
					Month month = converter(UserInputValidationController.validateNumberFromUser());
					System.out.println("Enter Year: ");
					int year = UserInputValidationController.validateNumberFromUser();
					LocalDateTime start = LocalDateTime.of(year, month, date, hour, minute);
					LocalDateTime end = LocalDateTime.of(year, month, date, hour_end, minute_end);
					CinemaShowTime show = new CinemaShowTime(id, cineId, movId, start, end);
					CinemaShowTimeController.createShowTime(show);
					CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());
					break;
				case 2:
					CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());
					System.out.println("Choose the ShowId that you wish to update");
					int showsId = UserInputValidationController.validateNumberFromUser();

					System.out.println("What do you want to update? ");
					System.out.println("------------------------------");
					System.out.println("1: Movie");
					System.out.println("2: Showtime");
					System.out.println("3: Exit");


					int Option = UserInputValidationController.validateNumberFromUser();
					switch(Option){
						case 1:
						System.out.println("Enter a new movie ID: ");
						int newMov = UserInputValidationController.validateNumberFromUser();
						CinemaShowTimeController.updateMovie(showsId, newMov);
						CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());
						break;

						case 2:
						System.out.println("Enter a new start time (hour): ");
						int newHourStart = UserInputValidationController.validateNumberFromUser();
						System.out.println("Enter a new start time (minute): ");
						int newMinuteStart = UserInputValidationController.validateNumberFromUser();
						System.out.println("Enter a new end time (hour): ");
						int newHourEnd = UserInputValidationController.validateNumberFromUser();
						System.out.println("Enter a new start time (minute): ");
						int newMinuteEnd = UserInputValidationController.validateNumberFromUser();
						System.out.println("Enter a new date: ");
						int newDate = UserInputValidationController.validateNumberFromUser();
						System.out.println("Enter a new month; ");
						int newMonth = UserInputValidationController.validateNumberFromUser();
						LocalDateTime newStart = LocalDateTime.of(2022, converter(newMonth), newDate, newHourStart, newMinuteStart);
						LocalDateTime newEnd = LocalDateTime.of(2022, converter(newMonth), newDate, newHourEnd, newMinuteEnd);
						CinemaShowTimeController.updateStartTime(showsId, newStart, newEnd);
						CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());
						break;

						case 3:
						break;




						


		

						


					}


					break;
				case 3:
				CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());
				System.out.println("Choose the ShowId that you wish to remove");
				int showId = UserInputValidationController.validateNumberFromUser();
				CinemaShowTimeController.deleteByShowId(showId);
				System.out.println("Deleted Successfully");
				CinemaShowTimeController.readAllAndPrint(CinemaShowTimeController.getAllCinemaShowTimeList());


					break;
				case 4:
					
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
    }

	public static Month converter(int month){
		switch(month){
			case 1: 
				return Month.JANUARY;
				
			case 2: 
				return Month.FEBRUARY;
				
			case 3: 
				return Month.MARCH;
				
			case 4: 
				return Month.APRIL;
			
			case 5: 
				return Month.MAY;
				
			case 6: 
				return Month.JUNE;
				
			case 7: 
				return Month.JULY;
			
			case 8: 
				return Month.AUGUST;
			
			case 9: 
				return Month.SEPTEMBER;
			
			case 10: 
				return Month.OCTOBER;
				
			case 11: 
				return Month.NOVEMBER;
			
			case 12: 
				return Month.DECEMBER;
			default:
				System.out.println("Invalid Month");
				return null;
			

		}
	}
}
