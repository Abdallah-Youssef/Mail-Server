package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.stack.Classes.Stack;
import interfaces.ISort;


public class SortingTemp {
	
	
	public static void quickSort(DoublyLinkedList arr, ISort comp)
	{
		_quickSort(arr, 0, arr.size(), comp);
	}
	
	public static void _quickSort(DoublyLinkedList arr, int l, int h, ISort comp) {
		
		//Changed from StackDS because the jar we have imported it is called Stack
		Stack stack = new Stack();
		stack.push(l);
		stack.push(h);
		 
		while(!stack.isEmpty()){
			h = (int) stack.pop();
			l = (int) stack.pop();
			
			int p = partition(arr, l, h, comp); 
			if (p - 1 > l) { 
	            stack.push(l);
	            stack.push(p-1);
	        }
			
			if (p + 1 < h) { 
				stack.push(p + 1);
				stack.push(h);
	        }
		}
	}
	
	
	static int partition(DoublyLinkedList arr, int l, int h, ISort c) 
	{ 
		Object x = arr.get(h); 
		
		sortComparator comp = (sortComparator)c;
	    int i = (l - 1); 
	  
	    for (int j = l; j <= h - 1; j++) { 
	    	if (comp.mycompare(arr.get(j), x) <= 0) {
	    		i++;
	    		Object temp = arr.get(i);
	    		arr.set(i, arr.get(j));
	    		arr.set(j, temp);
	    	}
	    } 
	    
	    Object temp = arr.get(i+1);
	    arr.set(i+1, arr.get(h));
	    arr.set(h, temp);
	    return (i + 1); 
	} 
	
	
}

class sortComparator implements ISort
{
	int type;
	
	public sortComparator(int type) {
		this.type = type;
	}
	
	public int mycompare(Object a, Object b)
	{
		switch(type)
		{
		case 0:
			return ((User)a).firstName.compareTo(((User)b).firstName);
		case 1:
			return ((User)a).emails[0].compareTo(((User)b).emails[0]);
		case 2:
			return ((Email)a).subject.compareTo(((Email)b).subject);
		case 3:
			return ((Email)a).senderEmail.compareTo(((Email)b).senderEmail);
		case 4:
			return ((Email)a).receiverEmail.compareTo(((Email)b).receiverEmail);
		case 5:
			return ((Email)a).date.compareTo(((Email)b).date);
		default:
		{
			if(((Email)a).priority < ((Email)b).priority)
				return -1;
			else if(((Email)a).priority == ((Email)a).priority)
				return 0;
			else
				return 1;
		}
		}	
	}
}
/*
class sortUsersByName implements ISort
{

	public int compare(User o1, User o2) {
		return o1.firstName.compareTo(o2.firstName);
	}
}

class sortUsersByEmail implements ISort, Comparator<User>
{

	public int compare(User o1, User o2) {
		return o1.emails[0].compareTo(o2.emails[0]);
	}
}

class sortEmailsbySubject implements ISort, Comparator<Email>
{
	public int compare(Email o1, Email o2) {
		return o1.subject.compareTo(o2.subject);
	}
	
}

class sortEmailsbySender implements ISort, Comparator<Email>
{

	public int compare(Email o1, Email o2) {
		return o1.senderEmail.compareTo(o2.senderEmail);
	}
	
}

class sortEmailsbyReceiver implements ISort, Comparator<Email>
{

	public int compare(Email o1, Email o2) {
		return o1.receiverEmail.compareTo(o2.receiverEmail);
	}
	
}

class sortEmailsbyDate implements ISort, Comparator<Email>
{

	public int compare(Email o1, Email o2) {
		return o1.date.compareTo(o2.date);
	}
	
}

class sortEmailsbyPriority implements ISort, Comparator<Email>
{
	public int compare(Email o1, Email o2) {
		return o1.date.compareTo(o2.date);
	}
	
}
*/