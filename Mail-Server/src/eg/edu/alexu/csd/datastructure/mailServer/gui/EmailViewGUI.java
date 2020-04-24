package eg.edu.alexu.csd.datastructure.mailServer.gui;

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
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		add(optionsPanel);
		add(textArea);
		add(receiversPanel);
		
		
		
	}
	
	
	
	
	private class OptionsPanel extends JPanel{
		public OptionsPanel(){
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.NONE;

			
			
			senderEmailLabel = new JLabel(senderEmail);
			addReceiverBtn = new JButton("Add receiver");
			sendBtn = new JButton("Send");
			receiversTextArea = new JTextArea();
			addReceiverField = new JTextField(50);
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
						receiversPanel.add(new Receiver(receiverEmail));
						revalidate();
					}
					
				}
			});
			
			sendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
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
					receiversPanel.remove(me);
					revalidate();
				}
			});
		}
	}
	
	public class ReceiversPanel extends JPanel{
		
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
