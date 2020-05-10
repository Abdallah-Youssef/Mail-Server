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
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class ContactsGUI extends JFrame{
JPanel ValidContacts=new JPanel();
JPanel AddedContacts =new JPanel();
DoublyLinkedList Added=new DoublyLinkedList();
DoublyLinkedList Exist=new DoublyLinkedList();
GridBagConstraints GC;
JScrollPane scroll1;
JScrollPane scroll2;

public GridBagLayout gridBagLayout = new GridBagLayout();
public ContactsGUI(App Ap) {
	
	setSize(1000,1000);
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
	 
	Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
	Border insideBorder = BorderFactory.createTitledBorder("Welcome");
	getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
	setLayout(new GridBagLayout()); 
	ValidContacts.setPreferredSize(new Dimension(400,600));
	AddedContacts.setPreferredSize(new Dimension(400,600));
	setLayout(gridBagLayout);
	GC=new GridBagConstraints();
	GC.weightx =1 ;
	GC.weighty =1;
	GC.fill = GridBagConstraints.NONE;	
	loadingData( Ap);
	add(scroll2, GC);
	add(scroll1, GC);
}
private void loadingData(App Ap) {
	DoubleLinkedList addedContactsholder=Ap.loggedInUser.getContactsIDs();
	for(int i=0;addedContactsholder.get(i)!=null;i++) {
		Added.add(FolderManagerBIN.getUser((int) addedContactsholder.get(i)).getEmails().get(0));
	}
	DoubleLinkedList ExistContactsholder=FolderManagerBIN.getUsers();
	for(int i=0;ExistContactsholder.get(i)!=null;i++) {
		User user =(User) ExistContactsholder.get(i);
		Exist.add(user.getEmails().get(0));
	}
	
	AddContactsToPanel (AddedContacts,Exist,Ap);
	AddedContacts.setMinimumSize(new Dimension(200,200));
	
	ExistContactsToPanel(ValidContacts,Added);
	scroll1 = new JScrollPane(AddedContacts);
	scroll2=new JScrollPane(ValidContacts);
}

private void AddContactsToPanel (JPanel panel,DoublyLinkedList Exis,App ap){
	for(int i=0;i<Exis.size();i++) {
		User user= FolderManagerBIN.getUser((String)Exis.get(i));
		
		JButton button=new JButton( Exis.get(i)+"" );
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(BorderFactory.createEmptyBorder(7, 20, 7, 250));
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!user.idExistInContacts(user.getID())) {
				ap.loggedInUser.addContactID(user.getID());
				loadingData(ap);
				}
			}
		});
	}
}
public static void run(App ap) {
	SwingUtilities.invokeLater(new Runnable () {
		public void run() {
			new ContactsGUI(ap);
		}
	});
}
private void ExistContactsToPanel (JPanel panel,DoublyLinkedList Added){
	for(int i=0;i<Added.size();i++) {
		JLabel button=new JLabel((String) Added.get(i) );
		panel.add(button);
	}
}

}
