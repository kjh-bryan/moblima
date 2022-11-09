package entity;

import java.util.ArrayList;

/**
 * A Seating Layout that shows the Seats of the Cinema A moviegoer will be shown
 * this layout while booking for a Movie of a specific CinemaShowTime
 */

public class SeatingLayout {
	/**
	 * This Seating Layout's CinemaShowTime
	 */
	private int cinemaShowTimeId;
	/**
	 * This Seating Layout's Cinema
	 */
	private String cinemaCode;
	/**
	 * This Seating Layout's seats represented in a 2D array
	 */
	private Seat[][] seats;
	
	/**
	 * This Seating Layout's Arraylist of String from the text file
	 * which this Seat layout is comprised of
	 */
	private ArrayList<String> layoutFromTextFile;
	/**
	 * This Seating Layout's number of rows
	 */
	private int numberOfRows;
	/**
	 * This Seating Layout's number of columns
	 */
	private int numberOfColumns;
	/**
	 * This Seating Layout's total no of seats
	 */
	private int totalNoOfSeats;
	/**
	 * This Seating Layout's number occupied seats
	 */
	private int noOfOccupiedSeats;
	/**
	 * This Seating Layout's number of available seats
	 */
	private int noOfAvailableSeats;

	/** 
	 * Create a new SeatingLayout with the given Arraylist of String from text file
	 * This correspond to 1 to 1 relationship to CinemaShowTime
	 * and the values and files output will be changed
	 * @param cinemaShowTimeId					This Seating Layout's CinemaShowTime it belongs to
	 * @param layoutFromTextFile				This Seating Layout line by line of string that represents the seats
	 */
	public SeatingLayout(int cinemaShowTimeId, ArrayList<String> layoutFromTextFile) {
		this.cinemaShowTimeId = cinemaShowTimeId;
		this.layoutFromTextFile = layoutFromTextFile;
		this.numberOfColumns = getColumnFromTextFile(layoutFromTextFile.get(0)) + 1;
		this.numberOfRows = layoutFromTextFile.size() - 1;
		this.totalNoOfSeats = 0;
		this.noOfAvailableSeats = 0;

		this.seats = new Seat[numberOfRows][numberOfColumns];
		for (int r = 1; r < numberOfRows + 1; r++) {
			String line = layoutFromTextFile.get(r);
			int c = 0;
			int seatIdColumn = 0;
			for (int i = 0; i < line.length(); i++) {

				if (Character.isDigit(line.charAt(i))) {
					boolean occupied = line.charAt(i) == '0' ? false : true;
					char seatLetter = (char) ('A' + r - 1);
					String seatRow = (seatIdColumn + 1) + "";
					String seatId = seatLetter + seatRow + "";
					Seat seat = new Seat(seatId, occupied, true);
					this.totalNoOfSeats++;
					if (occupied) {
						this.noOfOccupiedSeats++;
					} else {
						this.noOfAvailableSeats++;
					}
					this.seats[r - 1][c] = seat;
					seatIdColumn++;
					c++;
				} else if (line.charAt(i) == '-') {
					char seatLetter = (char) ('A' + r - 1);
					Seat seat = new Seat(seatLetter + (seatIdColumn + 1) + "", true, false);
					this.seats[r - 1][c] = seat;
					seatIdColumn++;
					c++;
				} else if (line.charAt(i) == 'x') {
					Seat seat = new Seat("", false, false);
					this.seats[r - 1][c] = seat;
					c++;
				}
			}
		}

	}
	
	
	/** 
	 * Create a new SeatingLayout with the given Arraylist of String from text file
	 * This corresponds to the Cinema the Seating Layout belongs to
	 * The text file will be static and file won't be changed or editted
	 * @param cinemaCode					This Seating Layout's Cinema
	 * @param layoutFromTextFile				This Seating Layout line by line of string that represents the seats
	 */
	public SeatingLayout(String cinemaCode, ArrayList<String> layoutFromTextFile) {
		this.cinemaCode = cinemaCode;
		this.layoutFromTextFile = layoutFromTextFile;
		this.numberOfColumns = getColumnFromTextFile(layoutFromTextFile.get(0)) + 1;
		this.numberOfRows = layoutFromTextFile.size() - 1;
		this.totalNoOfSeats = 0;
		this.noOfAvailableSeats = 0;

		this.seats = new Seat[numberOfRows][numberOfColumns];
		for (int r = 1; r < numberOfRows + 1; r++) {
			String line = layoutFromTextFile.get(r);
			int c = 0;
			int seatIdColumn = 0;
			for (int i = 0; i < line.length(); i++) {

				if (Character.isDigit(line.charAt(i))) {
					boolean occupied = line.charAt(i) == '0' ? false : true;
					char seatLetter = (char) ('A' + r - 1);
					String seatRow = (seatIdColumn + 1) + "";
					String seatId = seatLetter + seatRow + "";
					Seat seat = new Seat(seatId, occupied, true);
					this.totalNoOfSeats++;
					if (occupied) {
						this.noOfOccupiedSeats++;
					} else {
						this.noOfAvailableSeats++;
					}
					this.seats[r - 1][c] = seat;
					seatIdColumn++;
					c++;
				} else if (line.charAt(i) == '-') {
					char seatLetter = (char) ('A' + r - 1);
					Seat seat = new Seat(seatLetter + (seatIdColumn + 1) + "", true, false);
					this.seats[r - 1][c] = seat;
					seatIdColumn++;
					c++;
				} else if (line.charAt(i) == 'x') {
					Seat seat = new Seat("", false, false);
					this.seats[r - 1][c] = seat;
					c++;
				}
			}
		}

	}
	
