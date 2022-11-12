package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileWriter;

/**
 * This class represents the Controller for Admin
 * It handles all database functions related to Admin database file
 */

public class AdminController{
	/**
	 * Separator used as String Token to separate data in text file
	 */
	private static final String SEPARATOR = "|";
	
	/**
	 * Database Filename which stores Admin's information
	 */
	
	private static final String DATABASE_FILENAME = "src/database/admin.txt";
	
	/**
	 * Logger for debugging purposes
	 */
	private static final  Logger LOGGER = Logger.getLogger(AdminController.class.getName());
	
	/**
	 * CREATE a new Admin account, adding into the database file with separator |
	 * e.g. ID|username|password
	 * Check if an already existing username has exist, 
	 * if have return false
	 * else the Admin account will be created, returning true
	 * @param newAdminAccount 		New admin account to be created
	 * @return True if Admin account is successfully created, else false
	 */
	public static boolean createAdminAccount(Admin newAdminAccount)
	{
		
		try {
			UserInputValidationController.createDatabaseFileName(DATABASE_FILENAME);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(DATABASE_FILENAME,true));
			
			Admin checkExistingAdminAccount = loginAdminAccount(newAdminAccount);
			if(checkExistingAdminAccount != null)
			{
				System.out.println("Username already exist, please try a new username");
				System.out.println();
				out.close();
				return false;
			}
			int generateId = DatabaseController.generateIntegerId(DATABASE_FILENAME);
			out.append(generateId+ SEPARATOR + newAdminAccount.getUsername() + SEPARATOR + newAdminAccount.getPassword() + "\n");
			
			
			
			out.close();
			
			return true;
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "createAdminAccount exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}

	/**
	 * READ the username of the Admin account in the arraylist of getAdminAccountList()
	 * return null if no such username was found, 
	 * used by createAdminAccount() to test for existing username
	 * @param adminAccount 			Admin account contains (username and password)
	 * @return the matched the Admin account and returns the Admin object
	 */
	public static Admin loginAdminAccount(Admin adminAccount) 
	{
		try {
			
		ArrayList<Admin> adminAccountList = getAdminAccountList();
		for(Admin a : adminAccountList)
		{
			if(a.getUsername().equals(adminAccount.getUsername()))
			{
				return a;
			}
		}

		}
		catch(Exception e)
		{

			LOGGER.log(Level.SEVERE, "loginAdminAccount exception occured : " + e.getLocalizedMessage());
			
		}
		return null;
		
	}
	
	
	/**
	 * READ all the username and password in the database
	 * Store into an array list
	 * return empty array list if no account exist
	 * used by loginAdminAccount to iterate through the list of accounts
	 * @return  an arraylist of all Admin accounts
	 */
	private static ArrayList<Admin> getAdminAccountList() 
	{
		ArrayList<Admin> adminAccountList = new ArrayList<Admin>();
		Scanner sc = null;
		try {
		sc = new Scanner(new FileInputStream(DATABASE_FILENAME));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer stringTokenizer = new StringTokenizer(line, SEPARATOR);
				int id = Integer.parseInt(stringTokenizer.nextToken().trim());
				String username = stringTokenizer.nextToken().trim();
				String password = stringTokenizer.nextToken().trim();
				adminAccountList.add(new Admin(id, username, password));
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, "getAdminAccountList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			if(sc!=null)
				sc.close();
		}
		
		return adminAccountList;
	}
}
