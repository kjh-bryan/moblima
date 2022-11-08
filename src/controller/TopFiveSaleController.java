package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Review;
import entity.TicketSale;
public class TopFiveSaleController {

		public static void main(String[] args)throws IOException,Exception  {
		
	    String fileName = "src/database/ticketsale.txt";
	 
	    class ticketsaleCompare implements Comparator<TicketSale>
	    {
	        @Override
	        public int compare(TicketSale s1, TicketSale s2)
	        {
	            return s2.getSale() - s1.getSale();
	        }
	    }
	   
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(fileName));
	        ArrayList<TicketSale> saleRecords = new ArrayList<TicketSale>();
	        String currentLine = br.readLine();
	        
	        while (currentLine != null)
	        {
	            String[] saleDetails = currentLine.split("\\|");
	 
	            int movieid = Integer.valueOf(saleDetails[0]);
	 
	            int sale = Integer.valueOf(saleDetails[1]);
	           
	          
	 
	            saleRecords.add(new TicketSale(movieid,sale));
	 
	            currentLine = br.readLine();
	        }

	        Collections.sort(saleRecords, new ticketsaleCompare());
	     
	            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/sortTicketSale.txt"));
	 
	     
	            for (TicketSale ticketsale: saleRecords)
	            {
	                writer.write(ticketsale.getMovieid()+ "|" + ticketsale.getSale());
	     
	                writer.newLine();
	            }
	     
	    
	     
	            br.close();
	     
	            writer.close();
	            
	        
	    }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            ex.printStackTrace();
	        }

		}
	}

