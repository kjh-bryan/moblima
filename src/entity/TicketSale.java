package entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class TicketSale {
private int movieid; 
private int sale; 
public TicketSale() {}
  public TicketSale(int movieid, int sale) {
	this.movieid = movieid;
	this.sale = sale;
} 
  

public int getMovieid() {
	return movieid;
}


public int getSale() {
	return sale;
}


public void indicatesale(int movieid) {
	   int sale;
	   int num = 0; 
	  
		try {
		
			BufferedReader salebr = new BufferedReader(new FileReader("src/database/ticketsale.txt"));
	        String saleline = salebr.readLine();
	        BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/ticketsale.txt"));
	        PrintWriter pr = new PrintWriter(writer);
	        if (saleline == null )
	        {    
	        	sale = 1;
	        	writer.write(movieid+"|"+sale);
	        }
	        else 
	        {   
	        	while(saleline!=null) {
	        	String []x = saleline.split("\\|");
	            int xid = Integer.valueOf(x[0]); 
	        	if(xid==movieid)
	        	{
	        		num=num+Integer.valueOf(x[1]); 
	        		saleline=salebr.readLine();
	        		continue; 
	        	}
	               saleline=salebr.readLine();
	        	
	        }   
	        	
	        	pr.println(movieid+"|"+(num+1));
          }
	        salebr.close();
	        writer.close(); 
}
		 
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" + 
	            		"src/database/ticketsale.txt"+ "'");                
	    }
	    catch(IOException ex) {
	        ex.printStackTrace();
	    }
  }
}