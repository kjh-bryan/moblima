package entity;

public class Seat {
	private String seatId;
	private boolean isOccupied = false;
	
	public Seat(String seatId)
	{
		this.seatId = seatId;
	}
	
	public Seat(String seatId, boolean isOccupied)
	{
		this.seatId = seatId;
		this.isOccupied = isOccupied;
	}

	public String getSeatId() {
		return seatId;
	}
	
	public boolean getIsOccupied() {
		return isOccupied;
	}
	
	public void assignSeat()
	{
		isOccupied = true;
	}
	
	public void unassignSeat()
	{
		isOccupied = false;
	}
}
