package eg.edu.alexu.csd.datastructure.mailServer.gui;

import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;
import eg.edu.alexu.csd.datastructure.mailServer.User;



public class MainGUI {
	public static void main(String[] args) {
		new FolderManager();
		ComposeGUI.run(new User("test", "test", "test", "test"));
		FolderManager.printUsers();
		//LoginGUI.run();
	}
}
 