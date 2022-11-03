package entity;

public class SeatingCapacity {
		private Seat[][] seatingLayout;
		
		private int numberOfRows;
		private int numberOfColumns;
		
		private int totalNoOfSeats;
		private int noOfOccupiedSeats;
		private int noOfAvailableSeats;
		
		public SeatingCapacity(int numberOfRows, int numberOfColumns)
		{
			this.numberOfRows = numberOfRows;
			this.numberOfColumns = numberOfRows;
			
			seatingLayout = new Seat[numberOfRows][numberOfColumns];
			
			for(int r = 0; r < numberOfRows; r++)
			{
				char seatLetter = (char) (r + 'A');
				for(int c = 0; c < numberOfColumns; c++ )
				{
					String seatId = seatLetter + ""+ c;
					seatingLayout[r][c] = new Seat(seatId);
					System.out.println("SeatID : " + seatingLayout[r][c].getSeatId() + " \n");
				}
			}
		}
		
		public void privateSeatingLayout()
		{
			
			
			for(int r = -1; r < numberOfRows; r++)
			{
				
				for(int c = -1; c < numberOfColumns; c++)
				{
					
				}
			}
		}
}
