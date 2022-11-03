package Entity;

import java.util.List;
import java.util.ArrayList;
public class Cinema {

    private int cinemaId;
    private Cineplex mainSite;
    private int numOfSeatsAvailable;
    private String type;
    private Screening[] screenings;
    private List<Seat> availableSeats;

    public Cinema (int cinemaId, Cineplex mainSite, int numOfSeatsAvailable, String type, Screening[] screenings){
        this.cinemaId = cinemaId;
        this.mainSite = mainSite;
        this.numOfSeatsAvailable = numOfSeatsAvailable;
        this.type = type;
        this.screenings = screenings;
    }

    public Screening[] getScreenings(){
        return this.screenings;
    }

    public int getCinemaId(){
        return this.cinemaId;
    }

    public String getType(){
        return this.type;
    }


}

