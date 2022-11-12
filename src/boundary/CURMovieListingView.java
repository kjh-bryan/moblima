package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.MovieController;
import controller.UserInputValidationController;
import entity.Cast;
import entity.Movie;
import entity.MovieClassifiedRating;
import entity.MovieShowingStatus;
import entity.MovieType;
import entity.Review;
import entity.Ticket;

/**
 * This class represents the view for Admin
 * when Create/Update/Remove movie listing is selected
 * and Admin wishes to make changes to the corresponding information
*/

public class CURMovieListingView {
	
	/**
	 * Display the choice for Admin
	 * Create / Update or Remove 
	 * a Movie Listing
	*/
	public static void curMovieListingView()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n----------------------------------------------------------------------");
			System.out.println("----------- MOBLIMA - Create, Update, Remove Movie Listing -----------");
			System.out.println("----------------------------------------------------------------------");

			System.out.println("1: Create Movie Listing");
			System.out.println("2: Update Movie Listing");
			System.out.println("3: Remove Movie Listing");
			System.out.println("0: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					createMovieListingView();
					break;
				case 2:
					updateMovieListingView();
					break;
				case 3:
					deleteMovieListingView();
					

					break;
				case 0:
					
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	
	
	/**
	 * Create View for Admin
	 * A new movie is to be created
	*/
	public static void createMovieListingView()

	
	{
		System.out.println("============================");
		System.out.println("=== Create Movie Listing===");
		System.out.println("============================");
		
		int id = -1;
		
		System.out.println("Enter Movie Title: ");
		String movieTitle = UserInputValidationController.validateStringFromUser();
		int movieStatus = -1;
		while(movieStatus < 0 || movieStatus > 3)
		{
			System.out.println("Enter Movie Showing Status (0 for Coming soon, 1 for Preview, 2 for Now Showing, 3 for End Of Show)");
			
			movieStatus = UserInputValidationController.validateNumberFromUser();
		}
		MovieShowingStatus movieShowingStatus = null;
		switch(movieStatus)
		{
			case 0:
				movieShowingStatus = MovieShowingStatus.COMING_SOON;
				break;
			case 1:
				movieShowingStatus = MovieShowingStatus.PREVIEW;
				break;
			case 2:
				movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
				break;
			case 3:
				movieShowingStatus = MovieShowingStatus.END_OF_SHOW;
				break;
		}
		
		System.out.println("Enter Movie duration in mins: ");
		int movieDuration = UserInputValidationController.validateNumberFromUser();
		
		System.out.println( "Enter Start Date (yyyy-MM-dd): ");
		String startDate = UserInputValidationController.validateLocalDateFromUser();
		
		System.out.println( "Enter End Date (yyyy-MM-dd): ");
		String endDate = UserInputValidationController.validateLocalDateFromUser();
		
		LocalDate movieReleaseDate = LocalDate.parse(startDate);
		LocalDate movieEndDate = LocalDate.parse(endDate);
		
		
		System.out.println("Enter Movie Synopsis: ");
		String movieSynopsis = UserInputValidationController.validateStringFromUser();
		
		System.out.println("Enter Movie Director: ");
		String movieDirector = UserInputValidationController.validateStringFromUser();
		
		System.out.println("Enter number of cast: ");
		int numberOfCast = UserInputValidationController.validateNumberFromUser();
		ArrayList<Cast> movieCasts = new ArrayList<Cast>();
		for(int i = 0; i < numberOfCast; i++)
		{
			System.out.println("Enter cast " + (i+1) + "'s name: " );
			Cast cast = new Cast(UserInputValidationController.validateStringFromUser());
			movieCasts.add(cast);
		}
		
		int classifiedRating = -1;
		while(classifiedRating < 0 || classifiedRating > 5)
		{
			System.out.println("Enter Movie ClassifiedRating ");
			System.out.println("(0 for General, 1 for Parental Guidance, 2 for PG13, \n3 for NC16, 4 for M18, 5 for R21)");
		
			classifiedRating = UserInputValidationController.validateNumberFromUser();
		}
		MovieClassifiedRating movieClassifiedRating = null;
		switch(classifiedRating)
		{
			case 0:
				movieClassifiedRating = MovieClassifiedRating.G;
				break;
			case 1:
				movieClassifiedRating = MovieClassifiedRating.PG;
				break;
			case 2:
				movieClassifiedRating = MovieClassifiedRating.PG13;
				break;
			case 3:
				movieClassifiedRating = MovieClassifiedRating.NC16;
				break;
			case 4:
				movieClassifiedRating = MovieClassifiedRating.M18;
				break;
			case 5:
				movieClassifiedRating = MovieClassifiedRating.R21;
				break;
		}
		
		System.out.println("Enter Movie Genre: ");
		String movieGenre = UserInputValidationController.validateStringFromUser();
		

		System.out.println("Enter Movie Language: ");
		String movieLanguage = UserInputValidationController.validateStringFromUser();
		
		int mType = -1;
		while(mType < 0 || mType > 1)
		{
			System.out.println("Enter Movie Type (0 for 2D, 1 for 3D)");
			mType = UserInputValidationController.validateNumberFromUser();
		}
		MovieType movieType = null;
		switch(mType)
		{
			case 0:
				movieType = MovieType.TWOD;
				break;
			case 1:
				movieType = MovieType.THREED;
				break;
		}
		ArrayList<Review> reviewList = new ArrayList<Review>();
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		Movie movie = new Movie(id,movieTitle, movieShowingStatus, movieReleaseDate, movieEndDate, movieSynopsis, movieDirector, -1, movieCasts, reviewList, movieClassifiedRating, movieGenre, movieDuration, movieLanguage, movieType,ticketList);
		boolean success = MovieController.createMovie(movie);
		
		if(success)
		{
			System.out.println("Movie successfully created");
		}
		else
		{
			System.out.println("Movie could not be created");
		}
		
	}

	/**
	 * Update View for Admin
	 * Admin can  update a Movie Details
	 * Admin can also configure the Movie Showing Status to End Of Show
	 * so that the Movie won't be shown
	*/
	public static void updateMovieListingView()
	{
		printAllMovies();

		System.out.println();
		
		System.out.println("============================");
		System.out.println("=== Update Movie Listing===");
		System.out.println("============================");
		
		System.out.println("Enter the Movie ID you wish to update: ");
		int movieId = UserInputValidationController.validateNumberFromUser();
		String attributeToUpdate = "";
		Movie movie = MovieController.getMovieByMovieId(movieId);
		if(movie==null)
		{
			System.out.println("Movie does not exist!");
			return;
		}
		
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("---- What do you want to update? ----");
		System.out.println("-------------------------------------");
		System.out.println("1: Movie Title");
		System.out.println("2: Movie Showing Status");
		System.out.println("3: Movie Duration");
		System.out.println("4: Movie Start Date");
		System.out.println("5: Movie End Date");
		System.out.println("6: Movie Synopsis");
		System.out.println("7: Movie Director");
		System.out.println("8: Movie Classified Rating");
		System.out.println("9: Movie Genre");
		System.out.println("10: Movie Language");
		System.out.println("0: Exit");

		int option = UserInputValidationController.validateNumberFromUser();
		switch(option){
			case 1:
				attributeToUpdate = "Movie Title";
				System.out.println("Enter a new Movie Title: ");
				String newMovieTitle = UserInputValidationController.validateStringFromUser();
				movie.setMovieTitle(newMovieTitle);
				printAllMovies();
				break;

			case 2:
				attributeToUpdate = "Movie Status";
				int movieStatus = -1;
				while(movieStatus < 0 || movieStatus > 3)
				{
					System.out.println("Enter new Movie Showing Status \n(0 for Coming soon, 1 for Preview, 2 for Now Showing, 3 for End Of Show)");
					
					movieStatus = UserInputValidationController.validateNumberFromUser();
				}
				MovieShowingStatus movieShowingStatus = MovieShowingStatus.COMING_SOON;
				switch(movieStatus)
				{
					case 0:
						movieShowingStatus = MovieShowingStatus.COMING_SOON;
						break;
					case 1:
						movieShowingStatus = MovieShowingStatus.PREVIEW;
						break;
					case 2:
						movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
						break;
					case 3:
						movieShowingStatus = MovieShowingStatus.END_OF_SHOW;
						break;
				}
				movie.setMovieShowingStatus(movieShowingStatus);
				break;

			case 3:
				attributeToUpdate = "Movie Duration";
				// Duration
				System.out.println("Enter new Movie duration in mins: ");
				int movieDuration = UserInputValidationController.validateNumberFromUser();
				movie.setMovieDurationInMins(movieDuration);
				break;

			case 4:
				// Start Date
				attributeToUpdate = "Movie Start Date";
				
				System.out.println( "Enter new Start Date (yyyy-MM-dd): ");
				String startDate = UserInputValidationController.validateLocalDateFromUser();
				LocalDate movieStartDate = LocalDate.parse(startDate);
				movie.setMovieReleaseDate(movieStartDate);
				break;

			case 5:
				// End Date
				attributeToUpdate = "Movie End Date";
				System.out.println( "Enter new End Date (yyyy-MM-dd): ");
				String endDate = UserInputValidationController.validateLocalDateFromUser();
				LocalDate movieEndDate = LocalDate.parse(endDate);
				movie.setMovieEndDate(movieEndDate);
				break;

			case 6:
				// Movie Synopsis
				attributeToUpdate = "Movie Synopsis";
				System.out.println("Enter new Movie Synopsis: ");
				String movieSynopsis = UserInputValidationController.validateStringFromUser();
				movie.setMovieSynopsis(movieSynopsis);
				break;

			case 7:
				// Movie Director
				attributeToUpdate = "Movie Director";
				System.out.println("Enter new Movie Director: ");
				String movieDirector = UserInputValidationController.validateStringFromUser();
				movie.setMovieDirector(movieDirector);
				break;

			case 8:
				// Movie Classified Rating
				attributeToUpdate = "Movie Classified Rating";
				int classifiedRating = -1;
				while(classifiedRating < 0 || classifiedRating > 5)
				{
					System.out.println("Enter new Movie ClassifiedRating ");
					System.out.println("(0 for General, 1 for Parental Guidance, 2 for PG13, \n3 for NC16, 4 for M18, 5 for R21)");
				
					classifiedRating = UserInputValidationController.validateNumberFromUser();
				}
				MovieClassifiedRating movieClassifiedRating = null;
				switch(classifiedRating)
				{
					case 0:
						movieClassifiedRating = MovieClassifiedRating.G;
						break;
					case 1:
						movieClassifiedRating = MovieClassifiedRating.PG;
						break;
					case 2:
						movieClassifiedRating = MovieClassifiedRating.PG13;
						break;
					case 3:
						movieClassifiedRating = MovieClassifiedRating.NC16;
						break;
					case 4:
						movieClassifiedRating = MovieClassifiedRating.M18;
						break;
					case 5:
						movieClassifiedRating = MovieClassifiedRating.R21;
						break;
				}
				movie.setMovieClassifiedRating(movieClassifiedRating);
				break;

			case 9:
				// Movie Genre
				attributeToUpdate = "Movie Genre";
				System.out.println("Enter new Movie Genre: ");
				String movieGenre = UserInputValidationController.validateStringFromUser();
				movie.setMovieGenre(movieGenre);
				break;
			case 10:
				// Movie language
				attributeToUpdate = "Movie Language";
				System.out.println("Enter new Movie Language: ");
				String movieLanguage = UserInputValidationController.validateStringFromUser();
				movie.setMovieLanguage(movieLanguage);
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid option");
		}
		
		boolean success = MovieController.updateMovieByMovie(movie);
		if(success)
		{
			System.out.println(attributeToUpdate + " has been successfully updated");
		}
		else
		{
			System.out.println("Movie could not be updated!");
		}
		printAllMovies();
	}
	
	/**
	 * Delete View for Admin
	 * Admin can delete a Movie from the database
	*/
	public static void deleteMovieListingView()
	{
		printAllMovies();
		System.out.println();
		System.out.println("============================");
		System.out.println("=== Delete Movie Listing===");
		System.out.println("============================");
		
		System.out.println("Enter the Movie ID you wish to delete: ");
		int movieId = UserInputValidationController.validateNumberFromUser();
		boolean success = MovieController.deleteMovieByMovieId(movieId);
		if(success)
		{

			System.out.println("Deleted Successfully");
		}
		else
		{

			System.out.println("Movie could not be deleted");
		}

		printAllMovies();
	}
	
	/**
	 * A method to print all movies in the database
	 * Admin can select a movie from the list to Update or Delete
	*/
	public static void printAllMovies()
	{
		ArrayList<Movie> movieList = MovieController.getAllMovies();
		if(movieList.isEmpty())
		{
			System.out.println("No movies available");
			return;
		}
		for(Movie m : movieList)
		{
			ViewMovieDetailView.displayMovieDetail(m);
		}
	}

}
