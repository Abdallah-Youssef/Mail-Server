package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;

public class User implements IContact 
{
	int id;
	String firstName;
	String lastName;
	 
	
	DoublyLinkedList emails;
	String password;
	
	public User(String firstName, String lastName, String email, String password) 
	{
		int id;
		try
		{
			File file = new File("lastID.txt");
			Scanner cin = new Scanner(file);
			id = cin.nextInt()+1;
			cin.close();
			this.id = id;
		}catch(Exception e)
		{
			id = 0;
		}
		
		
		try {
			FileWriter writer = new FileWriter("lastID.txt");
			writer.write(id);
			writer.close();
		} catch (IOException e) {
		}
		
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emails = new DoublyLinkedList();
		this.emails.add(email);
		this.password = password;
	}
	
}
