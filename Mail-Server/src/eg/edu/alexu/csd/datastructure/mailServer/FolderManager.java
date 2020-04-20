package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class FolderManager {

	public FolderManager() {
	    new File("Users").mkdirs();
	}
	
	
	
	//TODO make it receive a user to add the email to, or to a new user if passed a null user
	
	public static void addEmail(String email, String password) {
		//right now it makes a new user and adds this email and password to it
		JSONObject user = createUserJSONObject(email, password);
		JSONArray arr = getUsers();
		arr.add(user);
		saveIndex("Users/usersIndex.json", arr.toJSONString());
	}
	
	
	public static JSONObject createUserJSONObject(String email, String password) {
		JSONObject user = new JSONObject();
		
		//hashcode is always zero, doesn't work :(
		//TODO id for the json objects
		user.put("id","Place Holder Id" );

		JSONArray emails = new JSONArray();
		emails.add(email);
		
		JSONArray passwords = new JSONArray();
		passwords.add(password);
		
		user.put("emails", emails);
		user.put("passwords", passwords);
		
		return user;
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
