package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





@SuppressWarnings({ "unchecked", "unchecked" })
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
	
	
	public static Boolean isUserExist(JSONArray users, String email)
	{
		for(Object user: users)
		{
			JSONArray emails = (JSONArray)((JSONObject)user).get("emails");
			if(emails.contains(email))
				return true;
		}
		return false;
	}
	
	public static JSONObject createUserJSONObject(User contact) 
	{
		JSONObject user = new JSONObject();
		
		user.put("id", contact.id);
		user.put("firstName", contact.firstName);
		user.put("lastName", contact.lastName);
		JSONArray emails = new JSONArray();
		for (String email : contact.emails)
			emails.add(email);
			
		user.put("emails", emails);
		user.put("password", contact.password);
		
		createUserSubDirectory(contact.id);
		return user;
	}
	
	
	public static void addJSONUser(JSONArray users, JSONObject user)
	{
		users.add(user);
	}
	
	/** 
	 * @return a JSONArray of all the users' JSONobjects
	 */
	public static JSONArray getUsers() {
		JSONParser parser = new JSONParser();
		JSONArray arr;
		try {
			FileReader reader = new FileReader("Users/usersIndex.json");
			arr = (JSONArray) parser.parse(reader);
			if (arr == null)
				clearIndex("Users/usersIndex.json");
			return arr;
             
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 
	public static void printUsers() {
		JSONArray arr = getUsers();
		for (int i = 0;i < arr.size();i++) {
			JSONObject user = (JSONObject) arr.get(i);
			System.out.println("---------------------------------------");
			System.out.println("Id = " + user.get("id"));
			
			JSONArray emails = (JSONArray) user.get("emails");
			JSONArray passwords = (JSONArray) user.get("passwords");

			for (int j = 0;j < emails.size();j++)
				System.out.println("Email : " + emails.get(j) + ", pass : " + passwords.get(j));
		}
		System.out.println("........................................");
	}
	
	/**
	 * 
	 * @param path of the index file required to clear
	 * sets the index file to an empty JSONArray
	 */
	public static void clearIndex (String path) {
		saveIndex(path, "[]");
	}
	
	/**
	 * 
	 * @param path of a file
	 * @param data String to be saved in the file
	 * 
	 * sets the text in the file to data.
	 * (Note this function clears the file first. i.e doesn't add to the existing text) 
	 */
	public static void saveIndex(String path, String data) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(data);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
