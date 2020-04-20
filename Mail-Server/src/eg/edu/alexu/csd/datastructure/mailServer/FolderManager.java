package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class FolderManager {
	/**
	 * 
	 * @param path (in this Directory "Users" will be created)
	 */
	public FolderManager() {
	    new File("Users").mkdirs();
	}
	
	
	
	
	public static void addEmail(String email, String password) {
		//right now it makes a new user

		JSONObject user = createUserJSONObject(email, password);
		JSONArray arr = getUsers();
		arr.add(user);
		saveIndex("Users/usersIndex.json", arr.toJSONString());
	}
	
	
	public static JSONObject createUserJSONObject(String email, String password) {
		JSONObject user = new JSONObject();
		user.put("id",new Integer (user.hashCode()) );

		JSONArray emails = new JSONArray();
		emails.add(email);
		
		JSONArray passwords = new JSONArray();
		passwords.add(password);
		
		user.put("emails", emails);
		user.put("passwords", passwords);
		
		return user;
	}
	
	
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
	
	
	public static void clearIndex (String path) {
		saveIndex(path, "[]");
	}
	
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
