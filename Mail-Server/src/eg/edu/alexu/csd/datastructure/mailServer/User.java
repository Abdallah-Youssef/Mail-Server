package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import interfaces.IContact;

public class User implements IContact, Serializable
{
	int id;
	String firstName;
	String lastName;
	 
	
	String[] emails;
	public String password;
	
	public static void createUserSubDirectory(int id)
	{
		
		//String path = Paths.get("").toAbsolutePath().toString() + "\\Users\\" + id + "\\";
		String path = "./Users/" + id + "/";
		new File(path).mkdirs();
		new File(path+"inbox/").mkdirs();
		new File(path+"sent/").mkdirs();
		new File(path+"trash/").mkdirs();
		new File(path+"user defined folders/").mkdirs();
	}
	
	
	public User(String firstName, String lastName, String email, String password) 
	{
		int id;
		try
		{
			File file = new File("./Users/lastID.txt");
			Scanner cin = new Scanner(file);
			id = cin.nextInt()+1;
			cin.close();
			this.id = id;
		}catch(Exception e)
		{
			id = 1;
			try {
				new File("./Users/lastID.txt").createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//System.out.println(id);
		createUserSubDirectory(id);
		
		try {
			FileWriter writer = new FileWriter("./Users/lastID.txt");
			writer.write(String.valueOf(id));
			writer.close();
		} catch (IOException e) {
		}
		
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emails = new String[10];
		this.emails[0] = email;
		this.password = password;
	}
	
}
