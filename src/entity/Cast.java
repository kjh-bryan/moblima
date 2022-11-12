package entity;

/**
 * A Cast represents the actor/actress which belongs to a Movie
 * a cast contains an integer ID, it's name and the corresponding Movie it belongs to. A Movie can have many Cast
 */

public class Cast {
	 /**
	 * This Cast's ID
	 */
	private int castId;
	/**
	 * This Cast's Name
	 */
	private String castName;
	/**
	 * The Movie ID this Cast belongs to
	 */
	private int movieId;
	
	/** 
	 * Create a new Cast with the given name and movie it belongs to
	 * @param castId					This Cast's ID 
	 * @param castName					This Cast's name
	 * @param movieId						The Movie which this Cast belonged to
	 */
	public Cast(int castId, String castName, int movieId) {
		this.castId = castId;
		this.castName = castName;
		this.movieId = movieId;
	}
	
	/** 
	 * Create a new Cast with the given name
	 * @param castName		This Cast's name
	 */
	public Cast(String castName)
	{
		this.castName = castName;
	}
	
	/** 
	 * Gets the ID of this Cast
	 * @return this Cast's ID
	 */
	public int getCastId() {
		return castId;
	}

	/** 
	 * Gets the name of this Cast
	 * @return this Cast's name
	 */
	public String getCastName() {
		return castName;
	}

	/** 
	 * Gets Movie which this Cast belongs to
	 * @return this Cast's Movie
	 */
	public int getMovieId() {
		return movieId;
	}
	
	/** 
	 * Set the Movie of which this Cast belongs to
	 * @param movieId  		The Movie which this Cast belonged to
	 */
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
}
