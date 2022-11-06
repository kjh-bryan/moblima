package entity;

public class Seat {
	private String seatId;
	private boolean isOccupied = false;
	private boolean isSeat;
	
	public Seat(String seatId)
	{
		this.seatId = seatId;
	}
	
	public Seat(String seatId, boolean isOccupied,boolean isSeat)
	{
		this.seatId = seatId;
		this.isOccupied = isOccupied;
		this.isSeat = isSeat;
	}

	public String getSeatId() {
		return seatId;
	}
	public boolean getIsSeat() {
		return isSeat;
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
