package Models;
import java.io.*;
import java.util.Arrays;

public class Movies {

	private String movieName;
	private String movieType;
	private String movieStatus; // can convert it to int and specify each int's meaning
	private String[] movieCast;
	private String movieDirector;
	private String movieSynopsis;
	private String movieRating; // rating can be out of 10 or 100
	private float movieSales;
	private boolean is3D;
	

	public Movies(String movieName, String movieType, String movieStatus, String[] movieCast, String movieDirector,
			String movieSynopsis, String movieRating, boolean is3d) {
		this.movieName = movieName;
		this.movieType = movieType;
		this.movieStatus = movieStatus;
		this.movieCast = movieCast;
		this.movieDirector = movieDirector;
		this.movieSynopsis = movieSynopsis;
		this.movieRating = movieRating;
		this.movieSales = 0;
		is3D = is3d;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public String getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}

	public String[] getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(String[] movieCast) {
		this.movieCast = movieCast;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public boolean isIs3D() {
		return is3D;
	}

	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}

	public static boolean check3D(String a) throws IOException {
		String fileName = "Movie.txt";
		String line;
        BufferedReader br = new BufferedReader(new FileReader(fileName)); 
        while((line = br.readLine()) != null) {
            String[] x = line.split(",");
            if(x[0].equals(a)) {
            	if(x[1].equals("true"))
            		return true;
            	else
            		return false;
            	}
            }
        br.close();
		
		return false;
	}

	public void writeMovie() throws IOException{
		String cast= String.join(",", this.getMovieCast());
		String Save = this.getMovieName() + ","+ this.is3D + "," + this.getMovieType() + "," + this.getMovieDirector() + "," + this.getMovieSynopsis() + "," + this.getMovieStatus() + "," + this.getMovieRating() + "," + cast;
		try {
			File file = new File("Movie.txt");
			PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			pr.println(Save);
			pr.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
