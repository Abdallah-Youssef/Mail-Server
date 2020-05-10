package eg.edu.alexu.csd.datastructure.mailServer.gui;

import javax.swing.*;

import eg.edu.alexu.csd.datastructure.mailServer.User;

public class ManageContactsGUI extends JFrame {
	JLabel emailLabel;
	JLabel emailError;
	JTextField emailField;
	
	
	
	User user;
	public ManageContactsGUI(User user) {
		super("Manage your Contacts");
		this.user = user;
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		emailLabel = new JLabel("Enter one of their emails : ");
		emailField = new JTextField(40);
		
		
		
	}
	
	private class ContactLabel extends JLabel{
		
	}
}
