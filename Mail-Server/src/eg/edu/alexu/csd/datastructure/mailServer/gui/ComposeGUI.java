package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import eg.edu.alexu.csd.datastructure.queue.cs.LinkedBasedQueue;
import interfaces.IFolder;

public class ComposeGUI extends JFrame {
	//TODO choose sender from emails
	//TODO choose receivers from contacts
	
	JLabel senderEmailLabel;
	
	JLabel receiverLabel;
	JLabel receiverError;
	
	JLabel attachmentsLabel;
	JLabel attachmentError;
	
	JLabel subjectLabel;
	
	
	
	JTextArea textArea;
	JTextField addReceiverField;
	JTextField subjectField;
	
	
	
	JButton addReceiverBtn;
	JButton addAttachmentBtn;
	JButton sendBtn;
	
	ElementsBox receiversBox;
	ElementsBox attachmentsBox;
	BottomPanel bottomPanel;  //contains both boxes
	

	
	OptionsPanel optionsPanel;
	
	User user;
	String senderEmail;
	SinglyLinked receivers; //String
	SinglyLinked attachments; //String
	
	public ComposeGUI(String senderEmail, SinglyLinked receivers) {
		super("Compose E-mail");
		this.senderEmail = senderEmail;
		this.user = FolderManagerBIN.getUser(senderEmail);
		
		this.receivers = receivers;
		attachments = new SinglyLinked();
		
		
		setSize(600,800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//TODO save draft, we will change the action here when he pree exit so we can save drafts
		setVisible(true);
		
		
		optionsPanel = new OptionsPanel();
		optionsPanel.setPreferredSize(new Dimension(0, 150));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		bottomPanel = new BottomPanel();
		
		setLayout(new BorderLayout());
		add(optionsPanel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

	}
	
	
	
	
	private class OptionsPanel extends JPanel{
		public OptionsPanel(){
			setPreferredSize(new Dimension(100,100));
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.NONE;

			senderEmailLabel = new JLabel(senderEmail);
			
			receiverLabel = new JLabel("Reciever:");
			addReceiverField = new JTextField(senderEmail);
			addReceiverField.setMinimumSize(new Dimension(200,30));
			addReceiverBtn = new JButton("Add receiver");
			receiverError = new JLabel("");
			receiverError.setForeground(Color.RED);
			
			
			attachmentsLabel = new JLabel("Attachments");
			addAttachmentBtn = new JButton("Browse Attachment");
			attachmentError = new JLabel("");
			attachmentError.setForeground(Color.RED);
			
			subjectLabel = new JLabel("Subject:");
			subjectField = new JTextField(25);
			subjectField.setMinimumSize(new Dimension(200, 25));
			
			sendBtn = new JButton("Send");
			
			

			
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
			add(receiverLabel, gc);
			
			setGC(gc,1,1,1,1);
			gc.anchor = GridBagConstraints.LINE_START;
			add(addReceiverField, gc);
			
			setGC(gc,2,1,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(addReceiverBtn, gc);
			
			setGC(gc,3,1,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(receiverError, gc);
			
			//Third Row
			setGC(gc,0,2,1,1);
			gc.anchor = GridBagConstraints.LINE_END;
			add(attachmentsLabel, gc);
		
			setGC(gc,1,2,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(addAttachmentBtn, gc);
			
			setGC(gc,2,2,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(attachmentError, gc);
			
			//Fourth row
			setGC(gc, 0,3,1,1);
			add(subjectLabel, gc);
			
			setGC(gc, 1,3,1,1);
			add(subjectField, gc);
			
			//FiftthRow
			setGC(gc,0,4,3,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(sendBtn, gc);
			
			
			
			addReceiverBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String receiverEmail = addReceiverField.getText();
					FolderManagerBIN.printUsers();
					if (FolderManagerBIN.getUser(receiverEmail) != null) {
						if (receiversBox.Add(receiverEmail)) { //returns true if Adding is successful
							receiverError.setText("");
						}
					}
					else {
						
						receiverError.setText("Email doesn't exist");
					}
					
				}
			});
			
			addAttachmentBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FileChooser.Run(new PathListener() {
						public void pathChosen(String path) {
							attachmentsBox.Add(path);
						}
					});	
				}
			});
			
			
			sendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					LinkedBasedQueue q = ListUtils.singleToQueue(receivers);
					
					while(!q.isEmpty()) {
						String receiver = (String) q.dequeue();
						User receiverUser = FolderManagerBIN.getUser(receiver);
						Email email = new Email(subjectField.getText(),
								textArea.getText(),
								user.getID(),
								senderEmail,
								receiverUser.getID(),
								receiver,
								attachments,
								0 //place holder priority
								);
						email.saveEmail(receiverUser.getID(),(IFolder) new Folder("inbox"));
					}
					
					//Save it in sent for sender
					Email email = new Email(subjectField.getText(),
							textArea.getText(),
							user.getID(),
							senderEmail,
							user.getID(),
							senderEmail,
							attachments,
							0 //place holder priority
							);
					email.saveEmail(user.getID(),(IFolder) new Folder("sent"));
				}
			});
			
		}
	}
	
	
	

	
	

	
	
	public class BottomPanel extends JPanel{
		public BottomPanel(){
			setPreferredSize(new Dimension(200,200));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			
			receiversBox = new ElementsBox(receivers, "Receivers", receiverError);
			attachmentsBox = new ElementsBox(attachments, "Attachments", attachmentError);
			
			JScrollPane scroll1 = new JScrollPane(receiversBox);
			JScrollPane scroll2 = new JScrollPane(attachmentsBox);
			
			add(scroll1, BorderLayout.WEST);
			add(scroll2, BorderLayout.EAST);
		}
	}
	
	
	/**
	 * 
	 * @param user
	 * @param doubleLinkedList  
	 * If no receivers are ready, pass a new DoublyLinkedList()
	 */
	
	public static void Run(String senderEmail, SinglyLinked receivers) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new ComposeGUI(senderEmail, receivers);
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
