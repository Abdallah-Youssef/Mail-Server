package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.FileReader;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;

public class State {
	User user;
	String path;

	/*
	 * Pair of the name and (list of emails => null if not loaded)
	 */
	DoublyLinkedList folderStatus;
	public State(User user) {
		this.user = user;
		path = "./Users/" + user.id + "/";
		
		folderStatus.add(new Pair("inbox", null));
		folderStatus.add(new Pair("sent", null));
		folderStatus.add(new Pair("drafts", null));
		folderStatus.add(new Pair("trash", null));
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
					//return loadFolder(folder);
				}else {
					return (DoublyLinkedList) ((Pair)folderStatus.get(i)).getSecond();
				}
			}
		}
		return emails;
	}
	
	
	/*public DoublyLinkedList loadFolder(IFolder folder) {
		FileReader reader = new FileReader(path + "body");
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		
	}*/
}
