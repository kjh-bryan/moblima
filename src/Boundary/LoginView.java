package Boundary;

import Controller.AdminController;
import Controller.MovieGoerController;
import Controller.UserInputValidationController;
import Global.Constants;
import Model.Admin;
import Model.MovieGoer;
import Model.User;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LoginView {
	
	private int role;
	

	String roleName;
	
	public LoginView(int role)
	{
		this.role = role;
		roleName = (role == 0) ? "Admin" : "MovieGoer";
	}
	
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
			System.out.println("3: Exit");
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
					else
					{
						System.out.println("Incorrect credentials, please try again");
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
				case 3:
					System.out.println("Exiting Login UI");
					return null;
				default:
					System.out.println(Constants.INCORRECT_OPTION);
					
			}
		}
		return null;
	}
	
	
	public User loginUser()
	{
		System.out.print("Enter your username: ");
		String username = UserInputValidationController.validateStringFormUser();
		System.out.println();
		System.out.print("Enter your password: ");
		String password = UserInputValidationController.validateStringFormUser();
		System.out.println();
		
		
		if(role == 0) {
			try {
				Admin adminUser = AdminController.loginAdminAccount(new Admin(username, password));
				if(adminUser != null)
				{
					return adminUser;
				}
			}
			catch(Exception e)
			{
				System.out.println("LoginUI : Exception occured : " + e.getLocalizedMessage());
			}
		}
		else
		{
			try {
				MovieGoer movieGoerUser = MovieGoerController.loginMovieGoerAccount(new MovieGoer(username, password));
				if(movieGoerUser != null)
				{
					return movieGoerUser;
				}
			}
			catch(Exception e)
			{
				System.out.println("LoginUI : Exception occured : " + e.getLocalizedMessage());
			}
		}
		
		return null;
		
	}
	
	
}
