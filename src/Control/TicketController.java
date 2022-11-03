package Control;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import Entity.Ticket;
import Entity.Customer;

public class TicketController {

    public Ticket getTicket(String ticketId) throws IOException{

        Ticket ticket;

        BufferedReader br = new BufferedReader(new FileReader("src/Ticket.csv"));

        String line;
        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            String id = values[0];
            if(id == ticketId){
                ticket = new Ticket(id, Integer.parseInt(values[1]), Double.parseDouble(values[2]), Integer.parseInt(values[3]));
                br.close();
                return ticket;
            }
        }

        br.close();
        return null;
    }

    public List<Ticket> getBookings(int customerId) throws IOException{

        List<Ticket> customerBookings = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/Ticket.csv"));

        String line;
        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            if(Integer.parseInt(values[1]) == customerId){
                customerBookings.add(getTicket(values[0]));
            }
        }

        br.close();
        return customerBookings;

    }

    public void addTicket(int customerId, int screeningId, String seatId, int price) throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Ticket.csv"));

        String ticketId = Integer.toString(customerId) + Integer.toString(screeningId) + seatId;

        bw.write(ticketId + ", " + customerId + ", " + screeningId + ", " + seatId + ", " + price);

        bw.close();
    }

    public double getPrice(Customer customer){
        return 5.00;
    }

    public void showBookings(List<Ticket> tickets){

        int index = 0;

        for(Ticket i = tickets.get(index); index < tickets.size(); index++){
            System.out.printf("%s: %s, %s, %td/%tm/%ty %tk:%tM\n", i.getFilm().getTitle(), i.getCineplexId(), i.getSeat().getSeatId(), i.getTiming());
        }
    }
    
}
