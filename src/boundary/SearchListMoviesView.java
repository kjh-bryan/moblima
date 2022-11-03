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
			System.out.println("MOBLIMA - Search/List Movies");
			System.out.println("------------------------------");
			System.out.println("1. Search by Movie ID");
			System.out.println("2. Search by Movie Title");
	        System.out.println("3. List All Movies");
	        System.out.println("4. Go Back");
			System.out.println();
			System.out.print("Please select an option of choice: ");
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1:	// Search movie by ID
//					search_movies();
					break;
				case 2:	// Search movie by Title
//					search_movies();
					break;
				case 3:	// List all movies
					list_all_movies();
					break;
				case 4:	// Go Back
					return;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					break;
			}
		}
	}
	
	
	private static void list_all_movies()
	{
		ArrayList<Movie> movieList = MovieController.getAllMovies();
		if(!movieList.isEmpty())
		{
			for(Movie movie : movieList)
			{
				printMovieBriefDescription(movie);
			}
		}
		
		boolean goBack = false;
		
		while(!goBack)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - List All Movies");
			System.out.println("------------------------------");
			System.out.println("Select a Movie by entering the Movie ID: ");
			System.out.println("or  ");
			System.out.println("Enter 0 to go back");
			System.out.println("------------------------------");
			System.out.println();
			
			int movieId = UserInputValidationController.validateNumberFromUser();
			
			if(movieId == 0)
			{
				goBack = true;
			}
			else
			{
				
			}
		}
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
