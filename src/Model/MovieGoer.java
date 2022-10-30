package Model;

public class MovieGoer extends User{
	
	String name;
	int phone;
	
	public MovieGoer(int id, String username, String password, String name, int phone)
	{
		super(id, username, password, MOVIE_GOER);
	}
	
	public MovieGoer(String username,String password)
	{
		super(username,password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	
}
