package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;
import eg.edu.alexu.csd.datastructure.queue.cs.LinkedBasedQueue;

public class ListUtils {
	static public SinglyLinked doubleToSingleList (DoubleLinkedList doubly) {
		SinglyLinked single = new SinglyLinked();
		for (int i = 0;i < doubly.size();i++)
			single.add(doubly.get(i));
		return single;
	}
	
	static public  LinkedBasedQueue singleToQueue (SinglyLinked singly) {
		LinkedBasedQueue queue = new LinkedBasedQueue();
		for (int i = 0;i < singly.size();i++)
			queue.enqueue(singly.get(i));
		return queue;
	}
	
	/**
	 * 
	 * @param emails
	 * @param pageNumber starting from 1
	 * @return arr of 10 emails of the page number
	 */
	static public  Email[] getPage (DoubleLinkedList emails, int pageNumber) {
		Email[] arr = new Email[10];
		int j = 0;
		for (int i = (pageNumber-1)*10;i < emails.size() && i < pageNumber*10;i++) {
			arr[j] = (Email) emails.get(i);
			j++;
		}
		
		return arr;
	}
}
