package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		
		FolderManagerBIN.clearUsers();
		//new File("./Users").mkdirs();
		
		
		User test = new User("test1", "popo", "user1@gmail.com", "passssap");
		FolderManagerBIN.addNewUser(test);

		User test2 = new User("test2", "popo", "koskos@eltany.com", "passssap");
		FolderManagerBIN.addNewUser(test2);
		
		//TODO fix error : all users have the same id
		test.addContactID(test2.getID());

		FolderManagerBIN.printUsers();
		MainPageGUI.Run();
	}
}
 