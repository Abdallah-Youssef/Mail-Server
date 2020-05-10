package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.stack.Classes.Stack;
import interfaces.ISort;


public class SortingTemp {
	
	
	public static void quickSort(DoubleLinkedList arr, ISort comp)
	{
		if (arr.size() != 0)
			_quickSort(arr, 0, arr.size()-1, comp);
	}
	
	public static void _quickSort(DoubleLinkedList arr, int l, int h, ISort comp) {
		
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
	
	
	static int partition(DoubleLinkedList arr, int l, int h, ISort c) 
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
/*
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
			String emailA = (String) ((User)a).emails.get(0);
			String emailB = (String) ((User)b).emails.get(0);
			return emailA.compareTo(emailB);
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
*/