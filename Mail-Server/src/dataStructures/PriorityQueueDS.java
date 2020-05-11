package dataStructures;

import interfaces.IPriorityQueue;

public class PriorityQueueDS implements IPriorityQueue
{

	
	private class Node
	{
		Object data;
		int key;
		Node next;
				
		Node(int key, Object data)
		{
			this.next = null;
			this.data = data;
			this.key = key;
		}
	}
	
	int numOfElements;
	Node head;
	
	public PriorityQueueDS() {
		numOfElements = 0;
		head = null;
	}
	
	
	@Override
	public void insert(Object item, int key) {
		if(head == null)
			head = new Node(key, item);
		else if(key < head.key)
		{
			Node tmp = new Node(key, item);
			tmp.next = head;
			head = tmp;
		}
		else
		{
			Node x = head;
			while(x.next != null && key > x.next.key)
				x = x.next;
			Node tmp = new Node(key, item);
			tmp.next = x.next;
			x.next = tmp;
		}
		numOfElements++;
	}

	@Override
	public Object removeMin() {
		if(head == null)
			throw new RuntimeException("Empty Queue");
		Object data = head.data;
		head = head.next;
		numOfElements--;
		return data;
	}

	@Override
	public Object min() {
		if(head == null)
			throw new RuntimeException("Empty Queue");
		return head.data;
	}

	@Override
	public boolean isEmpty() {
		return numOfElements == 0;
	}

	@Override
	public int size() {
		return numOfElements;
	}
	
	public void traverse()
	{
		if(head == null)
			return;
		Node x = head;
		while(x != null)
		{
			System.out.println(x.key + " " + (int)x.data);
			x = x.next;
		}
	}
	
	public static void debug(Object x)
	{
		System.out.println(x);
	}
	
}
