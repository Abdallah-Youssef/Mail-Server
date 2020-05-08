package eg.edu.alexu.csd.datastructure.mailServer.gui;

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

import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

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
public EmailModificationGUI(User user) {
	super("Email option");
	 setSize(800,600);
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
	 oldPassF=new JPasswordField(25);
	 nPassF=new JPasswordField(25);
	 //layout
	
	 //Boxes
	 Box NewEmails=Box.createHorizontalBox();
	 Box NewPass=Box.createHorizontalBox();
	 Box oldPass=Box.createHorizontalBox();
	 //Boxes adding
	 NewEmails.add(EMail);
	 NewEmails.add(nEmail);
	 NewEmails.add(AddNewEmail);
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
	 //
	 AddNewEmail.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			newEMail=nEmail.getText();
			if(!newEMail.trim().equals("")&&newEMail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")&&FolderManagerBIN.getUser(newEMail)==null) {
				user.addEmail(user, newEMail);
			}
			
		}
		 
	 });
	 ChangePassword.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
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
