package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import dataStructures.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import eg.edu.alexu.csd.datastructure.mailServer.gui.ElementsBox.Element;
import listeners.RemoveElementListener;

public class ContactsGUI extends JFrame{
JFrame frame = this;
JPanel indexPanel = new JPanel();

JPanel contactsPanel = new JPanel();
ElementsBox contactsBox;

DoubleLinkedList contacts = new DoubleLinkedList();
DoubleLinkedList index = new DoubleLinkedList();


GridBagConstraints GC;
JScrollPane scroll1;
JScrollPane scroll2;

App app;


public GridBagLayout gridBagLayout = new GridBagLayout();
	public ContactsGUI(App app) {
		this.app = app;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		Border outsideBorder = BorderFactory.createEmptyBorder(25, 25, 25, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Manage Your Contacts");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		
		loadingData();
		
		
		
		
		indexPanel.setLayout(new BoxLayout(indexPanel, BoxLayout.Y_AXIS));
		
		scroll1 = new JScrollPane(contactsPanel);
		scroll2 = new JScrollPane(indexPanel);
		
		add(scroll1);
		add(scroll2);
		
	}
	private void loadingData() {	
		contacts = app.loggedInUser.getContacts();
		index = FolderManagerBIN.getUsers();
		
		//Left panel
		contactsBox = new ElementsBox(ListUtils.doubleToSingleList(contacts), "Contacts", new JLabel());
		contactsBox.setRemoveListener(new RemoveElementListener() {
			public boolean elementRemoved(Element element) {
				String email = element.label.getText();
				app.loggedInUser.removeContact(email);
				return true;
			}
		});
		contactsPanel.add(contactsBox);
		
		//Right Panel
		for (int i = 0;i < index.size();i++) {
			User user = (User) index.get(i);
			DoubleLinkedList emails = user.getEmails();
			
			JLabel nameLabel = new JLabel(user.firstName);
			nameLabel.setAlignmentX(CENTER_ALIGNMENT);
			indexPanel.add(nameLabel);
			
			for (int j = 0;j < emails.size();j++) {
				String email = (String) emails.get(j);
				
				JButton btn = new JButton(email);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (app.loggedInUser.addContact(email)) {
							contactsBox.Add(email);
							contactsPanel.revalidate();
							indexPanel.revalidate();
							frame.revalidate();
						}
					}
				});
				btn.setAlignmentX(CENTER_ALIGNMENT);
				indexPanel.add(btn);
				
				JLabel line = new JLabel("--------------------------------------");
				line.setAlignmentX(CENTER_ALIGNMENT);
				indexPanel.add(Box.createRigidArea(new Dimension(5, 10)));
				indexPanel.add(line);
				indexPanel.add(Box.createRigidArea(new Dimension(5, 10)));
			}
		}
	}
	

	public static void run(App ap) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new ContactsGUI(ap);
			}
		});
	}
	


}
