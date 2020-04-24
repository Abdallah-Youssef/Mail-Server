package oldFiles;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class WriterReader {

	public static void main(String[] args) {

		User user = new User("a","b","a@b.com", "ahaha");
		DoublyLinkedList list = new DoublyLinkedList();
		list.add(user);
		
		
		try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(list);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			list = (DoublyLinkedList) oi.readObject();
			list.print();
			System.out.println(((User)list.get(0)).id);

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}