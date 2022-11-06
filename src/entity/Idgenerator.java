package entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Idgenerator {
   int num,id; 
   String fileName=null; 
	public int generate(int role) throws IOException{
		if (role == 0) {
		 fileName= "src/database/admin.txt";}
	    
	    if (role == 1) {
		fileName="src/database/moviegoer.txt";}
	    
		try {
			BufferedReader br = null; 
			br = new BufferedReader(new FileReader(fileName)); 
			String line = br.readLine();
			if(line==null)
			{
				id = 1;
			}
			else 
			{
				while(line!=null ) {
				String[] x = line.split("\\|");
				num = Integer.valueOf(x[0]);
				line= br.readLine(); 
				}
				id = id+(num+1); 
				br.close();
			  
			}
		}
			    
		catch(FileNotFoundException ex) {
		    System.out.println(
		        "Unable to open file '" + 
		        fileName + "'");                
		}
		catch(IOException ex) {
		    ex.printStackTrace();
		}
		return id; 
			    }
			}

	

