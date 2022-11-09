package boundary;

import java.util.ArrayList;

import controller.SeatingLayoutController;
import controller.UserInputValidationController;
import entity.Seat;
import entity.SeatingLayout;

/**
 * This class represents when the MovieGoer selected a ShowTimeID
 * and will be brought to see the Seating Layout of the CinemaShowTime
*/

public class SeatSelectionView {
	
	
	/**
	 * Get the Seating Layout of the CinemaShowTime, 
	 * and prints the layout of the Cinema and 
	 * allow the User to select its Seat choice by entering
	 * a Seat ID
	 * @param showTimeId			The CinemaShowTime
	*/
	public static void seatSelectionView(int showTimeId)
	{
		SeatingLayout seatingLayout = SeatingLayoutController.getSeatingLayoutByShowTimeId(showTimeId);

		
		boolean goBack = false;
		ArrayList<Seat> selectedSeatList = new ArrayList<Seat>();
		while(!goBack) {
			
			printScreenLayout(seatingLayout.getColumnString());
			seatingLayout.printSeatingLayout();
			printEntranceLayout(seatingLayout.getColumnString());
			
			System.out.println();
			System.out.println("Enter the corresponding Row Letter and Column Number of your seat choice (Enter 1 to Confirm, 0 to Go Back): ");
			
			String seatId = UserInputValidationController.validateSeatIDFromUser();
			
			if(seatId.equals("0"))
				return;
			if(seatId.equals("1"))
			{
				if(selectedSeatList.isEmpty())
				{
					System.out.println("Please select at least 1 seat!");
				}
				else
				{
					BookedSeatView.checkLoginBeforeBookSeatView(showTimeId, selectedSeatList);
					return;
				}
				
			}
			int row = seatId.charAt(0) - 'A';
			int column = Integer.parseInt(seatId.replaceAll("[\\D]", ""));
			
			Seat seat = seatingLayout.getSeatWithSeatId(seatId);
			if(seat == null)
			{
				System.out.println("Invalid seat selected");
			}
			else
			{
				if(!seat.getIsSeat())
				{
					System.out.println("This selection is not a seat!");
				}
				else
				{
					if(seat.getIsOccupied())
					{
						System.out.println("Seat is already occupied!");
					}
					else
					{
						seatingLayout.updateSeatLayoutWithSeatId(seatId);
						selectedSeatList.add(seat);
					}
				}
			}
		}
	}
	
	
	/**
	 * Prints how the Screen looks like in the Cinema
	 * @param column			How long the column is in the text file
	*/
	public static void printScreenLayout(String column)
	{
		String screen = "SCREEN";
		String spaceBeforeScreen = new String(new char[(column.length()/2) - 3]).replace("\0", " ");
		String spaceAfterScreen = new String(new char[(column.length()/2) - 3]).replace("\0", " ");
		String spaceBetweenScreenBar = new String(new char[(column.length()) ]).replace("\0", " ");
		String screenBottomDivider = new String(new char[column.length()]).replace("\0", "_");
		System.out.println("|" + spaceBeforeScreen + screen +spaceAfterScreen  + "|");
		System.out.println("|" + spaceBetweenScreenBar + "|");
		System.out.println("|" + screenBottomDivider + "|");
		System.out.println();
	}
	
	/**
	 * Prints how the Entrance looks like in the Cinema
	 * @param column			How long the column is in the text file
	*/
	public static void printEntranceLayout(String column)
	{
		String entrance = "ENTRANCE";
		
		String spaceBeforeEntranceDivider = new String(new char[column.length()/4]).replace("\0", " ");

		String spaceBeforeEntrance = new String(new char[entrance.length()/2]).replace("\0", " ");
		String spaceAfterEntrance = new String(new char[entrance.length()/2]).replace("\0", " ");
		String entranceWithBars = "|" +spaceBeforeEntrance +entrance+ spaceAfterEntrance+"|";

		String entranceTopDivider = new String(new char[entranceWithBars.length()]).replace("\0", "_");
		String spaceBetweenEntranceBar = new String(new char[entranceWithBars.length()-2]).replace("\0", " ");
		String spacesWithBar = "|" +spaceBetweenEntranceBar+ "|";
		
		System.out.println();
		System.out.println(spaceBeforeEntranceDivider + entranceTopDivider + spaceBeforeEntranceDivider);
		System.out.println(spaceBeforeEntranceDivider + spacesWithBar + spaceBeforeEntranceDivider);
		System.out.println(spaceBeforeEntranceDivider + entranceWithBars + spaceBeforeEntranceDivider);
		
	}
	
	
	
}
