package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import Entity.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileWriter;

public class AdminController{
	private static final String SEPARATOR = "|";
	private static final String databaseTableName = "src/database/admin.txt";
	
	private final static Logger logger = Logger.getLogger(AdminController.class.getName());
	
	
	public static boolean createAdminAccount(Admin newAdminAccount)
	{
		
		try {
			UserInputValidationController.createDatabaseTableFile(databaseTableName);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(databaseTableName,true));
			
			Admin checkExistingAdminAccount = loginAdminAccount(newAdminAccount);
			if(checkExistingAdminAccount != null)
			{
				System.out.println("Username already exist, please try a new username");
				System.out.println();
				out.close();
				return false;
			}
			out.append(newAdminAccount.getId() + SEPARATOR + newAdminAccount.getUsername() + SEPARATOR + newAdminAccount.getPassword() + "\n");
			
			
			
			out.close();
			
			return true;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "createAdminAccount exception occured : " + e.getLocalizedMessage());
		}
		
		return false;
	}

	
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

			logger.log(Level.SEVERE, "loginAdminAccount exception occured : " + e.getLocalizedMessage());
			
		}
		return null;
		
	}
	
	private static ArrayList<Admin> getAdminAccountList() throws IOException
	{
		ArrayList<Admin> adminAccountList = new ArrayList<Admin>();
		Scanner sc = new Scanner(new FileInputStream(databaseTableName));
		try {
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
			logger.log(Level.SEVERE, "getAdminAccountList() exception occured : " + e.getLocalizedMessage());
		}
		finally {
			sc.close();
		}
		
		return adminAccountList;
	}
}
