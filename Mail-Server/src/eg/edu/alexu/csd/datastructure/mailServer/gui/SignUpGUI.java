package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;
import eg.edu.alexu.csd.datastructure.mailServer.User;
public class SignUpGUI extends JFrame {
	GridBagConstraints gc;
	JLabel firstName, lastName;
	JLabel email, password;
	JLabel errorLabel;
	JTextField firstNameField, lastNameField;
	JTextField emailField;
	JPasswordField passwordField;
	
	JButton signUpBtn;
	
	
	
	public SignUpGUI () {
		super("Sign Up");
		setSize(600, 300);

		//instead of terminating it will return to the log in
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		firstName = new JLabel("First Name : ");
		lastName = new JLabel("Last Name : ");
		email = new JLabel("Email : ");
		password = new JLabel("Password : ");
		errorLabel=new JLabel("");
		
		firstNameField = new JTextField(25);
		lastNameField = new JTextField(25);
		emailField = new JTextField(25);
		passwordField = new JPasswordField(25);
		signUpBtn = new JButton("Sign Up");
		
		grid();
		
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstNameData = firstNameField.getText().trim();
				String lastNameData = lastNameField.getText().trim();
				String emailData = emailField.getText().trim();
				String passwordData = new String(passwordField.getPassword());
				String errorMsg = "";
				
				if(!emailData.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
					errorMsg += "Invalid Email form\n";
				
				if (FolderManager.getUser(emailData) != null)
					errorMsg += "Email already Exists\n";
				
				
				if(passwordData.length() < 8){
					errorMsg += "weak password (length must be more than 7 characters)\n";
				}
				
				
				if (errorMsg.equals("")) {
					User newUser = new User(firstNameData, lastNameData, emailData, passwordData);
					if (FolderManager.newUser(newUser)) {
						errorMsg +="Success";
						FolderManager.printUsers();
					}else {
						errorMsg += "Error in sign up";
					}
				}
				
				errorLabel.setText(errorMsg);
			}
		});

		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	LoginGUI.run();
                setVisible(false);
                dispose();
                
            }
        });
		
		 
	}
 
	

	
	
	private void grid() {
		//Border
		Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Create a new Email");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		//Layout Manager
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		
		////// First Row
		setGridCell(0,0);
		gc.anchor = GridBagConstraints.LINE_END;
		add(firstName, gc);

		setGridCell(1,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstNameField, gc);
		////////////////////////////////////////////
		////// Second Row
		setGridCell(0,1);
		gc.anchor = GridBagConstraints.LINE_END;
		add(lastName, gc);

		setGridCell(1,1);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastNameField, gc);
		////////////////////////////////////////////
		////// Third Row
		setGridCell(0,2);
		gc.anchor = GridBagConstraints.LINE_END;
		add(email, gc);

		setGridCell(1,2);
		gc.anchor = GridBagConstraints.LINE_START;
		add(emailField, gc);
		////////////////////////////////////////////
		////// Fourth Row
		setGridCell(0,3);
		gc.anchor = GridBagConstraints.LINE_END;
		add(password, gc);

		setGridCell(1,3);
		gc.anchor = GridBagConstraints.LINE_START;
		add(passwordField, gc);
		////////////////////////////////////////////
		///////Fifth Row
		setGridCell(0,4);
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(signUpBtn, gc);

		////////////////////////////////////////////
		///////Sixth Row
		setGridCell(0,5);
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(errorLabel, gc);
	}
	
	private void setGridCell(int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
	}
	
	public static void run() {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new SignUpGUI();
			}
		});
	}
}
