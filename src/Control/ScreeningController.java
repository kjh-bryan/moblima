package Control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Entity.Screening;
import java.util.List;
import java.util.ArrayList;
import Entity.Movie;
import java.util.Date;

public class ScreeningController {
    
    //findScreening method, takes in movie title and cineplex, and edits an empty list with screenings fro
    //that location.
    public boolean findScreening(String title, List<Screening> screenings ) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("src/Screenings.csv"));

        List<Screening> availableScreenings = new ArrayList<>();
        String line;
        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            if(values[0].equals(title)){
                //availableScreening.set...(values[...])
            }
        }

        if(availableScreenings.size() <= 0){
            br.close();
            return false;
        }

        screenings = availableScreenings;
        br.close();
        return true;
    }

    //getScreenings updates the screenings list by getting rid of past screenings
    public void getScreenings(Movie movie, List<Screening> screenings){

        Date currentDate = new Date();
        int index = 0;

        for(Screening i = screenings.get(index); index < screenings.size(); index++){
            if(i.getTiming().before(currentDate)){
                screenings.remove(index);
            }
        }

    }

    public void showScreenings(List<Screening> screenings){

        int index = 0;

        for(Screening i = screenings.get(index); index < screenings.size(); index++){
            System.out.printf("%d: %s, %d, %td/%tm/%ty %tk:%tM", index+1, i.getFilm().getTitle(), i.getScreen(), i.getTiming());
        }
    }

}
