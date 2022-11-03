package Entity;

import java.util.Date;

public class Screening extends Movie {
    
    private Movie film;
    private Date timing;
    private int screen;
    private int cineplexId;
    private int screeningId;


    public Movie getFilm(){
        return this.film;
    }

    public Date getTiming(){
        return this.timing;
    }

    public int getScreeningId(){
        return this.screeningId;
    }

    public int getScreen(){
        return this.screen;
    }

    public int getCineplexId(){
        return this.cineplexId;
    }

}