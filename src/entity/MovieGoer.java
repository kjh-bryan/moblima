package entity;


/**
 * A MovieGoer who utilized the system to View Movies/Cineplex/Cinemas
 * or Booking a ticket for a Movie
 * Usually has restricted access, a guest must register as a MovieGoer
 * to be able to book a ticket.
 */
public class MovieGoer extends User{
	 /**
	 * This MovieGoer's Name
	 */
	String name;
	 /**
	 * This MovieGoer's email address
	 */	
	String emailAddress;
	 /**
	 * This MovieGoer's phone
	 */
	int phone;
	 /**
	 * This MovieGoer's age
	 */
	int age;
	
	/** 
	 * Create a new MovieGoer with the username, password and its details.
	 * @param userId					This MovieGoer's ID 
	 * @param username					This MovieGoer's username (Credential to login)
	 * @param password					This MovieGoer's password (Credential to login)
	 * @param name						This MovieGoer's name
	 * @param phone						This MovieGoer's phone
	 * @param emailAddress				This MovieGoer's email address
	 * @param age						This MovieGoer's age
	 */
	public MovieGoer(int userId, String username, String password, String name, int phone, String emailAddress, int age)
	{
		super(userId, username, password, MOVIE_GOER);
		this.name = name;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.age = age;
	}
	
	/** 
	 * Create a new MovieGoer with the username, password
	 * This constructor is basically used to check for login credential
	 * @param username					This MovieGoer's username (Credential to login)
	 * @param password					This MovieGoer's password (Credential to login)
	 */
	public MovieGoer(String username,String password)
	{
		super(username,password);
	}

	/** 
	 * Gets the name of this MovieGoer
	 * @return this MovieGoer's Name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Gets the phone of this MovieGoer
	 * @return this MovieGoer's phone
	 */
	public int getPhone() {
		return phone;
	}
	
	/** 
	 * Gets the email address of this MovieGoer
	 * @return this MovieGoer's email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
		

	/** 
	 * Gets the age of this MovieGoer
	 * @return this MovieGoer's age
	 */
	public int getAge()
	{
		return age;
	}
	
	
	
}
