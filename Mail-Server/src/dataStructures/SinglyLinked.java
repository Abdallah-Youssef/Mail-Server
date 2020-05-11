package dataStructures;

import java.io.Serializable;

import interfaces.*;
public class SinglyLinked implements interfaces.ILinkedList, Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 5923205917986588732L;
	long size=size();
	Node head;
	Node tail;
	
	static class Node implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 549853224232478892L;
		Object data;
		Node next;
		Node(Object v, Node r){
			data=v;
			next=r;
		}
	}
	
	public void add(int index, Object element) {
		Node f=head;
		size=size();
		if(index==0&&isEmpty()) {
			tail=head=new Node(element,null);
		}
		else if(index==0) {
			head=new Node(element,f);
		}
		else {
			for(int i=0;i<size;i++) {
			   if(index==size&&i==index-1) {
				   Node newNode=new Node(element,null);
					f.next=newNode;
					tail=newNode;
			   }
			   else if(i==index-1) {
					Node newNode=new Node(element,f.next);
					f.next=newNode;
			   }
			   f=f.next;
			}	
		}
	}
	public void add(Object element) {
		if(isEmpty()) {
			tail=head=new Node(element,null);
		}
		else {
			Node f=head;
			 size=size();
			for(int i=0;i<size;i++) {
				if(i==size-1) {
					Node newNode=new Node(element,null);
					f.next=newNode;
					tail=newNode;
				}
				f=f.next;
			}
			
		}
	}
	public Object get(int index) {
		Node f=head;
		size=size();
		if(index>=size) {
			throw new RuntimeException("Out of bounds");
		}
		else {
		for (int i=0;i<index&&i<size;i++) {
			f=f.next;
		}
		return f.data;
	}
	}
	public void set(int index, Object element) {
		Node f=head;
		size=size();
		for(int i=0;i<size;i++) {
			if(i==index) {
				f.data=element;
				return;
			}
		f=f.next;
		}
		
		throw new RuntimeException("Out of bounds");
	}
	public void clear() {
		head=null;
		tail=null;
	}
	public boolean isEmpty() {
		return(head==null);
	}
	public void remove(int index) {
		Node f=head;
		size=size();
		
		if (size == 0)
			throw new RuntimeException("Empty List");
		
		if(index==0) {
			head=f.next;
		}
		
		else {
		for(int i=0;i<index;i++) {
			if(index==size-1&&index-1==i) {
				f.next=null;
				tail=f;
			}
			else if(i==index-1) {
				f.next=f.next.next;
			}
			f=f.next;
		}
		
	}
	}
	public int size() {
		Node f=head;
		int i;
		for(i=0;(f!=null);) {
			i++;
			f=f.next;
		}
		return i;
		
	}
	public ILinkedList sublist(int fromIndex, int toIndex) {
		SinglyLinked list = new SinglyLinked(); 
		Node f=head;
		for(int i=0;i<=toIndex;i++) {
			if(i>=fromIndex||i<=toIndex) {
				list.add(f.data);
			}
			f=f.next;
		}
		return list;
	}
	
	
	public boolean contains(Object o) {
		Node f=head;
		boolean flag=false;
		size=size();
		for(int i=0;i<size&&f!=null;i++) {
			if(f.data==o) {
				flag=true;
			}
			f=f.next;
		} 
		return flag;
	}
	
	
	
	
	
	

}