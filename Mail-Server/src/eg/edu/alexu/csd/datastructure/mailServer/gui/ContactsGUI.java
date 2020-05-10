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

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
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

DoubleLinkedList contacts=new DoubleLinkedList();
DoubleLinkedList index = new DoubleLinkedList();
GridBagConstraints GC;
JScrollPane scroll1;
JScrollPane scroll2;

App app;


public GridBagLayout gridBagLayout = new GridBagLayout();
	public ContactsGUI(App app) {
		this.app = app;
		setSize(1000,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Welcome");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		
		indexPanel.setPreferredSize(new Dimension(400,600));
		
		
		
		contactsPanel.setPreferredSize(new Dimension(400,600));
		loadingData(app);
		
		DoubleLinkedList contactNames = new DoubleLinkedList();
		for(int i = 0;i < contacts.size();i++)
			contactNames.add( ((String) ((User)(contacts.get(i))).getEmails().get(0) ));
		
		contactsBox = new ElementsBox(ListUtils.doubleToSingleList(contactNames), "Contacts", new JLabel());
		contactsBox.setRemoveListener(new RemoveElementListener() {
			public boolean elementRemoved(Element element) {
				String email = element.label.getText();
				User user = FolderManagerBIN.getUser(email);
				app.loggedInUser.removeContactID(user.getID());
				return true;
			}
		});
		
		contactsPanel.add(contactsBox);
		scroll1 = new JScrollPane(contactsPanel);
		
		
		add(scroll1);
		add(scroll2);
		
	}
	private void loadingData(App app) {
		DoubleLinkedList contcatsIDs = app.loggedInUser.getContactsIDs();
	
		for(int i=0; contcatsIDs.get(i)!=null ;i++) {
			User contact = FolderManagerBIN.getUser((int)contcatsIDs.get(i));
			contacts.add(contact);
		}
		
		index = FolderManagerBIN.getUsers();
		populateIndexPanel();


		scroll1 = new JScrollPane(contactsPanel);
		scroll2=new JScrollPane(indexPanel);
	}
	

	public static void run(App ap) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new ContactsGUI(ap);
			}
		});
	}
	
	public void populateIndexPanel() {
		for (int i = 0;i < index.size();i++) {
			User user = (User) index.get(i);
			DoubleLinkedList emails = user.getEmails();
			
			indexPanel.setLayout(new BoxLayout(indexPanel, BoxLayout.Y_AXIS));
			
			JButton btn = new JButton(user.firstName);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("hell");
					app.loggedInUser.addContactID(user.getID());
					printIDs(app.loggedInUser);
					contactsBox.Add((String)user.getEmails().get(0));
				}
			});
			
			indexPanel.add(new JButton(user.firstName));
			
			
			for (int j = 0;j < emails.size();j++) {
				JLabel email = new JLabel("   --->" + (String)emails.get(j));
				indexPanel.add(email);
			}
			
		}
	}


	public void printIDs(User user) {
		DoubleLinkedList ids = user.getContactsIDs();
		for (int i = 0;i < ids.size();i++)
			System.out.print((String)ids.get(i) + " ");
		System.out.println();
	}
}
