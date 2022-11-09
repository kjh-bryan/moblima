package entity;


/**
 * Enum MovieShowingStatus to represent the Type of the Movie
 * 2D is the standard while 3D is the elevated experience and more expensive
 */


public enum MovieType {
	TWOD("2D Movies"),
	THREED("3D Movies");
	
	private String movieType;
	
	private MovieType(String movieType)
	{
		this.movieType = movieType;
	}
	
	public String getMovieType()
	{
		return movieType;
	}
	
}