	/** 
	 * Gets the Number Of Rows of this SeatingLayout
	 * @return this SeatingLayout number of rows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}
	
	/** 
	 * Gets the Number Of Columns of this SeatingLayout
	 * @return this SeatingLayout number of columns
	 */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	
	/** 
	 * Gets the CinemaShowTime of this SeatingLayout
	 * @return this SeatingLayout's CinemaShowTime
	 */
	public int getCinemaShowTimeId() {
		return cinemaShowTimeId;
	}
	
	/** 
	 * Gets the Seats of this SeatingLayout
	 * @return this SeatingLayout's Seats
	 */
	public Seat[][] getSeatingLayout() {
		return seats;
	}

	/** 
	 * Change the Seats of this SeatingLayout
	 * @param seatId	 this SeatingLayout's Seat's ID
	 */
	public void updateSeatLayoutWithSeatId(String seatId) {
		for (int r = 0; r < numberOfRows; r++) {
			for (int c = 0; c < numberOfColumns; c++) {
				if (seats[r][c].getIsSeat()) {
					if (seats[r][c].getSeatId().equals(seatId)) {
						seats[r][c].assignSeat();

					}
				}
			}
		}
	}
	
	/** 
	 * Gets the Seats of this SeatingLayout by it's SeatID
	 * @param seatId	 this SeatingLayout's Seat's ID
	 * @return this SeatingLayout's Seat by that Seat ID
	 */
	public Seat getSeatWithSeatId(String seatId) {
		Seat seat = null;
		for (int r = 0; r < numberOfRows; r++) {
			for (int c = 0; c < numberOfColumns; c++) {
				if (seats[r][c].getSeatId().equals(seatId)) {
					seat = seats[r][c];
				}
			}
		}
		return seat;
	}
	
	/** 
	 * Change the Seats of this Seating Layout
	 * @param seats		 this SeatingLayout's Seat's
	 */
	public void setSeatingLayout(Seat[][] seats) {
		this.seats = seats;
	}

	/** 
	 * Gets the total number of seats of this SeatingLayout
	 * @return this SeatingLayout's total number of seats
	 */
	public int getTotalNoOfSeat() {
		return totalNoOfSeats;
	}
	
