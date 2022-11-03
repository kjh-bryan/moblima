package Models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

	/**
	 * This is a class used for defining Holiday objects
	 * @author Anon
	 *
	 */
	public class Holiday {
		
		/**
		 * This is the name of the Holiday
		 */
	    private String name;
	    
	    /**
	     * This is the Date object indicating the date of the Holiday
	     */
	    private Date date;
	    
	    /**
	     * This class initializes the values of Holiday object parameters
	     * @param name name of the Holiday
	     * @param date Date on which a Public Holiday falls 
	     */
		
		public Holiday(String name, Date date) {
	        this.name = name;
	        this.date = date;
	    }
		
		/**
		 * Gets the Name of the Public Holiday
		 * @return Name of the Public Holiday
		 */
		 public String getName() {
	        return name;
	    }
		
		/**
		 * Gets the Date of the Public Holiday
		 * @return Date of the Public Holiday
	     */
		public Date getDate() {
	        return date;
	    }
		
		/**
		 * This function checks if a given date is in the
		 * Holiday.txt file and returns a boolean value
		 * @param date Date to be checked for a Public Holiday
		 * @return boolean value indicating if the date is a holiday or not
		 * @throws IOException
		 */
		 
		 public static boolean checkHoliday(String date) throws IOException{
			 try {
				 	String line;
				 	String fileName = "Holiday.txt";
		            BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		            while((line = br.readLine()) != null) {
		                //System.out.println(line);
		                String[] x = line.split(",");
		                if(x[1].equals(date)) {
		                	return true;
		                }
		            }   
		            br.close();
		            return false;
		            
			 }
			 catch(IOException ex) {
				 ex.printStackTrace();
			 }
			 return false;
		 }
		 
		
	}


