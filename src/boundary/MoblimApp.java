package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
public class MoblimApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		LocalDate ld = LocalDate.parse("2022-10-31");
		LocalTime lt = LocalTime.parse("17:20");
		LocalDateTime ldt = LocalDateTime.parse("2022-10-31T17:20");
		System.out.println(ldt.format(DateTimeFormatter.ofPattern("EEE yyyy-MM-dd HH:mm")));
		
		
		
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
