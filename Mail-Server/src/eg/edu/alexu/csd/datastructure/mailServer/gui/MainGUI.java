package eg.edu.alexu.csd.datastructure.mailServer.gui;
import eg.edu.alexu.csd.datastructure.mailServer.IComparator;
import eg.edu.alexu.csd.datastructure.mailServer.Sort;

public class MainGUI {
	public static void main(String[] args) {
		
		String[] arr = new String[] {"Jeff", "Capital", "man", "k"};
		
		
		Sort.quickSort(arr,0, arr.length-1,new IComparator() {
			public int compare(Object x, Object y) {
				return ((String) x).length() - ((String) y).length();
			}
		});
		
		for (int i = 0;i < arr.length;i++)
			System.out.print(arr[i] + " ");
		
		
		/*FolderManager.clearIndex("Users/usersIndex.json");
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new SignUpGUI();
			}
		});*/
	}
}
 