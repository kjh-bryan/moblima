package boundary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import controller.AdminController;
import controller.IDgeneratorController;
import controller.MovieGoerController;
import controller.UserInputValidationController;
import controller.saleController;
import controller.ticketsaleController;
import entity.Admin;
import entity.MovieGoer;

public class RegisterView {
	 
	private int role;
	String roleName;
	int id=-1;
	public RegisterView(int role)
	{
		this.role = role;
		roleName = (role == 0) ? "Admin" : "MovieGoer";
	}
	
	
	public boolean showRegisterView() 
	{
		ticketsaleController.calculatesale(2);
		System.out.println("\n------------------------------");
		System.out.println("MOBLIMA - Register as " + roleName);
		System.out.println("------------------------------");
         
       
        
       
	    if (role==0) {
		id = IDgeneratorController.idgeneration();}
	    if (role==1) {
	    id = IDgeneratorController.idgeneration2();}
        
        
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
