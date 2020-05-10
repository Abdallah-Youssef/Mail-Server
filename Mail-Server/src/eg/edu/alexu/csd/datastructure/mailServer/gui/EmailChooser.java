package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Listeners.EmailChooserListener;
import Listeners.PathListener;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EmailChooser extends JFrame {
	boolean[] checkedEmails;
	String[] emails;
	EmailChooserListener emailChooserListener;
	
	//will count the total number of emails
	int counter = 0;
	
	/**
	 * @param users the users whose emails are going to be chosen from
	 */
	public EmailChooser(DoubleLinkedList users, EmailChooserListener listener) {
		super("Select Emails");
		setSize(100, 300);
		setVisible(true);
		this.emailChooserListener = listener;
		
		
		
		setLayout(new BorderLayout());
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.Y_AXIS));
		
		//Counting the emails and making their ui elements
		for (int i = 0;i < users.size();i++) {
			DoubleLinkedList emailList = ((User)users.get(i)).getEmails();			
			for (int j = 0;j < emailList.size();j++) {
				scrollPanel.add(new EMailPanel(((User)users.get(i)).firstName + " - " +  (String)emailList.get(j), counter++));
			}
		}
		
		JScrollPane scroll = new JScrollPane(scrollPanel);
		add(scroll, BorderLayout.CENTER);
		checkedEmails = new boolean[counter];
		emails = new String[counter];
		
		counter = 0;
		for (int i = 0;i < users.size();i++) {
			DoubleLinkedList emailList = ((User)users.get(i)).getEmails();			
			for (int j = 0;j < emailList.size();j++) {
				emails[counter++] = (String) emailList.get(j);
			}
		}
		
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	DoubleLinkedList returnEmails = new DoubleLinkedList();
		        for (int i = 0;i < checkedEmails.length;i++)
		        	if (checkedEmails[i])
		        		returnEmails.add(emails[i]);
		        emailChooserListener.emailsSelected(returnEmails);
		    }
		});
	}
	
	private class EMailPanel extends JPanel{
		GridBagConstraints gc;
		JCheckBox checkBox;
		JLabel label;
		
		
		EMailPanel(String email, int index){
			label = new JLabel(email);
			
			checkBox = new JCheckBox();
		    checkBox.setMnemonic(KeyEvent.VK_C); 
		    checkBox.setSelected(false);
			
			setLayout(new GridBagLayout());
			gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.NONE;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			
			gc.gridx = 0;
			add(checkBox, gc);
			
			
			gc.gridx = 1;
			add(label, gc);
			
			
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
			        boolean selected = abstractButton.getModel().isSelected();
			        checkedEmails[index] = selected;
			        
			        for (int i = 0;i < checkedEmails.length;i++)
			        	System.out.print(checkedEmails[i] + " ");
			        System.out.println();
				}
			});
		}
	}
	public static void Run(DoubleLinkedList users, EmailChooserListener listener) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            new EmailChooser(users, listener);
	        }
	    });
	}

}
