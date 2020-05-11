package dataStructures;

import interfaces.ILinkedBased;
import interfaces.IQueue;

public class QueueLinkedBased implements IQueue, ILinkedBased
{
	
	private class Node{
		Object data;
		Node next;
		
		
		
		Node(Object element)
		{
			data = element;
			next = null;
		}
	}
	
	Node head;
	Node tail;
	int numOfElements;
	
	public QueueLinkedBased() {
		head = null;
		tail = null;
		numOfElements = 0;
	}
	
	
	public void enqueue(Object item) {
		Node tmp = new Node(item);
		if(head == null)
			head = tmp;
		else
			tail.next = tmp;
		tail = tmp;
		numOfElements++;
	}

	
	public Object dequeue() {
		if(head == null)
			throw new RuntimeException("Queue is empty");
		Object data = head.data;
		head = head.next;
		if(head == null)
			tail = null;
		numOfElements--;
		return data;
	}

	
	public boolean isEmpty() {
		return numOfElements == 0;
	}

	
	public int size() {
		return numOfElements;
	}
}
