package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Listeners.RemoveElementListener;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import eg.edu.alexu.csd.datastructure.mailServer.gui.ElementsBox.Element;

public class EmailModificationGUI extends JFrame {
	String newEMail;
	String newPassAdded;
	String oldPassCheck;
	JButton AddNewEmail;
	JButton ChangePassword;
	JLabel EMail;
	JLabel newPass;
	JLabel oldpass;
	JLabel error;
	JTextField nEmail;
	JPasswordField oldPassF;
	JPasswordField nPassF;
	GridBagConstraints GC;
	public GridBagLayout gridBagLayout = new GridBagLayout();
	
	ElementsBox emailsBox;
	JLabel emailErrorLabel;
	
	public EmailModificationGUI(User user) {
		super("Email option");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		 
		Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Welcome");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
			
		setLayout(new GridBagLayout()); 
		setLayout(gridBagLayout);
		GC=new GridBagConstraints();
		GC.weightx =1 ;
		GC.weighty =1;
		GC.fill = GridBagConstraints.NONE;		
		//componenets
		error=new JLabel("");
		AddNewEmail=new JButton("Add EMail");
		ChangePassword=new JButton("Change Password");
		EMail=new JLabel("Enter new Email");
		newPass=new JLabel("Enter New Password");
		oldpass=new JLabel("Enter current password");
		
		nEmail=new JTextField(25);
		nEmail.setMinimumSize(new Dimension(300,300));
		
		oldPassF=new JPasswordField(25);
		nPassF=new JPasswordField(25);
		
		emailErrorLabel = new JLabel("");
		emailsBox = new ElementsBox(ListUtils.doubleToSingleList(user.getEmails()),
									"Current EMails",
									emailErrorLabel
									);
		
		//layout
		
		//Boxes
		Box NewEmails=Box.createHorizontalBox();
		Box NewPass=Box.createHorizontalBox();
		Box oldPass=Box.createHorizontalBox();
		 //Boxes adding
		NewEmails.add(EMail);
		NewEmails.add(nEmail);
		NewEmails.add(AddNewEmail);
		
		NewEmails.add(emailsBox);
		NewEmails.add(emailErrorLabel);
		//
		NewPass.add(newPass);
		NewPass.add(nPassF);
		//
		oldPass.add(oldpass);
		oldPass.add(oldPassF);
		//grid adding
		setGridCell(0,0);
		GC.anchor = GridBagConstraints.LINE_START;
		add(NewEmails,GC);
		 
		setGridCell(0,1);
		GC.anchor = GridBagConstraints.LINE_START;
		add(oldPass,GC);
		 
		setGridCell(0,2);
		GC.anchor = GridBagConstraints.LINE_START;
		add(NewPass,GC);
		 
		setGridCell(0,3);
		GC.anchor = GridBagConstraints.CENTER;
		add(ChangePassword,GC);
		setGridCell(0,4);
		GC.anchor = GridBagConstraints.CENTER;
		add(error,GC);
		 
		
		AddNewEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newEMail=nEmail.getText();
				if(!newEMail.trim().equals("") && 
					newEMail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					
					if(FolderManagerBIN.getUser(newEMail)==null) {
						user.addEmail(newEMail);
						emailsBox.Add(newEMail);
						emailErrorLabel.setText("");
						revalidate();
					}else {
						emailErrorLabel.setText("Email Already Exists");
					}
				}
			} 
		 });
		
		 ChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newPassAdded=new String(nPassF.getPassword());;
				 oldPassCheck=new String(oldPassF.getPassword());;
				 if(newPassAdded.equals("")||!oldPassCheck.equals(user.password)) {
					 error.setText("wrong inputs");
				 }
				 else {
					 user.password=newPassAdded;
				 }
			}
		 });
		 
		 
		 //Implement Listener for when an email is deleted, called by elementsBox to request a delete
		 //return false if you want to cancel the deletion, true if it's okay to delete
		 emailsBox.setRemoveListener(new RemoveElementListener() {
			public boolean elementRemoved(Element element) {
				DoubleLinkedList emails =  user.getEmails();
				String elementEmail = element.label.getText();
				
				if (emails.size() <= 1)
					return false;
				
				for (int i = 0;i < emails.size();i++) {
					if (((String)emails.get(i)).equals(elementEmail)) {
						user.removeEmail(elementEmail);
						user.printEmails();
						return true;
					}
				}
				
				return false;
					
			}
		 });
		 
		 
		 
		 
		 
	}

	private void setGridCell(int x, int y) {
		GC.gridx = x;
		GC.gridy = y;
	}
	public static void run(User user) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EmailModificationGUI(user );
			}
		});
	}
}
