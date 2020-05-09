package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;

public class ListUtils {
	static public SinglyLinked doubleToSingleList (DoubleLinkedList doubly) {
		SinglyLinked single = new SinglyLinked();
		for (int i = 0;i < doubly.size();i++)
			single.add(doubly.get(i));
		return single;
	}
	
	static public  QueueLinkedBased singleToQueue (SinglyLinked singly) {
		QueueLinkedBased queue = new QueueLinkedBased();
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
