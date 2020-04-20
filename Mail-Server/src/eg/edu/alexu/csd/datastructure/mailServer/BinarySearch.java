package eg.edu.alexu.csd.datastructure.mailServer;

import java.util.Stack;

public class BinarySearch {
	int binarySearch(Object arr[], int l, int r, Object x,String typeOfData) 
    {
		Comparator_ C=new Comparator_();
		class pair{
			private Object First;
			private Object Second;
			public pair(Object first,Object second) {
				First=first;
				Second =second;
			}
			public Object getFirst() {
				return First;
			}
			public Object getSecond() {
				return Second;
			}
		}
		Stack S = new Stack();
		pair P=new pair(l, r);
		S.push(P);
        while (!S.isEmpty()) {  
        	P=(pair) S.pop();
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
        	 		P=new pair(l,mid-1);          
        	 		S.push(P); 
        	 	}
        	 	// Else the element can only be present in right 
        	 	// subarray 
            	else {
            		P=new pair(mid+1,r);
            		S.push(P);
        
            	} 
        	 }
        	}
        // We reach here when element is not present in array 
        return -1;  
    }
        
    }


