package view;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;	

public class DeleteMovie {
	
	public static void main(String[] args) throws IOException, Exception {
		Scanner sc = new Scanner(System.in);
        String fileName = "Movie.txt";
        String line = null;
        int i = 1;
    
        try {
            System.out.println("-----------------------------------");
            System.out.println("Delete Movie");
            System.out.println("-----------------------------------");
            BufferedReader br = new BufferedReader(new FileReader(fileName)); 
            while((line = br.readLine()) != null) {
                String[] x = line.split(",");
                System.out.println(i++ +". " + x[0]);
            }   
            br.close();  
            System.out.println(i + ". Go back");
            System.out.print("Enter ID of movie to delete: ");
            
            int choice = sc.nextInt();
            while(choice>i || choice<1) {
            	System.out.print("Enter valid option:");
            	choice = sc.nextInt();
            }
            
            if(choice == i) {
            	MovieSetting.main(null);
            }
            else {
    			File file = new File("MovieTemp.txt");
    			PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
    			FileReader fileReader_new = new FileReader(fileName);
                BufferedReader bufferedReader_new = new BufferedReader(fileReader_new);
                i = 1;
                
                while((line = bufferedReader_new.readLine()) != null) {
                    if(i == choice) {
                    	i++;
                    	continue;
                    }
                    String Save = line;
                    pr.println(Save);
                    i++;
                }        
                bufferedReader_new.close();
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
