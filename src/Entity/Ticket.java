package Entity;

public class Ticket extends Screening {
    
    private double price;
    private Screening ticketInfo;
    private int custId;
    private Cineplex location;
    private int cineNum;
    private String ticketId;
    private int screeningId;
    private Seat seat;
    public Ticket (String ticketId, int custId, double price, int screeningId){
        this.price = price;
        this.custId = custId;
        this.ticketId = ticketId;
        this.screeningId = screeningId;
    }

    public Screening getTicketInfo(){
        return this.ticketInfo;
    }

    public Cineplex getLocation(){
        return this.location;
    }

    public void setPrice(Customer customer){
        double price = 0;
        double seniorCitizens;
        double students;
        double students3D;
        double classModifier;
        double[] pricesByDay = new double[8];


        double[] prices = new double[12];
        this.price = price;
    }

    public void setScreening(Screening screening){
        this.ticketInfo = screening;
    }

    public Seat getSeat(){
        return this.seat;
    }

}

