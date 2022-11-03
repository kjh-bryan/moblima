package Control;

import Entity.Customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomerController{
    

    public Customer getCustomer(int customerId) throws IOException{

        Customer customer = null;
        TicketController tC = new TicketController();

        BufferedReader br = new BufferedReader(new FileReader("src/Customer.csv"));
        String line;
        while( (line = br.readLine()) != null){
            String[] values = line.split(",");
            int id = Integer.parseInt(values[0]);
            if(id == customerId){
                customer = new Customer(customerId, values[1], tC.getBookings(customerId));

                br.close();
                return customer;
            }
        }

        br.close();
        return null;
    }
}
