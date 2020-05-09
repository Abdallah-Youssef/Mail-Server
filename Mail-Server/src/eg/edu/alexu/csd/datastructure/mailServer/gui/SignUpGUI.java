package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Listeners.SignUpErrorListener;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.User;
public class SignUpGUI extends JFrame {
	GridBagConstraints gc;
	JLabel firstName, lastName;
	JLabel email, password;
	JLabel errorLabel;
	JTextField firstNameField, lastNameField;
	JTextField emailField;
	JPasswordField passwordField;
	App app;
	JButton btn;
	
	public SignUpGUI () {
		super("Sign Up");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
		////Creating components
		firstName = new JLabel("First Name : ");
		lastName = new JLabel("Last Name : ");
		email = new JLabel("Email : ");
		password = new JLabel("Password : ");
		errorLabel=new JLabel("");
		
		firstNameField = new JTextField(25);
		lastNameField = new JTextField(25);
		emailField = new JTextField(25);
		passwordField = new JPasswordField(25);
		btn = new JButton("Sign Up");
		
		app = new App();
		
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
		add(btn, gc);
		////////////////////////////////////////////
		///////Sixth Row
		setGridCell(0,5);
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(errorLabel, gc);
		
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstNameData 	= firstNameField.getText().trim();
				String lastNameData 	= lastNameField.getText().trim();
				String emailData 		= emailField.getText().trim();
				String passwordData 	= new String(passwordField.getPassword());
				
				User user = new User(firstNameData, lastNameData, emailData, passwordData);
				
				app.setSignUpListener(new SignUpErrorListener() {
					public void sendError(String errorMessage) {
						errorLabel.setText(errorMessage);
					}
				});
				
				if(app.signup(user))
				{
					setVisible(false);
				}else
				{
					errorLabel.setText("Invalid Email or Password");
				}
			}
		});
		
		
		 
	}
 
	public void setGridCell(int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
	}
}
