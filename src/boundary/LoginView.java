package boundary;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import controller.AdminController;
import controller.MovieGoerController;
import controller.UserInputValidationController;
import entity.Admin;
import entity.MovieGoer;
import entity.User;
import global.Constants;
import global.UserSession;

/**
 * This class represents the view for User
 * to Login, which is used when authenticated
 * an Admin or a MovieGoer
*/
public class LoginView {
	/**
	 * The role of the User currently logging in
	*/
	private int role;
	
	/**
	 * The role name of the User currently loggin in
	*/
	String roleName;
	
	/**
	 * Create the LoginView with the role
	 * @param role					User's role
	*/
	public LoginView(int role)
	{
		UserSession.admin = null;
		UserSession.movieGoer = null;
		this.role = role;
		roleName = (role == 0) ? "Admin" : "MovieGoer";
	}
	
	
	/**
	 * Shows the view for the User to login or register
	 * @return The user object if successfully login
	*/
	public User showLoginView()
	{
		boolean selectedExit = false;
		
		while(!selectedExit)
		{
			System.out.println("\n------------------------------");
			System.out.println("MOBLIMA - Login as " + roleName);
			System.out.println("------------------------------");
			System.out.println("1: Enter Login Credentials");
			System.out.println("2: Register a new account");
			System.out.println("0: Go Back");
			System.out.println();
			
			switch(UserInputValidationController.validateNumberFromUser())
			{
				case 1: 
					// Get User Login Credentials
					User user = loginUser();
					if(user != null)
					{
						return user;
					}
					break;
					
				case 2:
					// Register Account UI
					RegisterView registerView = new RegisterView(role);
					if(registerView.showRegisterView())
					{
						System.out.println("Registration successful");
					}
					else
					{
						System.out.println("Registartion unsuccessful, please try again.");
					}
					
					break;
				case 0:
					
					return null;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					
			}
		}
		return null;
	}
	
	/**
	 * Shows the view for the User to login
	 * @return The user object if successfully login
	*/
	public User loginUser()
	{
		System.out.print("Enter your username: ");
		String username = UserInputValidationController.validateStringFromUser();
		System.out.println();
		System.out.print("Enter your password: ");
		String password = UserInputValidationController.validateStringFromUser();
		System.out.println();
		
		
		if(role == 0) {
			try {
				Admin adminUser = AdminController.loginAdminAccount(new Admin(username, password));
				if(adminUser != null)
				{
					if(adminUser.getPassword().equals(password))
					{
						return adminUser;
					}
					else
					{
						System.out.println("Incorrect password provided!");
						return null;
					}
				}
				else
				{
					System.out.println("Username does not exist!");
				}
			}
			catch(Exception e)
			{
				System.out.println("LoginView : Exception occured : " + e.getLocalizedMessage());
			}
		}
		else
		{
			try {
				MovieGoer movieGoerUser = MovieGoerController.loginMovieGoerAccount(new MovieGoer(username, password));
				if(movieGoerUser != null)
				{
					if(movieGoerUser.getPassword().equals(password))
					{
						return movieGoerUser;
					}
					else
					{
						System.out.println("Incorrect password provided!");
						return null;
					}
				}
				else
				{
					System.out.println("Username does not exist!");
				}
			}
			catch(Exception e)
			{
				System.out.println("LoginView : Exception occured : " + e.getLocalizedMessage());
			}
		}
		
		return null;
		
	}
	
	
}
