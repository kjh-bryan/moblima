package Entity;

public class Seat {
    
    private String seatId; //int:char, eg. 13D
    private boolean available;
    private String uniqueSeatId; //unique seat, string: seatId|cineplexId|cinemaNum
    private int cineplexId;
    private int cinemaNum;

    public Seat(String seatId, int cineplexId, int cinemaNum, boolean available){
        this.seatId = seatId;
        this.cineplexId = cineplexId;
        this.cinemaNum = cinemaNum;
        this.available = available;
    }

    public boolean isAvailable(){
        return this.available;
    }

    public String getSeatId(){
        return this.seatId;
    }

    public void generateUniqueSeatId(){

        this.uniqueSeatId = this.seatId + Integer.toString(this.cineplexId) + Integer.toString(this.cinemaNum);
    }
}
