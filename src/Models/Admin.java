package Models;
import java.io.*;
import java.util.*;
/**
 * Admin model 
 * @author Anon
 *
 */
public class Admin {
	/**
	 * ID of the admin
	 * Password of the admin account
	 * Name of the admin
	 */
	private String adminID;
	private String adminPass;
	private String adminName;
	
	/**
	 * Constructor of Admin class
	 * @param id ID of the Admin
	 * @param pass Password designated to the admim
	 * @param name Name of the admin
	 */
	public Admin(String id, String pass, String name){
		this.adminID = id;
		this.adminPass = pass;
		this.adminName = name;
	}
	
	/**
	 * Accessor of AdminID
	 * @return Returns the ID of the admin
	 */
	public String getAdminId(){
		return this.adminID;
	}
	
	/**
	 * Mutator of AdminID
	 * @param S ID to be set for the admin
	 */
	public void setAdminId(String S){
		this.adminID = S;
	}
	
	/**
	 * Accessor of AdminPass
	 * @return Returns admin password
	 */
	public String getAdminPass(){
		return this.adminPass;
	}
	/**
	 * Mutator of AdminPass
	 * @param S Password to be set
	 */
	public void setAdminPass(String S){
		this.adminPass = S;
	}
	/**
	 * Accessor of AdminName
	 * @return Returns the admin name
	 */
	public String getAdminName(){
		return this.adminName;
	}
	/**
	 * Mutator of adminName
	 * @param S Name to be set
	 */
	public void setAdminName(String S){
		this.adminName = S;
	}
	
	
	

}