package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;





public class FolderManager {

	public FolderManager() {
	    new File("./Users").mkdirs();
	}
		

	public static void createUserSubDirectory(int id)
	{
		String path = "./Users/" + id + "/";
		new File(path).mkdirs();
		new File(path+"inbox/").mkdirs();
		new File(path+"sent/").mkdirs();
		new File(path+"trash/").mkdirs();
		new File(path+"user defined folders/").mkdirs();
	}
	
	
	public static User getUser(String email)
	{
		DoublyLinkedList list = getUsers();
		
		for (int i = 0;i < list.size();i++) {
			User user = (User) list.get(i);
			DoublyLinkedList emails = user.emails;
			
			for (int j = 0;j < emails.size();j++) {
				if (((String)emails.get(j)).equals(email))
					return user;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param contact
	 * @return true if succeeds
	 * Adds a new user to the index
	 */
	public static boolean newUser(User contact) {
		
		String lastEmail = (String) contact.emails.get(contact.emails.size()-1);
		
		if (emailExists(lastEmail))
			return false;
		
		DoublyLinkedList users = getUsers();
		users.add(contact);
		saveObject("userIndex.txt", users);
		return true;
	}
	
	/**
	 * 
	 * @param user
	 * @return true if update succeeded.
	 * 	Looks up the id of the received user. Then sets the existing user to the new user and saves.
	 */
	public static boolean updateUser(User user) 
	{
		//I will check that the last email in contact.emails doesn't belong to any other users
		//Because any conflict would happen due to a new email, which is placed at the end of the list
		String lastEmail = (String) user.emails.get(user.emails.size()-1);
		
		if (emailExists(lastEmail))
			return false;
		
		DoublyLinkedList users = getUsers();
		
		for (int i = 0;i < users.size();i++) {
			User iterator = (User) users.get(i);
			if (iterator.id == user.id) {
				users.set(i, user);
				break;
			}
		}
		saveObject("./userIndex", users);
		return true;
	}
	
	
	/**  
	 * 
	 * "Index file will be loaded in memory in a double linked list data structure." :(
	 * @return a DoublyLinkedList of all the users' JSONobjects
	 * NOTE: returns an instance not a reference. i.e if you change the returned users nothing is saved in the file
	 */
	public static DoublyLinkedList getUsers() {
		DoublyLinkedList usersIndex = (DoublyLinkedList) loadObject("userIndex.txt");
		if (usersIndex == null)
			return new DoublyLinkedList();
		return usersIndex;
	}
	
	public static boolean emailExists(String email) {
		DoublyLinkedList users = getUsers();
		for (int i = 0;i < users.size();i++) {
			User user =  (User) users.get(i);
			
			for (int j = 0;j < user.emails.size();j++) {
				if (((String)user.emails.get(j)).equals(email))
					return true;
			}
		}
		return false;
	}
	


	 
	public static void printUsers() {
		DoublyLinkedList arr = getUsers();
		for (int i = 0;i < arr.size();i++) {
			User user = (User) arr.get(i);
			System.out.println("---------------------------------------");
			System.out.println("Id = " + user.id);
			
			DoublyLinkedList emails = (DoublyLinkedList) user.emails;
			for (int j = 0;j < emails.size();j++)
				System.out.println("Email : " + emails.get(j));
			
			String password =  user.password;
			System.out.println("pass = " + password);
		}
		System.out.println("........................................");
	}
	

	/**
	 * 
	 * @param path
	 * @return the object that is saved in that path (please include ".txt")
	 */
	public static Object loadObject (String path) {
		//TODO handle if file doesn't exist

		Object obj;
		try {
			FileInputStream fi = new FileInputStream(new File(path));
			ObjectInputStream oi = new ObjectInputStream(fi);
	
			obj = oi.readObject();
			
			oi.close();
			fi.close();
			
			return obj;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(path + "is empty");
		}
		return null;
	}
	
	/** 
	 * 
	 * @param path of a file
	 * @param data Object to be saved in the file
	 * 
	 * (Note this function clears the original first. i.e doesn't add to the existing text) 
	 */
	public static void saveObject(String path, Object data) {
		
		//TODO handle if file doesn't exist
		try {
			FileOutputStream f = new FileOutputStream(new File(path), false);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(data);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
