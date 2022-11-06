package boundary;

import java.util.ArrayList;

import controller.MovieController;
import controller.UserInputValidationController;
import entity.Movie;
import entity.MovieClassifiedRating;
import global.Constants;

public class SearchListMoviesView {
	
	public static void search_list_movies_view()
	{
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Explore Movies");
			System.out.println("------------------------------");
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
					
					movie = list_all_movies();
					System.out.println();
					if(movie != null)
					{
						goBack = true;
						Movie choosenMovie = ViewMovieDetailView.display_movie_detail(movie);
						if(choosenMovie != null)
						{
							
							MovieShowTimeView.show_times(choosenMovie);
						}
						return;
					}
					
					break;
				case 2:	// Search movie by ID
					movie = search_movies_by_id();

					System.out.println();
					
					if(movie != null)
					{
						goBack = true;
						Movie choosenMovie = ViewMovieDetailView.display_movie_detail(movie);
						if(choosenMovie != null)
						{
							
							MovieShowTimeView.show_times(choosenMovie);
						}
						return;
					}
					break;
				case 3:	// Search movie by Title
					movie = search_movies_by_title();
					System.out.println();
					if(movie != null)
					{
						goBack = true;
						Movie choosenMovie = ViewMovieDetailView.display_movie_detail(movie);
						if(choosenMovie != null)
						{
							
							MovieShowTimeView.show_times(choosenMovie);
						}
						return;
					}
					break;
				case 4:	//List Top 5 Movies
					return;	
				
				case 0:	// Go Back
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
	
	protected static void display_movie_list(ArrayList<Movie> movieList) {

		
		if(!movieList.isEmpty())
		{
			for(Movie movie : movieList)
			{
				printMovieBriefDescription(movie);
			}
		}
	}
	
	private static Movie list_all_movies()
	{

		ArrayList<Movie> movieList = MovieController.getAllMovies();
		if(movieList.isEmpty())
		{
			System.out.println("No movies available");
			return null;
		}
		display_movie_list(movieList);
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------------------");
			System.out.println("MOBLIMA - List All Movies");
			System.out.println("--------------------------------------------");
			System.out.println("Select a Movie to view Movie Details by entering the Movie ID (Enter 0 to Go Back): ");
			System.out.println("--------------------------------------------");
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
	
	public static Movie search_movies_by_title() {
		
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n-------------------------------------------");
			System.out.println("MOBLIMA - Search Movie by Title");
			System.out.println("--------------------------------------------");
			System.out.println("Enter Movie Title to view details (Enter 0 to go back) :");
			System.out.println("--------------------------------------------");
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
	
	public static Movie selectOneMovieFromMovieTitle(ArrayList<Movie> movieList)
	{
		display_movie_list(movieList);
		boolean goBack = false;
		while(!goBack)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Search Movie by Movie Title");
			System.out.println("------------------------------");
			System.out.println("Enter Movie ID to view details (Enter 0 to go back)");
			System.out.println("------------------------------");
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
	
	public static Movie search_movies_by_id()
	{
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Search Movie by Movie ID");
			System.out.println("------------------------------");
			System.out.println("Enter Movie ID to view details");
			System.out.println("or  ");
			System.out.println("Enter 0 to go back");
			System.out.println("------------------------------");
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
		String movieIdString = getMovieDescriptionString("Movie ID: " + movieId,divider);
		String titleString = getMovieDescriptionString(title,divider);
		String movieGenreString = getMovieDescriptionString(movieGenre,divider);
		String emptySpace = getMovieDescriptionString("", divider);
		String movieClassifiedRatingString = getMovieDescriptionString(movieClassifiedRating+"",divider);
		String movieDurationInMinsString = getMovieDescriptionString(movieDurationInMins+"mins",divider);
		String movieLanguageString = getMovieDescriptionString(movieLanguage,divider);
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
	
	public static String getMovieDescriptionString(String details, String totalCharacters)
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
