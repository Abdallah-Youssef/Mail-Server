package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EmailViewGUI extends JFrame {
	JLabel senderEmailLabel;
	JButton addReceiverBtn;
	JButton sendBtn;
	JTextArea textArea;
	JTextField addReceiverField;
	JTextArea receiversTextArea;
	ReceiversPanel receiversPanel;
	
	

	JScrollPane scroll;
	
	OptionsPanel optionsPanel;
	
	User sender;
	String senderEmail;
	DoublyLinkedList receivers;
	String testReceiver = "jk";
	
	public EmailViewGUI(String senderEmail, DoublyLinkedList receivers) {
		super("Compose E-mail");
		this.senderEmail = senderEmail;
		this.sender = FolderManagerBIN.getUser(senderEmail);
		this.receivers = receivers;
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		optionsPanel = new OptionsPanel();
		textArea = new JTextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		receiversTextArea = new JTextArea();
		receiversTextArea.setPreferredSize(new Dimension(200,200));
		receiversTextArea.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		receiversPanel = new ReceiversPanel();
		scroll = new JScrollPane(receiversPanel);
		scroll.setPreferredSize(new Dimension(0,200));
		
		//setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		add(optionsPanel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(scroll, BorderLayout.SOUTH);
		
		
		
	}
	
	
	
	
	private class OptionsPanel extends JPanel{
		public OptionsPanel(){
			setPreferredSize(new Dimension(100,100));
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.NONE;

			
			
			senderEmailLabel = new JLabel(senderEmail);
			addReceiverBtn = new JButton("Add receiver");
			sendBtn = new JButton("Send");
			receiversTextArea = new JTextArea();
			addReceiverField = new JTextField("koskos@zobzob.com");
			addReceiverField.setMinimumSize(new Dimension(200,25));
			
			//FirstRow
			setGC(gc,0,0,1,1);
			gc.anchor = GridBagConstraints.LINE_END;
			add(new JLabel("Sender : "), gc);
			setGC(gc,1,0,1,1);
			gc.anchor = GridBagConstraints.LINE_START;
			add(senderEmailLabel, gc);
			
			//Second Row
			setGC(gc,0,1,1,1);
			gc.anchor = GridBagConstraints.LINE_END;
			add(new JLabel("Recievers : "), gc);
			
			setGC(gc,1,1,1,1);
			gc.anchor = GridBagConstraints.LINE_START;
			add(addReceiverField, gc);
			
			setGC(gc,2,1,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(addReceiverBtn, gc);
			
			
			//ThirdRow
			setGC(gc,0,2,2,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(sendBtn, gc);
			
			
			
			addReceiverBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * Maybe open a pop up that returns an email?
					 */
					String receiverEmail = addReceiverField.getText();
					if (FolderManagerBIN.getUser(receiverEmail) != null) {
						receivers.add(receiverEmail);
						receiversPanel.add(new Receiver(receiverEmail));
						revalidate();
						
						receivers.print();
					}
					
				}
			});
			
			sendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO create Email object
					//Add the email to the receivers' folders
					//Use IApp function
					//Available info: 
					// receivers (DoublyLinkedList of Strings)
					// senderEmail
					// textArea.getText()
				}
			});
			
		}
	}
	
	public class Receiver extends JPanel{
		Receiver me = this;
		
		public Receiver(String email) {
			JLabel label = new JLabel(email);
			label.setFont(new Font("Serif", Font.PLAIN, 20));
			JButton delete = new JButton("X");
			setLayout(new FlowLayout());
			add(label);
			add(delete);
			
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					receiversPanel.remove(me);
					receiversPanel.revalidate();
					
					for(int i = 0;i < receivers.size();i++)
						if (label.getText().equals((String)receivers.get(i))) {
							receivers.remove(i);
							break;
						}
					
					receivers.print();
				}
			});
		}
	}
	
	public class ReceiversPanel extends JPanel{
		public ReceiversPanel() {
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			JLabel receiversLabel = new JLabel(":Receivers:");
			receiversLabel.setAlignmentX(CENTER_ALIGNMENT);
			add(receiversLabel);
		}
	}
	
	
	
	/**
	 * 
	 * @param user
	 * @param receivers  
	 * If no receivers are ready, pass a new DoublyLinkedList()
	 */
	public static void Run(String senderEmail, DoublyLinkedList receivers) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EmailViewGUI(senderEmail, receivers);
			}
		});
	}
	
	private void setGC(GridBagConstraints gc, int x, int y, int width, int height) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
	}
	
}
