package entity;

import java.util.ArrayList;

public class SeatingCapacity {
		private Seat[][] seatingLayout;
		private ArrayList<String> layoutFromTextFile;
		private int numberOfRows;
		private int numberOfColumns;
		
		private int totalNoOfSeats;
		private int noOfOccupiedSeats;
		private int noOfAvailableSeats;
		
		
		public SeatingCapacity(ArrayList<String> layoutFromTextFile)
		{
			this.layoutFromTextFile = layoutFromTextFile;
			this.numberOfColumns = getColumnFromTextFile(layoutFromTextFile.get(0))+1;
			this.numberOfRows = layoutFromTextFile.size() - 1;
			this.totalNoOfSeats = 0;
			this.noOfAvailableSeats = 0;

			this.seatingLayout = new Seat[numberOfRows][numberOfColumns];
			for(int r = 1; r < numberOfRows+1; r++)
			{
				String line = layoutFromTextFile.get(r);
				int c = 0;
				for(int i = 0; i < line.length();i++)
				{
					
					if(Character.isDigit(line.charAt(i)))
					{
						boolean occupied = line.charAt(i) == '0' ? false : true;
						char seatLetter = (char) ('A' + r-1);
						Seat seat = new Seat(seatLetter+(c+1)+"", occupied, true);
						this.totalNoOfSeats++;
						this.noOfAvailableSeats++;
						this.seatingLayout[r-1][c] = seat;
						c++;
					}
					else if(line.charAt(i) == '-')
					{
						char seatLetter = (char) ('A' + r-1);
						Seat seat = new Seat(seatLetter+(c+1)+"", true, false);
						this.seatingLayout[r-1][c] = seat;
						c++;
					}
				}
			}
			
		}
		
		
		
		public Seat[][] getSeatingLayout() {
			return seatingLayout;
		}

		public int getTotalNoOfSeat()
		{
			return totalNoOfSeats;
		}
		
		
		public int getNoOfOccupiedSeats() {
			return noOfOccupiedSeats;
		}

		public int getNoOfAvailableSeats() {
			return noOfAvailableSeats;
		}

		public int getColumnFromTextFile(String text)
		{
			
			int rows = 0;
			for(int i = 0; i< text.length();i++)
			{
				if(Character.isDigit(text.charAt(i)))
				{
					rows++;
				}
			}
			return rows;
		}
		
		public void printSeatingLayoutFromTextFile()
		{
			
			for(int i = 0 ; i < this.layoutFromTextFile.size(); i++)
			{
				System.out.println(this.layoutFromTextFile.get(i));
			}
		}
		
		public void printSeatingLayout()
		{
			System.out.println(layoutFromTextFile.get(0));

			for(int r = 0; r < this.numberOfRows;r++)
			{
				char seatLetter = (char) ('A' + r);
				System.out.print(seatLetter + "  ");
				for(int c = 0; c < this.numberOfColumns; c++)
				{

					if(seatingLayout[r][c].getIsSeat())
					{
						int isOccupied = seatingLayout[r][c].getIsOccupied() ? 1 : 0;
						System.out.print("|"+isOccupied+"|");
						
					}
					else if(!seatingLayout[r][c].getIsSeat())
					{

						System.out.print(" - ");
					}
				}
				System.out.println();
			}
		}
		
		public String getColumnString()
		{
			String largestColumnString = "";
			for(String s : layoutFromTextFile)
			{
				if(s.length() > largestColumnString.length())
				{
					largestColumnString = s;
				}
			}
			return largestColumnString;
		}
}
