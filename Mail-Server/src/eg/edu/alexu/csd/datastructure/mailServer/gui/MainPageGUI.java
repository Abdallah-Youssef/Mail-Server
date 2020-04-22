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
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class MainPageGUI extends JFrame{
	//decleration
	String ErrorMsg="";
	GridBagConstraints GC;
	JButton log_in_btn;
	JButton sign_up_btn;
	JLabel email,password,SignUpMsg;
	JTextField emailField;
	JPasswordField passwordField;
	public MainPageGUI() {
		super("Log IN");
		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//componenets
		email=new JLabel("E-Mail : ");
		password=new JLabel("Password : ");
		SignUpMsg=new JLabel("Don't have an E-Mail ?  ");
		emailField= new JTextField(25);
		passwordField=new JPasswordField(25);
		log_in_btn=new JButton("Log In");
		sign_up_btn=new JButton("Sign Up");
		
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
		GC.anchor = GridBagConstraints.LINE_START;
		add(email,GC);
		setGridAxes(1,0);
		GC.anchor=GridBagConstraints.LINE_END;
		add(emailField,GC);
		//second row
		setGridAxes(0,1);	
		GC.anchor = GridBagConstraints.LINE_START;
		add(password,GC);
		setGridAxes(1,1);
		GC.anchor=GridBagConstraints.LINE_END;
		add(passwordField,GC);
		//3rd row
		setGridAxes(0,2);
		GC.gridwidth=2;
		GC.anchor=GridBagConstraints.CENTER;
		add(log_in_btn,GC);
		//4th row
		setGridAxes(0,3);	
		GC.anchor = GridBagConstraints.LINE_START;
		add(SignUpMsg,GC);
		setGridAxes(1,3);
		GC.gridwidth=3;
		GC.anchor=GridBagConstraints.CENTER;
		add(sign_up_btn,GC);
		
		sign_up_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingUtilities.invokeLater(new Runnable () {
					public void run() {
						new SignUpGUI();
					}
				});
				
			}
		});
		log_in_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*we check if the email exsist if not error msg email
				doesn't exist
				else check if the password is right
				if not error msg password is wrong
				*/
			}
			
		});
	}
	private void setGridAxes(int x,int y) {
		GC.gridx=x;
		GC.gridy=y;
	}

}
