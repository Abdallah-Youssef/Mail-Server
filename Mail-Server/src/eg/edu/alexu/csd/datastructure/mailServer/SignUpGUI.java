package eg.edu.alexu.csd.datastructure.mailServer;

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

import org.json.simple.JSONArray;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;
public class SignUpGUI extends JFrame {
	GridBagConstraints gc;
	JLabel firstName, lastName;
	JLabel email, password;
	JLabel errorLabel;
	String errorString = "";
	
	JTextField firstNameField, lastNameField;
	JTextField emailField;
	JPasswordField passwordField;
	
	JButton btn;
	
	public SignUpGUI () {
		super("Sign Up");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		////Creating components
		firstName = new JLabel("First Name : ");
		lastName = new JLabel("Last Name : ");
		email = new JLabel("Email : ");
		password = new JLabel("Password : ");
		errorLabel = new JLabel("");
		
		firstNameField = new JTextField(25);
		lastNameField = new JTextField(25);
		emailField = new JTextField(25);
		passwordField = new JPasswordField(25);
		btn = new JButton("Sign Up");
		
		
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
		
		
		JSONArray users = FolderManager.getUsers();
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				for each possible error => errorString += error + "\n"
				
				after all checks:
				if success => errorString = "";
				
				errorLabel.setText(errorString);
				*/
				String firstNameData = firstNameField.getText().trim();
				String lastNameData = lastNameField.getText().trim();
				String emailData = emailField.getText().trim();
				String passwordData = passwordField.toString();
				
				if(!emailData.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || 
						FolderManager.isUserExist(users, emailData))
				{
					//generateError
				}else if(passwordData.length() < 8)
				{
					//generate error
				}
				else
				{
					User newUser = new User(firstNameData, lastNameData, emailData, passwordData);
					FolderManager.addJSONUser(users, FolderManager.createUserJSONObject(newUser));
					
				}
			}
		});
		
		
		 
	}
 
	public void setGridCell(int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
	}
}
