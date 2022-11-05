package boundary;

import java.util.Random;

import controller.AdminController;
import controller.MovieGoerController;
import controller.UserInputValidationController;
import entity.Admin;
import entity.MovieGoer;

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
		String username = UserInputValidationController.validateStringFromUser();
		System.out.println();
		System.out.print("Please enter your password: ");
		String password = UserInputValidationController.validateStringFromUser();
		System.out.println();
		String name;
		String email;
		int phone;
		int age;
		boolean registerSuccessful;
		
		if(role == 1)
		{
			System.out.print("Please enter your Full name: ");
			name = UserInputValidationController.validateStringFromUser();
			System.out.println();
			System.out.print("Please enter your phone number: ");
			phone = UserInputValidationController.validatePhoneNumberFromUser();
			System.out.println();
			System.out.print("Please enter your email address: ");
			//Todo implement email validator
			email = UserInputValidationController.validateStringFromUser();

			System.out.print("Please enter your age: ");
			age = UserInputValidationController.validateNumberFromUser();
			System.out.println();
			
			registerSuccessful =  MovieGoerController.createMovieGoerAccount(new MovieGoer(id,username,password,name,phone,email,age));
		}
		else
		{
			registerSuccessful = AdminController.createAdminAccount(new Admin(id, username, password));
		}
		
		return registerSuccessful;
	}
}
