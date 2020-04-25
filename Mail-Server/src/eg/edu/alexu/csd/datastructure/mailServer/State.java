package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.FileReader;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;

public class State {
	User user;
	String path;

	/*
	 * Pair of the name and (index of folder => null if not loaded)
	 */
	DoublyLinkedList folderStatus;
	public State(User user) {
		this.user = user;
		path = "./Users/" + user.id + "/";
		
		String[] folderNames = Folder.listFolders(user.id);
		for (int i = 0;i < folderNames.length;i++)
			loadFolder(folderNames[i], i);
	}
	
	/**
	 * 
	 * @param folder
	 * @return doublyLinkedList of Email objects
	 */
	public DoublyLinkedList getFolder(IFolder folder) {
		DoublyLinkedList emails = new DoublyLinkedList();
		
		for (int i = 0;i < folderStatus.size();i++) {
			if ((((Pair)folderStatus.get(i)).getFirst()).equals(((Folder)folder).type)) {
				if ((((Pair)folderStatus.get(i)).getSecond()) == null) {
					return (DoublyLinkedList) ((Pair)folderStatus.get(i)).getSecond();
				}
			}
		}
		return emails;
	}
	

	
	private void loadFolder(String folderName, int folderStatusIndex) {
		folderStatus.set(folderStatusIndex, Email.readUserEmails(user.id, new Folder(folderName)));
	}
}
