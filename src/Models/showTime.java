package Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
	 * Represents a particular Show Time
	 * 
	 * @author Anon
	 *
	 */
	public class showTime {
		
		private String movieName;   
		private String rating;
	    private String date;   
		private String[] time_arr;
		
		
		public showTime(String movieName, String rating, String date, String[] time_arr) {
			super();
			this.movieName = movieName;
			this.rating = rating;
			this.date = date;
			this.time_arr = time_arr;
		}


		public String getMovieName() {
			return movieName;
		}


		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}


		public String getRating() {
			return rating;
		}


		public void setRating(String rating) {
			this.rating = rating;
		}


		public String getDate() {
			return date;
		}


		public void setDate(String date) {
			this.date = date;
		}


		public String[] getTime_arr() {
			return time_arr;
		}


		public void setTime_arr(String[] time_arr) {
			this.time_arr = time_arr;
		}

		public void listShowtime() throws IOException{
			String time= String.join(",", this.getTime_arr());
			String Save = this.getMovieName() + ","+ this.getRating() + "," + this.getDate() + "," + time; 
			try {
				File file = new File("showtime.txt");
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
