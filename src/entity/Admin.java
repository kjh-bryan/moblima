package entity;

/**
 * An Admin or Cinema Staff which has access to the system
 * Have the rights to Create/Update/Delete movie listing, showtimes or configure system settings
 */


public class Admin extends User{

	/** 
	 * Constructor for Admin with the given attributes
	 * @param userId						This Admin's unique ID 
	 * @param username						This Admin's username 
	 * @param password						This Admin's password 
	 */
	public Admin(int userId, String username, String password) {
		super(userId, username, password, ADMIN);
	}
	
	/** 
	 * Constructor for Admin with the given attributes
	 * @param username						This Admin's username 
	 * @param password						This Admin's password 
	 */
	
	public Admin(String username, String password)
	{
		super(username,password);
	}
	
}
