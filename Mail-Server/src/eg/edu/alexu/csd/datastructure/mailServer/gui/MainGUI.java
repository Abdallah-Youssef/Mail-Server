package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		
		FolderManagerBIN.clearUsers();
		//new File("./Users").mkdirs();
		
		
		User test = new User("test1", "popo", "user1@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test);

		User test2 = new User("test2", "popo", "user2@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test2);
		
		User test3 = new User("test3", "popo", "user3@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test3);
		
		User test4 = new User("test4", "popo", "user4@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test4);
		
		User test5 = new User("test5", "popo", "user5@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test5);
		
		User test6 = new User("test6", "popo", "user6@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test6);
		
		
		
		//TODO fix error : all users have the same id

		FolderManagerBIN.printUsers();
		MainPageGUI.Run();
	}
}
 