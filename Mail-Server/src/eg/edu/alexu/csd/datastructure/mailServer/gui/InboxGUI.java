package eg.edu.alexu.csd.datastructure.mailServer.gui;

import javax.swing.JFrame;

import eg.edu.alexu.csd.datastructure.mailServer.User;

public class InboxGUI extends JFrame {
	public InboxGUI(User user) {
		super("Log IN");
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
