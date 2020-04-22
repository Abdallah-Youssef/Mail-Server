package eg.edu.alexu.csd.datastructure.mailServer;


import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class SortingTemp {
	
	
	public static void quickSort(JSONObject[] arr, ISort comp)
	{
		_quickSort(arr, 0, arr.length, comp);
	}
	
	public static void _quickSort(JSONObject[] arr, int l, int h, ISort comp) {
		StackDS stack = new StackDS();
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
	
	
	static int partition(JSONObject[] arr, int l, int h, ISort comp) 
	{ 
		JSONObject x = arr[h]; 
	    int i = (l - 1); 
	  
	    for (int j = l; j <= h - 1; j++) { 
	    	if (comp.compare(arr[j], x) <= 0) {
	    		i++;
	    		JSONObject temp = arr[i];
	    		arr[i] = arr[j];
	    		arr[j] = temp;
	    	}
	    } 
	    
	    Object temp = arr[i+1];
	    arr[i+1] = arr[h];
	    arr[h] = (JSONObject) temp;
	    return (i + 1); 
	} 
	
	
}

class sortStrings implements ISort
{
	String key;
	
	public sortStrings(String key) {
		this.key = key;
	}
	
	public int compare(JSONObject a, JSONObject b)
	{
		return ((String)a.get(key)).compareTo((String)b.get(key));
	}
}

class sortUserByEmail implements ISort
{
	public int compare(JSONObject a, JSONObject b)
	{
		String emailA = (String)((JSONArray)a.get("emails")).get(0);
		String emailB = (String)((JSONArray)a.get("emails")).get(0);
		return emailA.compareTo(emailB);
	}
}


class sortUserByDate implements ISort
{
	String key;
	
	public sortUserByDate(String key) {
		this.key = key;
	}
	
	public int compare(JSONObject a, JSONObject b)
	{
		Date dateA = (Date)(a.get(key));
		Date dateB = (Date)(a.get(key));
		return dateA.compareTo(dateB);
	}
}


class sortNumbers implements ISort
{
	String key;
	
	public sortNumbers(String key) {
		this.key = key;
	}

	public int compare(JSONObject a, JSONObject b)
	{
		return (int)a.get(key) - (int)b.get(key);
	}
}