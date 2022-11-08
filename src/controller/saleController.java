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

import entity.TicketSale;

public class saleController {
	//static int movieid; 
	//static int sale;
public static void main(String[] args)throws IOException,Exception  {
	
}
		public static void generate(int movieID ) {
			class ticketsaleCompare implements Comparator<TicketSale>
		    {
		        @Override
		        public int compare(TicketSale s1, TicketSale s2)
		        {
		            return s2.getSale() - s1.getSale();
		        }
		    }
	    String fileName = "src/database/ticketsale.txt";
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(fileName));
	        ArrayList<TicketSale> saleRecords = new ArrayList<TicketSale>();
	        String currentLine = br.readLine();
	        
	        while (currentLine != null)
	        {
	            String[] saleDetails = currentLine.split("\\|");
	            if (Integer.valueOf(saleDetails[0])==movieID) {
	            int id = Integer.valueOf(saleDetails[0]);
	            int num =Integer.valueOf(saleDetails[1]);
	            int le = num+1;
	            System.out.println(le);
	            saleRecords.add(new TicketSale(id,num+1));
	            }
	            else {
	            int movieid = Integer.valueOf(saleDetails[0]);
	            int sale = Integer.valueOf(saleDetails[1]);
	            saleRecords.add(new TicketSale(movieid,sale));
	            }
	            currentLine = br.readLine();
	        }
	        br.close();
	        Collections.sort(saleRecords, new ticketsaleCompare());
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/TicketSale.txt"));
 
     
            for (TicketSale ticketsale: saleRecords)
            {
                writer.write(ticketsale.getMovieid()+ "|" + ticketsale.getSale());
     
                writer.newLine();
            }
     
    
     
            
     
            writer.close();
            System.out.println("done"); 
        
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

	          

