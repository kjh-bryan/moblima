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

public class CURMovieListingView {
	
	public static void cur_movie_listing_view()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("Create, Update, Remove Movie Listing");
			System.out.println("------------------------------");
			System.out.println("1: Create Movie Listing");
			System.out.println("2: Update Movie Listing");
			System.out.println("3: Remove Movie Listing");
			System.out.println("0: Exit");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:
					create_movie_listing_view();
					break;
				case 2:
					update_movie_listing_view();
					break;
				case 3:
					delete_movie_listing_view();
					

					break;
				case 0:
					
					return;
				
				default:
					System.out.println("Incorrect Option");
					break;
			}
		}
	}
	public static void create_movie_listing_view()

	
	{
		System.out.println("============================");
		System.out.println("=== Create Movie Listing===");
		System.out.println("============================");
		
		int id = -1;
		
		System.out.println("Enter Movie Title: ");
		String movieTitle = UserInputValidationController.validateStringFromUser();
		int movieStatus = -1;
		while(movieStatus < 0 || movieStatus > 2)
		{
			System.out.println("Enter Movie Showing Status (0 for Coming soon, 1 for Preview, 2 for Now Showing)");
			
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
		}
		
		System.out.println("Enter Movie duration in mins: ");
		int movieDuration = UserInputValidationController.validateNumberFromUser();
		
		System.out.println( "Enter Start Date (yyyy-MM-dd): ");
		String startDate = UserInputValidationController.validateLocalDateFromUser();
		
		System.out.println( "Enter End Date (yyyy-MM-dd): ");
		String endDate = UserInputValidationController.validateLocalDateFromUser();
		
		LocalDate movieStartDate = LocalDate.parse(startDate);
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
			System.out.println("Enter cast " + i+1 + "'s name: " );
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
		
		Movie movie = new Movie(id,movieTitle, movieShowingStatus, movieStartDate, movieEndDate, movieSynopsis, movieDirector, id, movieCasts, null, movieClassifiedRating, movieGenre, movieDuration, movieLanguage, movieType);
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

	public static void update_movie_listing_view()
	{
		printAllMovies();

		System.out.println();
		
		System.out.println("============================");
		System.out.println("=== Update Movie Listing===");
		System.out.println("============================");
		
		System.out.println("Enter the Movie ID you wish to update: ");
		int movieId = UserInputValidationController.validateNumberFromUser();

		Movie movie = MovieController.getMovieByMovieId(movieId);
		if(movie==null)
		{
			System.out.println("Movie does not exist!");
			return;
		}
		
		System.out.println();
		System.out.println("What do you want to update? ");
		System.out.println("------------------------------");
		System.out.println("1: Movie Title");
		System.out.println("2: Movie Showing Status");
		System.out.println("3: Movie Duration");
		System.out.println("4: Movie Start Date");
		System.out.println("5: Movie End Date");
		System.out.println("6: Movie Synopsis");
		System.out.println("7: Movie Director");
		System.out.println("8: Movie Classified Rating");
		System.out.println("9: Movie Genre");
		System.out.println("9: Movie Rating");
		System.out.println("10: Movie Rating");
		System.out.println("0: Exit");

		int option = UserInputValidationController.validateNumberFromUser();
		switch(option){
			case 1:
				System.out.println("Enter a new Movie Title: ");
				String newMovieTitle = UserInputValidationController.validateStringFromUser();
				movie.setMovieTitle(newMovieTitle);
				printAllMovies();
				break;

			case 2:
				int movieStatus = -1;
				while(movieStatus < 0 || movieStatus > 2)
				{
					System.out.println("Enter new Movie Showing Status \n(0 for Coming soon, 1 for Preview, 2 for Now Showing)");
					
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
				}
				movie.setMovieShowingStatus(movieShowingStatus);
				break;

			case 3:
				// Duration
				System.out.println("Enter new Movie duration in mins: ");
				int movieDuration = UserInputValidationController.validateNumberFromUser();
				movie.setMovieDurationInMins(movieDuration);
				break;

			case 4:
				// Start Date
				
				System.out.println( "Enter new Start Date (yyyy-MM-dd): ");
				String startDate = UserInputValidationController.validateLocalDateFromUser();
				LocalDate movieStartDate = LocalDate.parse(startDate);
				movie.setMovieStartDate(movieStartDate);
				break;

			case 5:
				// End Date
				System.out.println( "Enter new End Date (yyyy-MM-dd): ");
				String endDate = UserInputValidationController.validateLocalDateFromUser();
				LocalDate movieEndDate = LocalDate.parse(endDate);
				movie.setMovieEndDate(movieEndDate);
				break;

			case 6:
				// Movie Synopsis
				System.out.println("Enter new Movie Synopsis: ");
				String movieSynopsis = UserInputValidationController.validateStringFromUser();
				movie.setMovieSynopsis(movieSynopsis);
				break;

			case 7:
				// Movie Director
				System.out.println("Enter new Movie Director: ");
				String movieDirector = UserInputValidationController.validateStringFromUser();
				movie.setMovieDirector(movieDirector);
				break;

			case 8:
				// Movie Classified Rating
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
				System.out.println("Enter new Movie Genre: ");
				String movieGenre = UserInputValidationController.validateStringFromUser();
				movie.setMovieGenre(movieGenre);
				break;
			case 10:
				// Movie language
				System.out.println("Enter new Movie Language: ");
				String movieLanguage = UserInputValidationController.validateStringFromUser();
				movie.setMovieLanguage(movieLanguage);
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid option");
		}
		
		MovieController.updateMovieByMovie(movie);
	}
	
	public static void delete_movie_listing_view()
	{
		printAllMovies();
		System.out.println();
		System.out.println("============================");
		System.out.println("=== Delete Movie Listing===");
		System.out.println("============================");
		
		System.out.println("Enter the Movie ID you wish to delete: ");
		int movieId = UserInputValidationController.validateNumberFromUser();
		MovieController.deleteMovieByMovieId(movieId);
		
		System.out.println("Deleted Successfully");

		printAllMovies();
	}
	
	
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
			ViewMovieDetailView.display_movie_detail(m);
		}
	}

}
