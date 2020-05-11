package eg.edu.alexu.csd.datastructure.mailServer;





import interfaces.IComparator;
import interfaces.ISort;

public class Sort implements ISort{
	
	public static void quickSort(Object[] arr, int l, int h, IComparator comp) {
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
	
	
	static int partition(Object[] arr, int l, int h, IComparator comp) 
	{ 
		Object x = arr[h];
	    int i = (l - 1);
	  
	    for (int j = l; j <= h - 1; j++) {
	    	if (comp.compare(arr[j], x) <= 0) {
	    		i++;
	    		Object temp = arr[i];
	    		arr[i] = arr[j];
	    		arr[j] = temp;
	    	}
	    } 
	    
	    Object temp = arr[i+1];
	    arr[i+1] = arr[h];
	    arr[h] = temp;
	    return (i + 1);
	}


	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	} 
	
}
