package controller;

import java.io.IOException;

import entity.Idgenerator;

public class IDgeneratorController {
	static int id; 
	public static int idgeneration () {
		
	  Idgenerator admin = new Idgenerator();
	  
	try {
		id = admin.generate(0);
		
	} catch (IOException e) {
		e.printStackTrace();
	} 
	return id;
	
	}
	public static int idgeneration2 () {
		
		
		  Idgenerator moviegoer = new Idgenerator();
		try {
		
			id = moviegoer.generate(1); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return id;
		
		}
}
	