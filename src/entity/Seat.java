package entity;

/**
 * A Seat that belongs to a Seating Layout.
 * There can be many Seats that belongs to a single Seating Layout
 */

public class Seat {
	/**
	 * This Seat's ID
	 */
	private String seatId;
	/**
	 * This Seat's status of occupied
	 */
	private boolean isOccupied = false;
	/**
	 * This Seat whether it is valid
	 */
	private boolean isSeat;
	
	/** 
	 * Create a new Seat with the seat ID
	 * @param seatId						This Seat ID 
	 */
	public Seat(String seatId)
	{
		this.seatId = seatId;
	}
	
	/** 
	 * Create a new Seat with the given attributes
	 * @param seatId					This Seat ID 
	 * @param isOccupied				This Seat if its occupied
	 * @param isSeat					This Seat is valid (Could be a walk way)
	 */
	public Seat(String seatId, boolean isOccupied,boolean isSeat)
	{
		this.seatId = seatId;
		this.isOccupied = isOccupied;
		this.isSeat = isSeat;
	}

	/** 
	 * Gets the Seat ID of this Seat
	 * @return this Seat ID
	 */
	public String getSeatId() {
		return seatId;
	}
	
	/** 
	 * Gets whether this Seat is valid (Walkway or Seat)
	 * @return this Seat is a seat
	 */
	public boolean getIsSeat() {
		return isSeat;
	}
	
	/** 
	 * Gets whether this Seat is occupied
	 * @return this Seat occupied 
	 */
	public boolean getIsOccupied() {
		return isOccupied;
	}
	
	/** 
	 * Change this Seat to occupied
	 */
	public void assignSeat()
	{
		isOccupied = true;
	}
	
	/** 
	 * Change this Seat to unoccupied
	 */
	public void unassignSeat()
	{
		isOccupied = false;
	}
}
