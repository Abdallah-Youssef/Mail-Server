package eg.edu.alexu.csd.datastructure.mailServer.gui;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class MainGUI {
	public static void main(String[] args) {
		
		//String[] arr = new String[] {"Jeff", "Capital", "man", "k"};
		
		
		//Sort.quickSort(arr,0, arr.length-1,new IComparator() {
			/*public int compare(Object x, Object y) {
				return ((String) x).length() - ((String) y).length();
			}
		});*/
		
		/*for (int i = 0;i < arr.length;i++)
			System.out.print(arr[i] + " ");
		*/
		
		FolderManagerBIN.saveUsersLinkedList(new DoublyLinkedList());
		User test = new User("haha", "popo", "koskos@zobzob.com", "passssap");
		
		FolderManagerBIN.addUser(test);
		EmailViewGUI.Run("koskos@zobzob.com", new DoublyLinkedList());
		
	}
}
 