package Control;

import Entity.Seat;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SeatController {

    public List<Seat> getSeats(int cineplexId, int cinemaNum) throws IOException{

        List<Seat> availableSeats = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/Seat.csv"));

        String line;

        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            if(values[2].equals(Integer.toString(cineplexId)) && values[3].equals(Integer.toString(cinemaNum)) && values[4].equals("false")){
                Seat seat = new Seat(values[1], cineplexId, cinemaNum, false);
                seat.generateUniqueSeatId();
                availableSeats.add(seat);
            }
        }

        br.close();
        return availableSeats;
    }

    public Seat getSeat(String seatId, List<Seat> availableSeats){
        
        int index = 0;

        for(Seat i = availableSeats.get(index); index < availableSeats.size(); index++){
            if(seatId.equals(i.getSeatId())){
                return i;
            }
        }

        return null;
    }

    public void showSeats(List<Seat> availableSeats){
        int index = 0;

        for(Seat i = availableSeats.get(index); index < availableSeats.size(); index++){
            System.out.printf("Seat %s is available\n", i.getSeatId());
        }
    }
    
}
