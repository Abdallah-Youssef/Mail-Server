package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataStructures.DoubleLinkedList;






public class FolderManagerBIN {

	public FolderManagerBIN() {
	    
	}
		
	public static void initProgramDirectories()
	{
		new File("./Users").mkdirs();
	}
	
	public static User getUser(int id)
	{
		DoubleLinkedList users = getUsers();
		for(int i = 0; i < users.size();i++)
		{
			User user = (User)users.get(i);
			if (user.getID() == id)
				return user;
		}
		return null;
	}
	
	public static User getUser(String email)
	{
		DoubleLinkedList users = getUsers();
		for(int i = 0; i < users.size();i++)
		{
			User user = (User)users.get(i);
			for (int j = 0;j < user.emails.size();j++) {
				String userEmail = (String) user.emails.get(j);
				if (userEmail.equals(email))
					return user;
			}
		}
		return null;
	}
	
	public static void WriteObjectToFile(Object serObj, String path) {
		
		//System.out.println(serObj.getClass().toString());
		
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	public static Object ReadObjectFromFile(String path)
	{
		Object s = null;
		try
		{
			FileInputStream fstream = new FileInputStream(path);
			ObjectInputStream objectIn = new ObjectInputStream(fstream);
			
			s = (Object)objectIn.readObject();
			fstream.close();
			objectIn.close();
		}catch(Exception e)
		{
			new File(path);
		}
		return s;
	}
	
		
	
	public static void saveUsersLinkedList(DoubleLinkedList users)
	{
		WriteObjectToFile(users, "./Users/usersIndex.txt");
	}
	
	/**  
	 * @return a DoublyLinkedList of all the users User objects
	 */
	public static DoubleLinkedList getUsers() 
	{
		DoubleLinkedList d = (DoubleLinkedList)ReadObjectFromFile("./Users/usersIndex.txt");
		if(d == null)
			return new DoubleLinkedList();
		return d;
	}
	
	public static void addNewUser(User newUser) {
		DoubleLinkedList users = getUsers();
		
		//TODO compare every email in the new user with all the emails of the other users
		for (int i = 0;i < users.size();i++) {
			User user = (User) users.get(i);
			for (int j = 0;j < user.emails.size();j++) {
				if (((String)newUser.emails.get(0)).equals((String)user.emails.get(j))){
					return;
				}
			}
		}
		
		users.add(newUser);
		saveUsersLinkedList(users);
	}
	
	/**
	 * 
	 * @param updatedUser
	 * the user has to exist
	 */
	public static void updateUser (User updatedUser) {
		DoubleLinkedList users = getUsers();
		for (int i = 0;i < users.size();i++) {
			User user = (User) users.get(i);
			if (updatedUser.getID() == user.getID()) {
				users.set(i, updatedUser);
				saveUsersLinkedList(users);
				return;
			}
		}
		
	}
	public static void printUsers() {
		DoubleLinkedList arr = getUsers();
		
		for (int i = 0;i < arr.size();i++) {
			User user = (User) arr.get(i);
			System.out.println("---------------------------------------");
			System.out.println("Id = " + user.id);
			
			DoubleLinkedList emails = user.emails;
			String password = user.password;

			for (int j = 0;j < emails.size() && emails.get(j) != null;j++)
				System.out.println("Email : " + emails.get(j) + ", pass : " + password);
		}
		System.out.println("........................................");
	}
	
	public static void clearUsers() {
		//delete directories
		deleteDirectory(new File("Users"));
		new File("./Users").mkdirs();
		//TODO reset lastID
	}
	
	static boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	

	
	/*public static void main(String[] args) {
		FolderManagerBIN f = new FolderManagerBIN();
		f.initProgramDirectories();
		DoublyLinkedList users = getUsers();
		users.add(new User("ahmed", "Bahgat", "ahmedelsherif@gmal.com", "fsfsdfds"));
		saveUsersLinkedList(users);
		
		System.out.println(((User)users.get(0)).emails[0]);
	}*/
}
