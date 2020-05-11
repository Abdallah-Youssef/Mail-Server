package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import listeners.PathListener;

public class EMailsPanel extends JPanel {
	JFrame parentFrame = (JFrame) this.getParent();
	User user;
	//public boolean[] checkedEmails;
	public boolean[] newCheckedEmails;
	int currentPage;
	public EMailsPanel(Email[] emails, User user, boolean[] checkedEmailsBoxes, int page) {
		
		setPreferredSize(new Dimension(500,400));
		EMailsBox box = new EMailsBox();
		add(box);
		
		
		this.user = user;
		//checkedEmails = new boolean[emails.length];
		newCheckedEmails = checkedEmailsBoxes;
		currentPage = page;
		
		
		//create a panel for each email
		for (int i = 0;i < emails.length && emails[i] != null;i++) {
			box.Add(emails[i], i);
		}
		
		
	}
	
	private class EMailsBox extends JPanel{
		EMailsBox(){
			setLayout(new GridLayout(10,1,20,2));
		}
		
		public void Add(Email email, int index) {
			add(new EMailPanel(email, index));
		}
	}
	
	private class EMailPanel extends JPanel{
		GridBagConstraints gc;
		JButton button;
		JCheckBox checkBox;
		
		Email email;
		int index;
		
		EMailPanel(Email email, int index){
			this.email = email;
			this.index = index;
		
			button = new JButton(email.getSubject() + " - " + email.getSender());
			if (button.getText().length() > 30) {
				button.setText(button.getText().substring(0, 30) + "...");
			}
			button.setBackground(Color.WHITE);
			button.setFont(new Font("Arial", Font.PLAIN, 20));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(BorderFactory.createEmptyBorder(7, 20, 7, 250));
			
			
			checkBox = new JCheckBox();
		    checkBox.setMnemonic(KeyEvent.VK_C); 
		    System.out.println("INDEX: "+(10*currentPage + index));
		    if(newCheckedEmails != null)
		    	checkBox.setSelected(newCheckedEmails[10*currentPage + index]);
		    else
		    	checkBox.setSelected(false);
			
			setLayout(new GridBagLayout());
			gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.NONE;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			
			gc.gridx = 0;
			add(checkBox, gc);
			
			
			gc.gridx = 1;
			add(button, gc);
			
			
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
			        boolean selected = abstractButton.getModel().isSelected();
			        //checkedEmails[index] = selected;
			        newCheckedEmails[10*currentPage + index] = selected;
				}
			});
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EmailView.run(email, new PathListener() {
						public void pathChosen(String path) {
							//TODO for youssef : open the path
							try {
								ViewingAttachment.ViewingAttachment(path);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			});
		}
		
		
		
	}


	

}
