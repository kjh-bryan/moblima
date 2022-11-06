package entity;

import java.util.ArrayList;

public class SeatingCapacity {
		private String showTimeId;
		private Seat[][] seatingLayout;
		private ArrayList<String> layoutFromTextFile;
		private int numberOfRows;
		private int numberOfColumns;
		
		private int totalNoOfSeats;
		private int noOfOccupiedSeats;
		private int noOfAvailableSeats;
		
		
		public SeatingCapacity(String showTimeId,ArrayList<String> layoutFromTextFile)
		{
			this.showTimeId = showTimeId;
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
				int seatIdColumn = 0;
				for(int i = 0; i < line.length();i++)
				{
					
					if(Character.isDigit(line.charAt(i)))
					{
						boolean occupied = line.charAt(i) == '0' ? false : true;
						char seatLetter = (char) ('A' + r-1);
						String seatRow = (seatIdColumn+1)+"";
						String seatId = seatLetter + seatRow + "";
						Seat seat = new Seat(seatId, occupied, true);
						this.totalNoOfSeats++;
						if(occupied)
						{
							this.noOfOccupiedSeats++;
						}
						else
						{
							this.noOfAvailableSeats++;
						}
						this.seatingLayout[r-1][c] = seat;
						seatIdColumn++;
						c++;
					}
					else if(line.charAt(i) == '-')
					{
						char seatLetter = (char) ('A' + r-1);
						Seat seat = new Seat(seatLetter+(seatIdColumn+1)+"", true, false);
						this.seatingLayout[r-1][c] = seat;
						seatIdColumn++;
						c++;
					}
					else if(line.charAt(i) == 'x')
					{
						Seat seat = new Seat("", false, false);
						this.seatingLayout[r-1][c] = seat;
						c++;
					}
				}
			}
			
		}
		
		public int getNumberOfRows() {
			return numberOfRows;
		}

		public int getNumberOfColumns() {
			return numberOfColumns;
		}

		public String getShowTimeId()
		{
			return showTimeId;
		}
		
		public Seat[][] getSeatingLayout() {
			return seatingLayout;
		}
		
		public void updateSeatLayoutWithSeatId(String seatId)
		{
			for(int r = 0;r < numberOfRows; r++)
			{
				for(int c = 0; c < numberOfColumns; c++)
				{
					if(seatingLayout[r][c].getIsSeat())
					{
						if(seatingLayout[r][c].getSeatId().equals(seatId))
						{
							seatingLayout[r][c].assignSeat();
							
						}
					}
				}
			}
		}
		
		public Seat getSeatWithSeatId(String seatId)
		{
			Seat seat = null;
			for(int r = 0;r < numberOfRows; r++)
			{
				for(int c = 0; c < numberOfColumns; c++)
				{
					if(seatingLayout[r][c].getSeatId().equals(seatId))
					{
						seat = seatingLayout[r][c];
					}
				}
			}
			return seat;
		}
		
		public void setSeatingLayout(Seat[][] seatingLayout)
		{
			this.seatingLayout = seatingLayout;
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
		
		public ArrayList<String> outputToFile()
		{
			ArrayList<String> output = new ArrayList<String>();
			output.add(layoutFromTextFile.get(0));
			for(int r = 0; r < this.numberOfRows;r++)
			{
				String line = "";
				char seatLetter = (char) ('A' + r);
				line += seatLetter + " ";
				for(int c = 0; c < this.numberOfColumns; c++)
				{
					if(seatingLayout[r][c] == null)
					{
						continue;
					}
					if(seatingLayout[r][c].getIsSeat())
					{
						int isOccupied = seatingLayout[r][c].getIsOccupied() ? 1 : 0;
						
						line+= "|"+isOccupied+"|";
						
					}
					else if(!seatingLayout[r][c].getIsSeat())
					{
						line+= " - ";
					}
				}
				output.add(line);
			}
			return output;
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
					if(seatingLayout[r][c] == null)
					{
						continue;
					}
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
