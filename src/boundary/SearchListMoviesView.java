package boundary;

import java.util.ArrayList;

import controller.MovieController;
import controller.UserInputValidationController;
import entity.Movie;
import entity.MovieClassifiedRating;
import global.Constants;

/**
 * This class the View when User selected Explore Movies option
 *	A list of options will be available for User to further select
 *	
*/

public class SearchListMoviesView {
	
	/**
	 * The view to be shown to User, to select an options of
	 * List All Movies, Search by Movie ID or Title, List Top 5 Movies
	 * or Go Back
	*/
	public static void searchListMoviesView()
	{
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------------------------------------");
			System.out.println("----------------- MOBLIMA - Explore Movies -----------------");
			System.out.println("------------------------------------------------------------");
	        System.out.println("1: List All Movies");
			System.out.println("2: Search by Movie ID");
			System.out.println("3: Search by Movie Title");
	        System.out.println("4: List Top 5 Movies");
	        System.out.println("0: Go Back");
	        System.out.println();
			System.out.print("Please Select an Option: ");
	        System.out.println();
			Movie movie;
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:	// List all movies
					
					movie = listAllMovies();
					System.out.println();
					if(movie != null)
					{
						goBack = true;
						ViewMovieDetailView.viewMovieDetailView(movie);
						return;
					}
					
					break;
				case 2:	// Search movie by ID
					movie = searchMovieById();

					System.out.println();
					
					if(movie != null)
					{
						goBack = true;

						ViewMovieDetailView.viewMovieDetailView(movie);
						return;
					}
					break;
				case 3:	// Search movie by Title
					movie = searchMoviesByTitle();
					System.out.println();
					if(movie != null)
					{
						goBack = true;

						ViewMovieDetailView.viewMovieDetailView(movie);
						return;
					}
					break;
				case 4:	//List Top 5 Movies
					ListTop5View.listTop5View();
					return;	
				case 0:	// Go Back
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
	
	
	/**
	 * Print out all the movie details in the array list 
	 * @param movieList			List of Movie to be displayed
	*/
	protected static void printMovieListDetails(ArrayList<Movie> movieList) {

		
		if(!movieList.isEmpty())
		{
			for(Movie movie : movieList)
			{
				printMovieBriefDescription(movie);
			}
		}
	}
	
	/**
	 * The View to be shown when User select option 1 to List All Movies
	 * Gets all showing movie in the database using the controller
	 * User can then select the Movie by entering the Movie ID
	 * @return	the Movie if User selected 
	*/
	private static Movie listAllMovies()
	{

		ArrayList<Movie> movieList = MovieController.getAllShowingMovie();
		if(movieList.isEmpty())
		{
			System.out.println("No movies available");
			return null;
		}
		printMovieListDetails(movieList);
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n-----------------------------------------------------------");
			System.out.println("---------------- MOBLIMA - List All Movies ----------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("Select a Movie to view Movie Details by entering the Movie ID (Enter 0 to Go Back): ");
			System.out.println("-----------------------------------------------------------");
			System.out.println();
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			
			if(movieId == 0)
			{
				goBack = true;
				return null;
			}
			else
			{
				Movie movie = MovieController.getMovieByMovieId(movieId);
				return movie;
			}
		}
		return null;
	}
	/**
	 * The View to be shown when User select option 3 to Search By Movie Title
	 * Gets all showing movie in the database that contains the Title
	 * User can then select the Movie by entering the Movie ID
	 * @return	the Movie if User selected 
	*/
	public static Movie searchMoviesByTitle() {
		
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n-----------------------------------------------------------");
			System.out.println("------------- MOBLIMA - Search Movie By Title -------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("Enter Movie Title to view details (Enter 0 to go back) :");
			System.out.println("-----------------------------------------------------------");
			System.out.println();
			
			String movieTitle = UserInputValidationController.validateStringFromUser();
			
			if(movieTitle.equals("0"))
			{
				goBack = true;
				return null;
			}
			else
			{
				ArrayList<Movie> movieList = MovieController.getMoviesByMovieTitle(movieTitle);
				if(movieList.isEmpty())
				{
					System.out.println("Movie Title does not exist. Try again");
					continue;
				}
				return selectOneMovieFromMovieTitle(movieList);
			}
		}
		return null;
		
	}
	
	/**
	 * The View to be shown after entering a Movie Title after selecting the
	 * option of Search By Title
	 * User can then select the Movie by entering the Movie ID
	 * @param movieList 			a List of Movie passed when Search via movie Title
	 * @return	the Movie if User selected 
	*/
	public static Movie selectOneMovieFromMovieTitle(ArrayList<Movie> movieList)
	{
		printMovieListDetails(movieList);
		boolean goBack = false;
		while(!goBack)
		{
			System.out.println("\n-----------------------------------------------------------");
			System.out.println("---------- MOBLIMA - Search Movie by Movie Title ----------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("Enter Movie ID to view details (Enter 0 to go back) :");
			System.out.println("-----------------------------------------------------------");
			
			System.out.println();
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			
			if(movieId == 0)
			{
				goBack = true;
				return null;
			}
			else
			{
				Movie movie = null;
				for(Movie m : movieList)
				{
					if(m.getMovieId() == movieId)
					{
						movie = m;
					}
				}
				if(movie == null)
				{
					System.out.println("No Movie ID exist in the list");
				}
				else
				{
					return movie;
					
				}
			}
		}
		return null;
		
	}
	
	
	/**
	 * The View to be shown after User select Option 2 to Search By Movie ID
	 * User can then select the Movie by entering the Movie ID
	 * @return	the Movie if User selected 
	*/
	public static Movie searchMovieById()
	{
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n----------------------------------------------------------");
			System.out.println("----------- MOBLIMA - Search Movie by Movie ID -----------");
			System.out.println("----------------------------------------------------------");
			System.out.println("Enter Movie ID to view details (Enter 0 to go back) :");
			System.out.println("----------------------------------------------------------");
			System.out.println();
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			
			if(movieId == 0)
			{
				goBack = true;
				return null;
			}
			else
			{
				Movie movie = MovieController.getShowingMovieByMovieId(movieId);
				return movie;
			}
		}
		return null;
	}
	
	/**
	 * Print out the Movie Brief Description
	 * @param	movie 			The movie to be printed
	*/
	public static void printMovieBriefDescription(Movie movie)
	{
		int movieId = movie.getMovieId();
		String title = movie.getMovieTitle();
		String movieGenre = movie.getMovieGenre();
		MovieClassifiedRating movieClassifiedRating = movie.getMovieClassifiedRating();
		int movieDurationInMins = movie.getMovieDurationInMins();
		String movieLanguage = movie.getMovieLanguage();
		
		String longestWord = "";
		
		if(title.length() > movieGenre.length() && title.length() > movieLanguage.length())
		{
			longestWord = title;
		}
		else if(title.length() < movieGenre.length() && movieGenre.length() > movieLanguage.length())
		{
			longestWord = movieGenre;
		}
		else
		{
			longestWord = movieLanguage;
		}
		
		String divider = generateDivider(longestWord);
		String movieIdString = formatMovieDescriptionString("Movie ID: " + movieId,divider);
		String titleString = formatMovieDescriptionString(title,divider);
		String movieGenreString = formatMovieDescriptionString(movieGenre,divider);
		String emptySpace = formatMovieDescriptionString("", divider);
		String movieClassifiedRatingString = formatMovieDescriptionString(movieClassifiedRating+"",divider);
		String movieDurationInMinsString = formatMovieDescriptionString(movieDurationInMins+"mins",divider);
		String movieLanguageString = formatMovieDescriptionString(movieLanguage,divider);
		System.out.println(divider);
		System.out.println(movieIdString);
		System.out.println(emptySpace);
		System.out.println(titleString);
		System.out.println(emptySpace);
		System.out.println(movieClassifiedRatingString);
		System.out.println(movieGenreString);
		System.out.println(movieDurationInMinsString);
		System.out.println(movieLanguageString);
		System.out.println(divider);
	}
	
	/**
	 * Format the string to fit the the movie description
	 * @param	details 			The movie to be printed
	 * @param	totalCharacters 			The movie to be printed
	 * @return	 The combined description
	*/
	public static String formatMovieDescriptionString(String details, String totalCharacters)
	{
		int totalLength = totalCharacters.length();
		int detailsLength = details.length();
		
		String filledSpace = "| "+details;
		
		for(int i = detailsLength; i < totalLength-2; i++){
			filledSpace += " ";
		}
		filledSpace += "|";
		return filledSpace;
	}
	
	/**
	 * Generate the length of divided according to movieTitle length
	 * @param	movieTitle 			The movie Title
	 * @return	a String of equal symbol that makes up a divider
	*/
	public static String generateDivider(String movieTitle)
	{
		String divider = "";
		
		for(int i = 0; i < movieTitle.length()+5;i++)
		{
			divider += "=";
		}
		
		return divider;
	}
}
