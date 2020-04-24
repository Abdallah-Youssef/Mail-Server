package eg.edu.alexu.csd.datastructure.mailServer.gui;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;
import eg.edu.alexu.csd.datastructure.mailServer.User;



public class MainGUI {
	public static void main(String[] args) {
		new FolderManager();
		FolderManager.newUser(new User("jeff", "mohamed", "poopoo@gmail.com","strongPass"));
		EmailViewGUI.Run( "poopoo@gmail.com", new DoublyLinkedList());
		FolderManager.printUsers();
		//LoginGUI.run();
	}
}
 