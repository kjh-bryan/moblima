package entity;

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

