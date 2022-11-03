package entity;

public enum SeatingRowLetter {
	A(0),B(1),C(2);
	
	
	private final int seatRowLetter;
	
	private SeatingRowLetter(int seatRowLetter) {
		this.seatRowLetter = seatRowLetter;
	}
	
	public int getSeatRowLetter()
	{
		return seatRowLetter;
	}
}
