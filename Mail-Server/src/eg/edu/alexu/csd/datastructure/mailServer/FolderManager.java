package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
	
	public static JSONObject getUser(String email)
	{
		DoublyLinkedList list = getUsers();
		
		for (int i = 0;i < list.size();i++) {
			JSONObject user = (JSONObject) list.get(i);
			JSONArray emails = (JSONArray)(user).get("emails");
			if(emails.contains(email)) {
				JSONObject Found=(JSONObject) user;
				return Found;
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
		
		JSONObject user = new JSONObject();
		user.put("id", contact.id);
		user.put("firstName", contact.firstName);
		user.put("lastName", contact.lastName);
		JSONArray emails = new JSONArray();
		
		for(int j = 0;j < contact.emails.size();j++) 
			emails.add(contact.emails.get(j));
		
		user.put("emails", emails);
		user.put("password", contact.password);
		
		DoublyLinkedList Data = getUsers();
		Data.add(user);
		createUserSubDirectory(contact.id);
		try (FileWriter file = new FileWriter("Users/usersIndex.json")) {
			//write doesn't add, it sets whatever is in the file to "Data.toJSONString"
			JSONArray arr = listToJSONArray(Data);
            file.write(arr.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
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
			JSONObject object = (JSONObject) users.get(i);
			if ((int)object.get("id") == user.id) {
				users.set(i, user);
				break;
			}
		}
		
		try (FileWriter file = new FileWriter("Users/usersIndex.json")) {
			//write doesn't add, it sets whatever is in the file to "Data.toJSONString"
			JSONArray arr = listToJSONArray(users);
            file.write(arr.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	
	/**  
	 * 
	 * "Index file will be loaded in memory in a double linked list data structure." :(
	 * @return a DoublyLinkedList of all the users' JSONobjects
	 * NOTE: returns an instance not a reference. i.e if you change the returned users nothing is saved in the file
	 */
	public static DoublyLinkedList getUsers() {
		JSONParser parser = new JSONParser();
		JSONArray arr;
		try {
			FileReader reader = new FileReader("Users/usersIndex.json");
			arr = (JSONArray) parser.parse(reader);
			if (arr == null)
				clearJSONFile("Users/usersIndex.json");
			
			DoublyLinkedList users = new DoublyLinkedList();
			for (int i = 0;i < arr.size();i++) {
				users.add(arr.get(i));
			}
			return users;
             
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean emailExists(String email) {
		DoublyLinkedList users = getUsers();
		for (int i = 0;i < users.size();i++) {
			JSONObject user = (JSONObject) users.get(i);
			
			JSONArray emails = (JSONArray)(user.get("emails"));
			for (Object e : emails) {
				if (((String)e).equals(email))
					return true;
			}
		}
		return false;
	}
	
	public static JSONObject UserToJSONObject (User user) {
		JSONObject object  = new JSONObject();
		
		object = new JSONObject();
		object.put("id", user.id);
		object.put("firstName", user.firstName);
		object.put("lastName", user.lastName);
		JSONArray emails = new JSONArray();
		
		for(int j = 0;j < user.emails.size();j++) 
			emails.add(user.emails.get(j));
		
		object.put("emails", emails);
		object.put("password", user.password);
		
		return object;
	}
	
	public static DoublyLinkedList JSONArrayToList(JSONArray arr) {
		DoublyLinkedList list = new DoublyLinkedList();
		for (int i = 0;i < arr.size();i++) {
			list.add(arr.get(i));
		}
		return list;
	}
	
	public static JSONArray listToJSONArray(DoublyLinkedList list) {
		JSONArray arr = new JSONArray();
		for (int i = 0;i < list.size();i++) {
			arr.add(list.get(i));
		}
		return arr;
	}
	 
	public static void printUsers() {
		DoublyLinkedList arr = getUsers();
		for (int i = 0;i < arr.size();i++) {
			JSONObject user = (JSONObject) arr.get(i);
			System.out.println("---------------------------------------");
			System.out.println("Id = " + user.get("id"));
			
			JSONArray emails = (JSONArray) user.get("emails");
			String password =  (String) user.get("password");
			

			for (int j = 0;j < emails.size()&&emails.get(j)!=null;j++)
				System.out.println("Email : " + emails.get(j));
			
			System.out.println("pass = " + password);
		}
		System.out.println("........................................");
	}
	
	/**
	 * 
	 * @param path of the index file required to clear
	 * sets the index file to an empty JSONArray
	 */
	public static void clearJSONFile (String path) {
		saveToFile(path, "[]");
	}
	
	/** 
	 * 
	 * @param path of a file
	 * @param data String to be saved in the file
	 * 
	 * sets the text in the file to data.
	 * (Note this function clears the file first. i.e doesn't add to the existing text) 
	 */
	public static void saveToFile(String path, String data) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(data);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
