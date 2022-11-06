package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.time.LocalDateTime;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import entity.Ticket;
import entity.CinemaClass;
import entity.TicketType;
import entity.TicketDay;
import entity.Movie;
import entity.CinemaShowTime;
import entity.Cineplex;
import entity.Cinema;

public class TicketController {

    private static final String SEPARATOR = "|";
    private static final String databaseTableName = "src/database/ticket.txt";

    private final static Logger logger = Logger.getLogger(MovieGoerController.class.getName());

    public static ArrayList<Ticket> getAllTickets(){
        
        ArrayList<Ticket> allTicketList = new ArrayList<>();
        try{
		    Scanner sc = new Scanner(new FileInputStream(databaseTableName));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				TicketType movieGoerClass  = TicketType.valueOf(stringTokenizer.nextToken().trim());
				TicketDay dayOfSession = TicketDay.valueOf(stringTokenizer.nextToken().trim());
				LocalDateTime dateTimeOfSession = LocalDateTime.parse(stringTokenizer.nextToken().trim());
				int showTime = Integer.parseInt(stringTokenizer.nextToken().trim());
				CinemaClass cinemaClass = CinemaClass.valueOf(stringTokenizer.nextToken().trim());
				double ticketPrice = Double.parseDouble(stringTokenizer.nextToken().trim());
				boolean isHoliday = Boolean.parseBoolean(stringTokenizer.nextToken().trim());
                int movieGoerId = Integer.parseInt(stringTokenizer.nextToken().trim());

                Ticket ticket = new Ticket(cinemaClass, movieGoerClass, dayOfSession, ticketPrice, isHoliday, movieGoerId);
                ticket.setCinemaShowTimeId(showTime);
                ticket.setTicketDateTime(dateTimeOfSession);
				allTicketList.add(ticket);
			}

            sc.close();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getAllTickets() exception occured : " + e.getLocalizedMessage());
		}
		
		return allTicketList;
	}

    public static ArrayList<Ticket> getTicketsByMovieGoerId(int id){
        
        ArrayList<Ticket> allTickets = getAllTickets();
        ArrayList<Ticket> movieGoerTickets = new ArrayList<>();

        for(Ticket i: allTickets){
            if(i.getMovieGoerId() == id){
                movieGoerTickets.add(i);
            }
        }

        return movieGoerTickets;
    }

    public static ArrayList<CinemaShowTime> getCinemaShowTimesFromMovieGoerTickets(ArrayList<Ticket> tickets){

        ArrayList<CinemaShowTime> showTimes = new ArrayList<>();

        for(Ticket i: tickets){
            showTimes.add(CinemaShowTimeController.getCinemaShowTimeByShowTimeId(i.getCinemaShowTimeId()));
        }

        return showTimes;

    }

    public static ArrayList<Movie> getMoviesFromMovieGoerCinemaShowTimes(ArrayList<CinemaShowTime> showTimes){

        ArrayList<Movie> movies = new ArrayList<>();

        for(CinemaShowTime i: showTimes){
            movies.add(MovieController.getMovieByMovieId(i.getMovieId()));
        }

        return movies;

    }

    public static void showMovieGoerBookings(ArrayList<Ticket> tickets, ArrayList<CinemaShowTime> showTimes, ArrayList<Movie> movies){

        System.out.println("Here are your bookings:");
        int index = 0;

        while(index < tickets.size()){
            Ticket i = tickets.get(index);
            CinemaShowTime j = showTimes.get(index);
            Movie k = movies.get(index);
            Cinema v = CinemaController.getCinemaByCinemaCode(j.getCinemaCode());
            Cineplex c = CineplexController.getCineplexByCinemaCode(j.getCinemaCode());

            //Date, time: movieTitle, cinemaClass cinemaCode, CineplexName
            System.out.printf("%tB %<te, %<tY, %<tl:%<tM %<Tp: %s, %s %s, %s\n", i.getTicketDateTime(), k.getMovieTitle(), v.getCinemaClass(), j.getCinemaCode(), c.getCinemaLocatedMall());
            index++;
        }
    }

    public static boolean createTicket(Ticket newTicket)
	{

		try {
		UserInputValidationController.createDatabaseTableFile(databaseTableName);

		PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
		
		out.append(newTicket.getTicketType() + SEPARATOR + newTicket.getTicketDayToString() + SEPARATOR + newTicket.getTicketDateTime() +SEPARATOR+ newTicket.getCinemaShowTimeId() + SEPARATOR + newTicket.getCinemaClass() + SEPARATOR +newTicket.getTicketPrice() + SEPARATOR+ newTicket.isHoliday() + SEPARATOR + newTicket.getMovieGoerId() +"\n");
		
		
		out.close();
		return true;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createMovieGoerAccount() exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}
}
