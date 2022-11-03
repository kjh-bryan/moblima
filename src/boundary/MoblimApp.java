package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
public class MoblimApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int movieId = 001;
		String title = "Black Adam";
		String movieStartDate = "Sunday 20 Nov";
		String movieGenre = "Action";
		MovieClassifiedRating movieClassifiedRating = MovieClassifiedRating.PG13;
		int movieDurationInMins = 125;
		String movieLanguage = "English";
		String topDivider = "------------------------";
		String movieIdString = getMovieDescriptionString("Movie ID: " + movieId,topDivider);
		String titleString = getMovieDescriptionString(title,topDivider);
		String movieGenreString = getMovieDescriptionString(movieGenre,topDivider);
		String emptySpace = getMovieDescriptionString("", topDivider);
		String movieStartDateString = getMovieDescriptionString("Movie ID: " + movieId,topDivider);
		String movieClassifiedRatingString = getMovieDescriptionString(movieClassifiedRating+"",topDivider);
		String movieDurationInMinsString = getMovieDescriptionString(movieDurationInMins+"mins",topDivider);
		String movieLanguageString = getMovieDescriptionString(movieLanguage,topDivider);
		System.out.println(topDivider);
		System.out.println(movieIdString);
		System.out.println(emptySpace);
		System.out.println(titleString);
		System.out.println(emptySpace);
		System.out.println(movieClassifiedRatingString);
		System.out.println(movieGenreString);
		System.out.println(movieDurationInMinsString);
		System.out.println(topDivider);
		
		
		
		while(true) {
			int choice = -1;
			while(choice<1||choice>8) {
				System.out.println("\n------------------------------");
				System.out.println("MOvie Booking and Listing Management Application - MOBLIMA");
				System.out.println("------------------------------");
				System.out.println("1: Search/List Movies");
				System.out.println("2: View Movie Details");
				System.out.println("3: Check Seat Availability and Selection of Seats");
				System.out.println("4: Book and Purchase Tickets");
				System.out.println("5: View Booking History");
				System.out.println("6: List Top 5 Movies");
				System.out.println("7: Admin Module");
				System.out.println("8: Exit");
				System.out.println();
				System.out.print("Please Enter Your Choice: ");
				choice = sc.nextInt();
			}
			
			if(choice == 1) {}		// Search/List Movies
			else if(choice == 2) {}	// View Movie Details
			else if(choice == 3) {}	// Check Seat Availability and Selection of Seats
			else if(choice == 4) {}	// Book and Purchase Tickets
			else if(choice == 5) {}	// View Booking History
			else if(choice == 6) {}	// List Top 5 Movies
			else if(choice == 7) {}	// Admin Module
			else{					// Exit
				System.out.println("\n------------------------------");
				System.out.println("Thank you for using MOBLIMA!");
				System.out.println("------------------------------");
				sc.close();
				System.exit(0);
			}
		}
	}
	
	public static String getMovieDescriptionString(String details, String totalCharacters)
	{
		int totalLength = totalCharacters.length();
		int detailsLength = details.length();
		
		String filledSpace = "| "+details;
		
		for(int i = detailsLength; i < totalLength-2; i++){
			filledSpace += " ";
		}
		filledSpace += "|";
		return filledSpace;
	}
}
