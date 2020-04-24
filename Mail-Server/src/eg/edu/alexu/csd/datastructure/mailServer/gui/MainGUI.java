package eg.edu.alexu.csd.datastructure.mailServer.gui;

import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;



public class MainGUI {
	public static void main(String[] args) {
		new FolderManager();
		FolderManager.clearJSONFile("Users/usersIndex.json");
		
		
		EMailHomePageGUI.run();
	}
}
 