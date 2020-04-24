package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class FolderManagerBIN {

	public FolderManagerBIN() {
	    
	}
		
	public void initProgramDirectories()
	{
		new File("./Users").mkdirs();
	}
	
	
	public static User userExists(DoubleLinkedList users, String email)
	{
		for(int i = 0; i < users.size();i++)
		{
			User user = (User)users.get(i);
			for(int j = 0; j < 10;j++)
			{
				String userEmail = user.emails[j];
				if(userEmail == null)
					return null;
				if(email.equals(userEmail))
					return user;
			}
		}
		return null;
	}
	
	public static void WriteObjectToFile(Object serObj, String path) {
		 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	public static DoubleLinkedList ReadObjectFromFile(String path)
	{
		DoubleLinkedList s = null;
		try
		{
			FileInputStream fstream = new FileInputStream(path);
			ObjectInputStream objectIn = new ObjectInputStream(fstream);
			
			s = (DoubleLinkedList)objectIn.readObject();
			fstream.close();
			objectIn.close();
		}catch(Exception e)
		{
			try {
				new File(path).createNewFile();
				s = new DoubleLinkedList();
			} catch (IOException e1) {
				System.out.println(e1.toString());
			}
		}
		return s;
	}
	
		
	
	public static void saveUsersLinkedList(DoubleLinkedList users)
	{
		WriteObjectToFile(users, "./Users/usersIndex.json");
	}
	
	/**  
	 * @return a JSONArray of all the users' JSONobjects
	 */
	public static DoubleLinkedList getUsers() 
	{	
		return (DoubleLinkedList)ReadObjectFromFile("./Users/usersIndex.json");
	}
	
	 
	public static void printUsers() {
		DoubleLinkedList arr = getUsers();
		for (int i = 0;i < arr.size();i++) {
			User user = (User) arr.get(i);
			System.out.println("---------------------------------------");
			System.out.println("Id = " + user.id);
			
			String[] emails = user.emails;
			String password = user.password;

			for (int j = 0;j < 10 && emails[j] != null;j++)
				System.out.println("Email : " + emails[j] + ", pass : " + password);
		}
		System.out.println("........................................");
	}
	
	
	
	public static void main(String[] args) {
		FolderManagerBIN f = new FolderManagerBIN();
		f.initProgramDirectories();
		DoubleLinkedList users = getUsers();
		users.add(new User("ahmed", "Bahgat", "ahmedelsherif@gmal.com", "fsfsdfds"));
		saveUsersLinkedList(users);
		
		System.out.println(((User)users.get(0)).emails[0]);
	}
}
