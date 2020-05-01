package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		
		
		User test = new User("haha", "popo", "koskos@zobzob.com", "passssap");
		FolderManagerBIN.addUser(test);
		//MainPageGUI.Run();
		FolderManagerBIN.printUsers();
		EmailViewGUI.Run("koskos@zobzob.com", FolderManagerBIN.getUsers());
	}
}
 