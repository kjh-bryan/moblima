package Boundary;

import java.util.Random;

import Controller.AdminController;
import Controller.MovieGoerController;
import Controller.UserInputValidationController;
import Model.Admin;
import Model.MovieGoer;

public class RegisterView {

	private int role;
	String roleName;
	
	public RegisterView(int role)
	{
		this.role = role;
		roleName = (role == 0) ? "Admin" : "MovieGoer";
	}
	
	public boolean showRegisterView()
	{
		System.out.println("\n------------------------------");
		System.out.println("MOBLIMA - Register as " + roleName);
		System.out.println("------------------------------");

		Random random = new Random();
		int id = random.nextInt(1000);
		
		System.out.print("Please enter your username: ");
		String username = UserInputValidationController.validateStringFormUser();
		System.out.println();
		System.out.print("Please enter your password: ");
		String password = UserInputValidationController.validateStringFormUser();
		System.out.println();
		String name;
		int phone;
		boolean registerSuccessful;
		
		if(role == 1)
		{
			System.out.print("Please enter your Full name: ");
			name = UserInputValidationController.validateStringFormUser();
			System.out.println();
			System.out.print("Please enter your phone number: ");
			phone = UserInputValidationController.validatePhoneNumberFromUser();
			System.out.println();
			
			registerSuccessful =  MovieGoerController.createMovieGoerAccount(new MovieGoer(id,username,password,name,phone));
		}
		else
		{
			registerSuccessful = AdminController.createAdminAccount(new Admin(id, username, password));
		}
		
		return registerSuccessful;
	}
}
