package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
=======
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Arrays;
>>>>>>> 9dedbc6311eb8d03e63d7cf2b09f56a0c04bb394
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
	   ArrayList<String[]> entries = new ArrayList<>();
	  
		try {
			String line;
			int updatedSales;
			boolean found = false;
			Scanner sc = new Scanner(new FileInputStream("src/database/ticketsale.txt"));
			while(sc.hasNextLine()){
				line = sc.nextLine();
				String[] values = new String[2];
				StringTokenizer st = new StringTokenizer(line, "|");
				values[0] = st.nextToken().trim();
				values[1] = st.nextToken().trim();
				if(Integer.parseInt(values[0]) == movieid){
					updatedSales = Integer.parseInt(values[1]);
					updatedSales = updatedSales+1;
					values[1] = Integer.toString(updatedSales);
					System.out.println(values[1]);
					found = true;
				}

				entries.add(values);
			}

			if(found == false){
				sale = 1;
				String[] newEntry = {Integer.toString(movieid), Integer.toString(sale)};
				entries.add(newEntry);
			}
			PrintWriter pr = new PrintWriter(new FileOutputStream("src/database/ticketsale.txt"));
			PrintWriter pr2 = new PrintWriter(new FileOutputStream("src/database/ticketsale.txt", true));
			int append = 0;
			for(String[] i : entries){
				if(append == 0){
					pr.println(i[0]+ "|" + i[1]);
					append++;
				}else{
					pr2.println(i[0]+ "|" + i[1]);
				}
			}
	        pr.close();
			pr2.close();
			sc.close();
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