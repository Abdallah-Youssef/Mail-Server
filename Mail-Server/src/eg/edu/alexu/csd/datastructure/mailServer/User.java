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
	public String firstName;
	String lastName;
	
	
	DoubleLinkedList emails;
	DoubleLinkedList contactsIDs;
	public String password;
	
	public static void createUserSubDirectory(int id)
	{
		//String path = Paths.get("").toAbsolutePath().toString() + "\\Users\\" + id + "\\";
		try
		{
		String path = "./Users/" + id + "/";
		new File(path).mkdirs();
		new File(path+"inbox/").mkdirs();
		new File(path+"inbox/index.txt").createNewFile();
		new File(path+"sent/").mkdirs();
		new File(path+"sent/index.txt").createNewFile();
		new File(path+"trash/").mkdirs();
		new File(path+"trash/index.txt").createNewFile();
		new File(path+"user defined folders/").mkdirs();
		new File(path+"user defined folders/index.txt").createNewFile();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private int calculateNewUserID()
	{
		try
		{
			File file = new File("./Users/lastID.txt");
			Scanner cin = new Scanner(file);
			id = cin.nextInt()+1;
			System.out.println("hello - " + id);
			cin.close();
		}catch(Exception e)
		{
			System.out.println("hello");
			id = 1;
			new File("./Users/lastID.txt");
		}
		
		try {
			FileWriter writer = new FileWriter("./Users/lastID.txt");
			writer.write(String.valueOf(id));
			writer.close();
		} catch (IOException e) {
			
		}
		return id;
	}
	
	public User(String firstName, String lastName, String email, String password)
	{		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emails = new DoubleLinkedList();
		this.emails.add(email);
		this.password = password;
		this.contactsIDs = new DoubleLinkedList();
		int id = calculateNewUserID();
	}
	
	public void saveToFileSystem()
	{
		this.id = calculateNewUserID();
		createUserSubDirectory(id);
		FolderManagerBIN.addNewUser(this);
	}
	
	
	public int getID() {
		return id;
	}
	
	public DoubleLinkedList getContactsIDs() {
		return contactsIDs;
	}
	
	public DoubleLinkedList getEmails() {
		return emails;
	}
	public void addEmail(String Email) {
		emails.add(Email);
		FolderManagerBIN.updateUser(this);
	}
	
	public void removeEmail(String Email) {
		for (int i = 0;i < emails.size();i++) {
			if (((String)emails.get(i)).equals(Email)) {
				emails.remove(i);
				FolderManagerBIN.updateUser(this);
				return;
			}
		}
	}
	
	public void addContactID(int id) {
		contactsIDs.add(id);
		FolderManagerBIN.updateUser(this);
	}
	public void printEmails() {
		for (int i = 0;i < emails.size();i++) {
			System.out.print((String)emails.get(i) + " ");
		}
		System.out.println();
	}
}
