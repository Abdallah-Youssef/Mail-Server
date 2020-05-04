package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class MainGUI {
	public static void main(String[] args) throws IOException {
		FolderManagerBIN.clearUsers();
		User test = new User("haha", "popo", "koskos@zobzob.com", "passssap");
		User reciever = new User("haha", "popo", "reciever@zobzob.com", "passssap");
		
		FolderManagerBIN.addNewUser(test);
		FolderManagerBIN.addNewUser(reciever);
		
		
		
		
		SinglyLinked receivers = new SinglyLinked();
		receivers.add("reciever@zobzob.com");
		
	
		
		EmailViewGUI.Run("koskos@zobzob.com",receivers);
	}
}
 