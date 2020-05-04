package eg.edu.alexu.csd.datastructure.mailServer;



import java.io.Serializable;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import interfaces.IFolder;

public class State implements Serializable{
	User user;
	String path;
	
	/*
	 * Pair of the name and (list of emails in folder => null if not loaded)
	 */
	DoubleLinkedList folderStatus;
	String[] folderNames;
	public State(User user) {
		this.user = user;
		path = "./Users/" + user.id + "/";
		
		//TODO fix listFolders
		//String[] folderNames = Folder.listFolders(user.id);
		folderNames = new String[]{"inbox","sent","trash"};
		
		folderStatus = new DoubleLinkedList();
		
		/*//debugging
		System.out.println("folder names");
		for (int i = 0;i < folderNames.length;i++)
			System.out.print(folderNames[i] + " ");
		System.out.println();*/
		
		for (int i = 0;i < folderNames.length;i++) {
			folderStatus.add(Email.readUserEmails(user.id, new Folder(folderNames[i])));
		}
	}
	
	public DoubleLinkedList getEmailsFromFolder(String folderName) {
		DoubleLinkedList emails = new DoubleLinkedList();
		
		for (int i = 0;i < folderNames.length;i++) {
			if (folderName.equals(folderNames[i])) {
				return (DoubleLinkedList) folderStatus.get(i);
			}
		}
		System.out.println("Failed to find the folder");
		return null;
	}

	
	private void loadFolder(String folderName, int folderStatusIndex) {
		folderStatus.set(folderStatusIndex, Email.readUserEmails(user.id, new Folder(path + folderName)));
	}
}
