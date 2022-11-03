package Control;

import Entity.Movie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MovieController {
    
    public Movie getMovie(String title) throws IOException{

        Movie movie;

        BufferedReader br = new BufferedReader(new FileReader("src/Movie.csv"));

        String line;

        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            if(values[0].equals(title)){
                movie = new Movie(title, Integer.parseInt(values[1]));
                br.close();
                return movie;
            }
        }

        br.close();
        return null;
        
    }

    public void showTopMovies(List<Movie> movies, int by){

        Movie[] movieRatings = new Movie[movies.size()];
        Movie[] movieSales = new Movie[movies.size()];

        if(by == 0){
            sortMovies(movies, movieRatings, movieSales, 0);
            for(int i = 0; i < 5; i++){
                System.out.printf("%d: %s\n", i+1, movieSales[i].getTitle());
            }
        }else if(by == 1){
            sortMovies(movies, movieRatings, movieSales, 1);
            for(int i = 0; i < 5; i++){
                System.out.printf("%d: %s\n", i+1, movieRatings[i].getTitle());
            }
        }else System.out.println("Please enter a valid option");
    
    }

    public void sortMovies(List<Movie> movies, Movie[] movieRatings, Movie[] movieSales, int by){

        //by == 0, Movies sorted by TicketSales, by == 1, Movies sorted by Movie Ratings
        if(by == 0){
            int index = 0;
            for(Movie i = movies.get(index); index < movies.size(); index++){

            }
        }
    }
}
