package eg.edu.alexu.csd.datastructure.mailServer.gui;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import interfaces.IFolder;

public class LOadDraftGUI extends JFrame {
	public GridBagLayout gridBagLayout = new GridBagLayout();
	JPanel panel;
	GridBagConstraints GC;
	public LOadDraftGUI(User user) {
	setSize(600,600);
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);	 
	Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
	Border insideBorder = BorderFactory.createTitledBorder("Welcome");
	getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
	setLayout(new GridBagLayout());
	GC=new GridBagConstraints();
	GC.weightx =1 ;
	GC.weighty =1;
	GC.fill = GridBagConstraints.NONE;	
	panel=new JPanel();
	DoubleLinkedList drafts=Email.readUserEmails(user.getID(), (IFolder)new Folder("Draft"));
	adding(drafts);
	JScrollPane scroll1=new JScrollPane(panel);
	add(scroll1,GC);
	
	
	
}
	public void adding(DoubleLinkedList drafts) {
		for(int i=0;i<drafts.size();i++) {
			Email mail=(Email) drafts.get(i);
			JButton button =new JButton(mail.getSubject());
			button.setBackground(Color.WHITE);
			button.setFont(new Font("Arial", Font.PLAIN, 20));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorder(BorderFactory.createEmptyBorder(7, 20, 7, 250));
			panel.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ComposeGUI.draftLoading(mail);
					
				}
				
			});
		}
	}
	public static void Run(User user) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LOadDraftGUI(user);
			}
		});
	}
}
