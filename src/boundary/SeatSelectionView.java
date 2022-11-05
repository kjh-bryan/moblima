package boundary;

import controller.SeatingCapacityController;
import controller.UserInputValidationController;
import entity.Seat;
import entity.SeatingCapacity;

public class SeatSelectionView {
	
	public static void seat_selection_view(int showTimeId)
	{
		SeatingCapacity seatingCapacity = SeatingCapacityController.getSeatingCapacityByShowTimeId(showTimeId);

		System.out.println("\n======== SCREEN ========");
		
		seatingCapacity.printSeatingLayout();
		boolean goBack = false;
		
		while(!goBack) {
			
			
			
			System.out.println();
			System.out.print("Please Enter a Seat to Purchase a Ticket (Enter 0 to Go Back): ");
			
			String seatId = UserInputValidationController.validateStringFromUser();
			if(seatId.equals("0")) return;
			int row = seatId.charAt(0) - 'A';
			int column = Integer.parseInt(seatId.replaceAll("[\\D]", ""));
			
			Seat[][] seat = seatingCapacity.getSeatingLayout();
			Seat selectedSeat = null;
			if(seat[row][column].getIsSeat())
			{
				if(!seat[row][column].getIsOccupied())
				{
					selectedSeat = seat[row][column];
				}
				else
				{
					System.out.println("Seat is already occupied!");
				}
			}
			else
			{
				System.out.println("This selection is not a seat!");
			}
			
			if(selectedSeat != null)
			{
				BookSeatView.book_seat_view(showTimeId, selectedSeat);
			}
		}
	}
	
	
	
}
