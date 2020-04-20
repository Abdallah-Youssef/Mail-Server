package eg.edu.alexu.csd.datastructure.mailServer;
import javax.swing.*;

public class MainGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LoginGUI();
			}
		});
	}
}
