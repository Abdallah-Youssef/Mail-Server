package eg.edu.alexu.csd.datastructure.mailServer;

import org.json.simple.JSONObject;

import dataStructures.Stack;
import eg.edu.alexu.csd.datastructure.mailServer.Comparator;
import eg.edu.alexu.csd.datastructure.mailServer.Pair;

public class BinarySearch {
	private class Comparator_ implements Comparator {
		public int compare(Object x, Object y,String typeOfData) {
			// TODO Auto-generated method stub
			if(typeOfData.equals("String")) {
				String first=(String) x;
				String second=(String)y;
				return second.compareTo(first);
			}
			else if(typeOfData.equals("numbers")) {
				return (int)x-(int )y;
			}
			return 0;
			
		}
	}
	
	
	int binarySearch(JSONObject arr[], int l, int r, Object x,String typeOfData) 
    {
		Comparator_ C=new Comparator_();
		Stack S = new Stack();
		Pair P=new Pair(l, r);
		S.push(P);
        while (!S.isEmpty()) {  
        	P=(Pair) S.pop();
        	l=(int) P.getFirst();
	    	r=(int) P.getSecond();
	    	if(l<=r) {
		    	int mid = l + (r - l) / 2; 
		    	// If the element is present at the middle itself 
		    	if ((int)C.compare(arr[mid],x,typeOfData) == 0) { 
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


