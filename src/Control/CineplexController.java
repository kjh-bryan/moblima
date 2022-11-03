package Control;

import Entity.Cineplex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CineplexController {
    

    public Cineplex getCineplex(String location) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("src/Cineplex"));
        String line;
        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            if(values[0].equals(location)){
                //set the Cineplex object with the values in the CSV
                br.close();
                return null;
            }
        }
        br.close();
        return null;
    }
}