	/** 
	 * Gets the number of occupied seats of this SeatingLayout
	 * @return this SeatingLayout's number of occupied seats
	 */
	public int getNoOfOccupiedSeats() {
		return noOfOccupiedSeats;
	}
	
	/** 
	 * Gets the number of available seats of this SeatingLayout
	 * @return this SeatingLayout's number of available seats
	 */
	public int getNoOfAvailableSeats() {
		return noOfAvailableSeats;
	}
	
	/** 
	 * Gets the number of columns in the strings
	 * Iterate and check if the character at that string is a number
	 * if it's a number, increment
	 * e.g.    1  2     3  4  5  6  7  8     9 10
	 * returns 10
	 * @return this SeatingLayout's number columns seats
	 */
	public int getColumnFromTextFile(String text) {

		int rows = 0;
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) {
				rows++;
			}
		}
		return rows;
	}

	/** 
	 * Print out the array list of string from the text file
	 */
	public void printSeatingLayoutFromTextFile() {

		for (int i = 0; i < this.layoutFromTextFile.size(); i++) {
			System.out.println(this.layoutFromTextFile.get(i));
		}
	}
	
	/** 
	 * Gets the ArrayList of string of this Seating Layout
	 * @return this SeatingLayout's seat in array list to text file format
	 */
	public ArrayList<String> outputToFile() {
		ArrayList<String> output = new ArrayList<String>();
		output.add(layoutFromTextFile.get(0));
		for (int r = 0; r < this.numberOfRows; r++) {
			String line = "";
			char seatLetter = (char) ('A' + r);
			line += seatLetter + " ";
			for (int c = 0; c < this.numberOfColumns; c++) {
				if (seats[r][c] == null) {
					continue;
				}
				if (seats[r][c].getIsSeat()) {
					int isOccupied = seats[r][c].getIsOccupied() ? 1 : 0;

					line += "|" + isOccupied + "|";

				} else if (!seats[r][c].getIsSeat()) {
					if (seats[r][c].getSeatId().equals("")) {
						line += " x ";
					} else {
						line += " - ";

					}

				}
			}
			output.add(line);
		}
		return output;
	}
	
	/** 
	 * Print out the seats of this Seating Layout
	 * e.g. output:
	 *       1  2     3  4  5  6  7  8
	 *	  A  -  -  - |0||0||0||0||0| -  
	 *	  B |0||0| - |0||0||0||0||0||0|
	 *	  C |0||0| - |0||0||0||0||0||0|
	 *	  D |0||0| - |0||0||0||0||0||0|
	 *	  E |0||0| - |0||0||0||0||0||0|
	 *	  F |0||0| - |0||0||0||0||0||0|
	 *	  G |0||0| - |0||0||0||0||0||0|
	 *	  H |0||0| - |0||0||0||0||0||0|
	 * 
	 */
	public void printSeatingLayout() {
		System.out.println(layoutFromTextFile.get(0));

		for (int r = 0; r < this.numberOfRows; r++) {
			char seatLetter = (char) ('A' + r);
			System.out.print(seatLetter + " ");
			for (int c = 0; c < this.numberOfColumns; c++) {
				if (seats[r][c] == null) {
					continue;
				}
				if (seats[r][c].getIsSeat()) {
					int isOccupied = seats[r][c].getIsOccupied() ? 1 : 0;
					System.out.print("|" + isOccupied + "|");

				} else if (!seats[r][c].getIsSeat()) {

					System.out.print(" - ");
				}
			}
			System.out.println();
		}
	}
	
	/** 
	 * Gets the longest string in the array of string of this Seating Layout
	 * To calculate the how the Screens and Entrance will form
	 * 
	 */
	public String getColumnString() {
		String largestColumnString = "";
		for (String s : layoutFromTextFile) {
			if (s.length() > largestColumnString.length()) {
				largestColumnString = s;
			}
		}
		return largestColumnString;
	}
}
