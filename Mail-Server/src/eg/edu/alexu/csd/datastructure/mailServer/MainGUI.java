package eg.edu.alexu.csd.datastructure.mailServer;
import javax.swing.*;

public class MainGUI {
	public static void main(String[] args) {
		
		String[] arr = new String[] {"Jeff", "Capital", "man", "k"};
		
		
		Sorting.quickSort(arr,0, arr.length-1,new Comparator() {
			public int compare(Object x, Object y) {
				return ((String) x).length() - ((String) y).length();
			}
		});
		
		for (int i = 0;i < arr.length;i++)
			System.out.println(arr[i] + " ");
		
		
		/*FolderManager.clearIndex("Users/usersIndex.json");
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new SignUpGUI();
			}
		});*/
	}
}
 