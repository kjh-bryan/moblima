package entity;

public class Cast {
	private int castId;
	private String castName;
	private int movieId;
	
	public Cast(int castId, String castName, int movieId) {
		this.castId = castId;
		this.castName = castName;
		this.movieId = movieId;
	}
	
	public Cast(String castName)
	{
		this.castName = castName;
	}
	
	
	public void setCastId(int castId) {
		this.castId = castId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getCastId() {
		return castId;
	}

	public String getCastName() {
		return castName;
	}

	public int getMovieId() {
		return movieId;
	}
	
	
}
