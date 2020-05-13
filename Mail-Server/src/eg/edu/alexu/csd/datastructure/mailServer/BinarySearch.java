package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.mailServer.Pair;
import eg.edu.alexu.csd.datastructure.stack.Classes.Stack;

public class BinarySearch {
	
	int binarySearch(DoubleLinkedList arr, sortComparator C) 
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
		    	// If the element is present at the middle itself 
		    	if ((int)C.compare(arr.get(mid),x,typeOfData) == 0) { 
		    		return mid;
		    	}
		    	 		//return the index
		  
		        	 		// If element is smaller than mid, then it can only 
		    	 		// be present in left subarray 
			 	if ((int)C.compare(arr[mid], x, typeOfData) < 0) { 
			 		P=new Pair(l,mid-1);          
			 		S.push(P); 
			 	}
			 	// Else the element can only be present in right 
			 	// subarray 
		    	else {
		    		P=new Pair(mid+1,r);
		    		S.push(P);
		    	} 
	    	}
    	}
        // We reach here when element is not present in array 
        return -1;  
    }
        
}


