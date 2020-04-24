package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.json.simple.JSONObject;

import eg.edu.alexu.csd.datastructure.mailServer.FolderManager;

public class LoginGUI extends JFrame{
	//decleration
	JSONObject user;
	JLabel emailErrorMessage, passwordErrorMessage;
	GridBagConstraints GC;
	JButton log_in_btn;
	JButton sign_up_btn;
	JLabel email,password,SignUpMsg;
	JTextField emailField;
	JPasswordField passwordField;
	
	SignUpPanel signupPanel;
	public LoginGUI() {
		super("Log IN");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//components
		emailErrorMessage = new JLabel("");
		passwordErrorMessage = new JLabel("");
		email=new JLabel("E-Mail : ");
		password=new JLabel("Password : ");
		SignUpMsg=new JLabel("Don't have an E-Mail ?  ");
		emailField= new JTextField(25);
		passwordField=new JPasswordField(25);
		log_in_btn=new JButton("Log In");
		sign_up_btn=new JButton("Sign Up");
		signupPanel = new SignUpPanel();
		
		Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Log In : ");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		setLayout(new GridBagLayout());
		GC=new GridBagConstraints();
		GC.weightx = 1;
		GC.weighty = 1;
		GC.fill = GridBagConstraints.NONE;
		//griding
		//first row
		setGridAxes(0,0);	
		GC.anchor = GridBagConstraints.LINE_END;
		add(email,GC);
		setGridAxes(1,0);
		GC.anchor=GridBagConstraints.LINE_START;
		add(emailField,GC);
		
		//2nd
		setGridAxes(0,1);
		GC.gridwidth = 2;
		GC.anchor = GridBagConstraints.CENTER;
		add(emailErrorMessage, GC);
		
		GC.gridwidth = 1;
		//3rd row
		setGridAxes(0,2);	
		GC.anchor = GridBagConstraints.LINE_END;
		add(password,GC);
		setGridAxes(1,2);
		GC.anchor=GridBagConstraints.LINE_START;
		add(passwordField,GC);
		
		//4th
		setGridAxes(0,3);
		GC.gridwidth = 2;
		GC.anchor = GridBagConstraints.CENTER;
		add(passwordErrorMessage, GC);
		
		GC.gridwidth = 1;
		//5th row
		setGridAxes(0,4);
		GC.gridwidth=2;
		GC.anchor=GridBagConstraints.CENTER;
		add(log_in_btn,GC);
		
		GC.gridwidth=1;
		//6thRow
		setGridAxes(0,5);
		GC.gridwidth=2;
		GC.anchor=GridBagConstraints.CENTER;
		add(signupPanel, GC);
		
		
		
		
		sign_up_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpGUI.run();
				setVisible(false);
				dispose();
			}
		});

		log_in_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FolderManager.printUsers();
				/*we check if the email exists if not error msg email
				doesn't exist
				else check if the password is right
				if not error msg password is wrong
				*/
				String email;
				String password;
				email = emailField.getText();
				password = new String(passwordField.getPassword());
				
				//TODO CHECK EMAIL FORMAT
				if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					emailErrorMessage.setText("Please enter a valid email address");
					return;
				}
				else if(FolderManager.getUser(email)==null) {
					emailErrorMessage.setText("User doesn't exist");
					return;
				}
				else {
					user = FolderManager.getUser(email);
				}
				if (password.contentEquals("")) {
					passwordErrorMessage.setText("Please enter a password");
					return;
				}
				else if(!user.get("password").equals(password)) {
					passwordErrorMessage.setText("Wrong password");
				}
				else {
					passwordErrorMessage.setText("");
					/*
					 * call the GUI for USer emails and his s**t
					 */
					System.out.println("LOGIN SUCCESS");
				}
					
					
			}
			
		});
	}
	private void setGridAxes(int x,int y) {
		GC.gridx=x;
		GC.gridy=y;
	}
	
	
	private class SignUpPanel extends JPanel {
		SignUpPanel(){
			setLayout(new BorderLayout());
			add(SignUpMsg,BorderLayout.LINE_START);
			add(sign_up_btn,BorderLayout.LINE_END);
		}
	}
	
	public static void run() {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LoginGUI();
			}
		});
	}

}