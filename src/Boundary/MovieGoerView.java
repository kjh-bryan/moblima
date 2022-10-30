package Boundary;

import Controller.UserInputValidationController;

public class MovieGoerView {
	public static void movie_goer_view()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOvie Booking and Listing Management Application - Movie Goer Module");
			System.out.println("------------------------------");
			System.out.println("1: Search/List Movies");
			System.out.println("2: View Movie Details");
			System.out.println("3: Check Seat Availability and Selection of Seats");
			System.out.println("4: Book and Purchase Tickets");
			System.out.println("5: View Booking History");
			System.out.println("6: List Top 5 Movies");
			System.out.println("7: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					// Search/ List movies
					break;
				case 2:
					// View Movie Details
					break;
				case 3:
					// Check Seat Availability and Selection of Seats
					break;
				case 4:
					// Book and Purchase Tickets
					break;
				case 5:
					// View Booking History
					break;
				case 6:
					// List Top 5 Movies
					break;
				case 7:
					// Exit
					return;
				default:
					System.out.println("Incorrect options. Please try again.");
					break;
			}
		}
	}
}
