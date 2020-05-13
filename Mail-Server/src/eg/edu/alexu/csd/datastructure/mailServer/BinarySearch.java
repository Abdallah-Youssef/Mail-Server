package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.mailServer.Pair;
import eg.edu.alexu.csd.datastructure.stack.Classes.Stack;

public class BinarySearch {
	
	Email binarySearch(DoubleLinkedList arr, Email m,sortComparator C) 
    {
		Stack S = new Stack();
		Pair P=new Pair(0, arr.size());
		S.push(P);
        while (!S.isEmpty()) {  
        	P=(Pair) S.pop();
        	int l =(int) P.getFirst();
	    	int r =(int) P.getSecond();
	    	if(l<=r) {
		    	int mid = l + (r - l) / 2; 
		    	int comparison = C.mycompare(arr.get(mid),m);
		    	if (comparison == 0)
		    		return (Email)arr.get(mid);
			 	if (comparison < 0) { 
			 		P=new Pair(l,mid-1);          
			 		S.push(P); 
			 	}
		    	else {
		    		P=new Pair(mid+1,r);
		    		S.push(P);
		    	} 
	    	}
    	}
        return null;  
    }
        
}


