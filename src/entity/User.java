package entity;


/**
 * A base class for Admin or MovieGoer
 * It consists of the username and password and similar attributes
 */
public class User {
	/**
	 * This User ID
	 */
	private int userId;
	/**
	 * This User's username
	 */
	private String username;
	/**
	 * This User's password
	 */
	private String password;
	/**
	 * This User's Role
	 */
	private int role;
	
	/**
	 * Constants for Role, only Admin or Movie Goer at the moment
	 */
	public static final int ADMIN = 1, MOVIE_GOER = 2;
	
	/** 
	 * Create a new User with the given attributes
	 * @param userId				This User's ID
	 * @param username				This User's username
	 * @param password				This User's password
	 * @param role					This User's role
	 */
	public User(int userId, String username, String password,int role)
	{
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	/** 
	 * Create a new User with the given attributes
	 * Used to login the User with just username and password
	 * @param username				This User's username
	 * @param password				This User's password
	 */
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	/** 
	 * Gets the ID of this User
	 * @return this User ID
	 */
	public int getUserId() {
		return userId;
	}


	/** 
	 * Gets the username of this User
	 * @return this User username
	 */
	public String getUsername() {
		return username;
	}


	/** 
	 * Gets the password of this User
	 * @return this User password
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Change the password of the user
	 * @param password 			This user 's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Gets the role of this User
	 * @return this User role
	 */
	public int getRole() {
		return role;
	}

	
	
}
