package Entity;

import java.util.*;

public class Movie {
    
    private String title;
    private int ageRating;
    private List<Screening> screenings;

    public List<Screening> getCurrentScreenings(){
        List<Screening> currentScreenings = new ArrayList<>();
    } 

    public Movie(String title, int ageRating) {

    }

    public String getTitle(){
        return this.title;
    }

    public void getScreenings(){

        int index = 0;
        int currentTime = 0;

        List<Screening> allAvailableScreenings = new ArrayList<>();
        allAvailableScreenings = AllScreenings.findScreenings(this.title);
        for(Screening i = allAvailableScreenings.get(index); index <= allAvailableScreenings.size(); index++){
            if(i.getTiming() < currentTime){
                allAvailableScreenings.remove(index);
            }
        }

        this.screenings = allAvailableScreenings;
    }

    public void showScreenings(){

        int index = 0;

        System.out.printf("Here are the screenings for %s: \n", this.title);
        for(Screening i = movie.screenings.get(index); index < this.screenings.size(); index++){
            System.out.printf("");
        }
    }

}

