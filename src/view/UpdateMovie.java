package view;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class UpdateMovie {
	
	public static void main(String[] args) throws IOException, Exception {
		Scanner in = new Scanner(System.in);
        String fileName = "Movie.txt";
        String line = null;
        int i = 1;
    
        try {
            System.out.println("-----------------------------------");
            System.out.println("Update Movie");
            System.out.println("-----------------------------------");
            
            BufferedReader br = new BufferedReader(new FileReader(fileName)); 
            while((line = br.readLine()) != null) {
                String[] x = line.split(",");
                System.out.println(i++ +". " + x[0]);
            }   
            br.close();  
            System.out.println(i + ". Go back");
            System.out.print("Enter ID of movie to update: ");
            
            String choice = in.nextLine();
            while(Integer.valueOf(choice)>i || Integer.valueOf(choice)<1) {
            	System.out.print("Enter valid option:");
            	choice = in.nextLine();
            }
            
            if(Integer.valueOf(choice) == i) {
            	MovieSetting.main(null);
            }
            else {
    			File file = new File("MovieTemp.txt");
    			PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
    			FileReader fileReader_new = new FileReader(fileName);
                BufferedReader br_new = new BufferedReader(fileReader_new);
                i = 1;
                
                while((line = br_new.readLine()) != null) {
                    if(i == Integer.valueOf(choice)) {
                    	String[] oldString = line.split(",");
                    	System.out.println(Arrays.toString(oldString));
                    	String newPart, newFinal;
                    	System.out.println();
                    	System.out.println("Press enter if no updates for the category!");
                    	System.out.println();
                    	System.out.println("Movie Name:");
                    	newPart = in.nextLine();
                    	if(newPart.isEmpty()) {
                    		newFinal = oldString[0];
                    	}
                    	else {
                    		newFinal = newPart;
                    	}
                    	
                    	
                    	System.out.println();
                		System.out.println("Is the movie 3D?");
                		System.out.println("1: Yes");
                		System.out.println("2: No");
                		System.out.println("Enter your option:");
                		newPart = in.nextLine();
                		
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[1];
                    	}
                		else {
                			switch(Integer.valueOf(newPart)) {
                    		case 1: newPart = "true"; break;
                    		case 2: newPart = "false"; break;
                    		default: newPart = "false";
                    		}
                			newFinal = newFinal + "," + newPart;
                		}
                		
                    	
                    	
                    	System.out.println();
                    	System.out.println("Movie Type:");
                		System.out.println("1: Blockbuster");
                		System.out.println("2: Action");
                		System.out.println("3: Drama");
                		System.out.println("4: Comedy");
                		System.out.println("5: Horror");
                		newPart = in.nextLine();
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[2];
                    	}
                		else {
                			switch(Integer.valueOf(newPart)) {
                			case 1: newPart = "Blockbuster"; break;
                			case 2: newPart = "Action"; break;
                			case 3: newPart = "Drama"; break;
                			case 4: newPart = "Comedy"; break;
                			case 5: newPart = "Horror"; break;
                			default: newPart = "Not defined"; 
                			}
                			newFinal = newFinal + "," + newPart;
                		}
                		
                		System.out.println();
                		System.out.println("Movie Director: ");
                		newPart = in.nextLine();
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[3];
                    	}
                		else {
                			newFinal = newFinal + "," + newPart;
                		} 
                		
                		System.out.println();
                		System.out.println("Movie Synopsis: ");
                		newPart = in.nextLine();
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[4];
                    	}
                		else {
                			newFinal = newFinal + "," + newPart;
                		}
                		
                		System.out.println();
                		System.out.println("Movie Status: ");
                		System.out.println("1: Coming soon!");
                		System.out.println("2: Preview");
                		System.out.println("3: Now showing");
                		System.out.println("4: End of showing");
                		newPart = in.nextLine();
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[5];
                    	}
                		else {
                			switch(Integer.valueOf(newPart)) {
                			case 1: newPart = "Coming soon!"; break;
                			case 2: newPart = "Preview"; break;
                			case 3: newPart = "Now showing"; break;
                			case 4: newPart = "End of showing"; break;
                			default: newPart = "Not defined"; 
                			}
                			newFinal = newFinal + "," + newPart;
                		}
                		
                		System.out.println();
                		System.out.println("Movie Rating: ");
                		System.out.println("1: PG13");
                		System.out.println("2: NC16");
                		System.out.println("3: M18");
                		System.out.println("4: R21");
                		newPart = in.nextLine();
                		if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[6];
                    	}
                		else {
                			switch(Integer.valueOf(newPart)) {
                			case 1: newPart = "PG13"; break;
                			case 2: newPart = "NC16"; break;
                			case 3: newPart = "M18"; break;
                			case 4: newPart = "R21"; break;
                			default: newPart = "Not defined";
                			}
                			newFinal = newFinal + "," + newPart;
                		}
                		
                		System.out.println();
                		System.out.println("Update the new Movie Cast (Separate cast by commas): ");
                        newPart = in.nextLine();
                    	if(newPart.isEmpty()) {
                    		newFinal = newFinal + "," + oldString[7];
                    	} 
                    	else
                    	{   
                    		String[] newP=newPart.split("\\s*,\\s*");
                    		for(int a = 0; a < newP.length; a++) 
                    		newFinal = newFinal + "," + newP[a];
                    	}
                		
                	
                		pr.println(newFinal);
                		i++;
                		continue;
                    }
                		
                        String Save = line;
                        pr.println(Save);
                        i++;
                }        
                br_new.close();
    			pr.close();
    			
    		
    			
    			Files.deleteIfExists(Paths.get("Movie.txt")); 
       			Path source = Paths.get("MovieTemp.txt");
    			Files.move(source, source.resolveSibling("Movie.txt"));
    			AdminFunctions.main(null);
    			
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

	}
}