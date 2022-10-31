package entity;

public class User {
	private int id;
	private String username;
	private String password;
	private int role;
	
	public static final int ADMIN = 1, MOVIE_GOER = 2;
	
	public User(int id, String username, String password,int role)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	

	public int getId() {
		return id;
	}



	public String getUsername() {
		return username;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return role;
	}

	
	
}
