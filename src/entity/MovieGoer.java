package entity;

public class MovieGoer extends User{
	
	String name;
	String emailAddress;
	int phone;
	int age;
	
	public MovieGoer(int id, String username, String password, String name, int phone, String emailAddress, int age)
	{
		super(id, username, password, MOVIE_GOER);
		this.name = name;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.age = age;
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

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public int getAge()
	{
		return age;
	}
	
	
	
}
