package eg.edu.alexu.csd.datastructure.mailServer;
import javax.swing.*;

public class MainGUI {
	public static void main(String[] args) {
		FolderManager.clearIndex("Users/usersIndex.json");
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new SignUpGUI();
			}
		});
	}
}
 