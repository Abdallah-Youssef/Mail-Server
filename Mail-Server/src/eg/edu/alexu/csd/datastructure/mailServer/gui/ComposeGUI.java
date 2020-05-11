package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.SinglyLinked;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.QueueLinkedBased;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import interfaces.IFolder;
import listeners.EmailChooserListener;
import listeners.PathListener;

public class ComposeGUI extends JFrame {
	//TODO choose sender from emails
	//TODO choose receivers from contacts
	
	DropDownMenuButton senderButton;
	JPopupMenu sendersMenu;
	JLabel senderError;
	
	JLabel receiverLabel;
	JLabel receiverError;
	
	JLabel attachmentsLabel;
	JLabel attachmentError;
	
	JLabel subjectLabel;
	
	JButton SaveDraft ;
	JButton LoadDraft ;
	
	static JTextArea textArea;
	JTextField addReceiverField;
	static JTextField subjectField;
	
	
	
	JButton addReceiverBtn;
	JButton chooseContactsBtn;
	JButton addAttachmentBtn;
	JButton sendBtn;
	
	ElementsBox receiversBox;
	static ElementsBox attachmentsBox;
	BottomPanel bottomPanel;  //contains boxes
	

	
	OptionsPanel optionsPanel;
	
	User user;
	SinglyLinked receivers; //String
	static SinglyLinked attachments; //String
	
	
	public ComposeGUI(User user, SinglyLinked receivers) {
		super("Compose E-mail");
		this.user = user;
		
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

			//senderEmailLabel = new JLabel(senderEmail);
			sendersMenu = new JPopupMenu();
			senderButton = new DropDownMenuButton("Choose Sender", sendersMenu);
			senderError = new JLabel("");
			senderError.setForeground(Color.RED);
			
			receiverLabel = new JLabel("Reciever:");
			addReceiverField = new JTextField("Type email");
			addReceiverField.setMinimumSize(new Dimension(200,30));
			addReceiverBtn = new JButton("Add receiver");
			chooseContactsBtn = new JButton("Choose From Contacts");
			receiverError = new JLabel("");
			receiverError.setForeground(Color.RED);
			
			SaveDraft=new JButton("Save as Draft");
			LoadDraft=new JButton("Load Draft");
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
			add(senderButton, gc);
			setGC(gc,2,0,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(senderError, gc);
			
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
			
			setGC(gc,4,1,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(chooseContactsBtn, gc);
			
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
			setGC(gc,0,4,2,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(sendBtn, gc);
			
			setGC(gc,2,4,2,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(SaveDraft, gc);
			
			setGC(gc,3,4,2,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(LoadDraft, gc);
			
			
			LoadDraft.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LOadDraftGUI.Run(user);
				}
				
			});
			

			SaveDraft.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String sender = senderButton.getText();
					if ((senderButton.getText()).equals("Choose Sender")) {
						senderError.setText("Choose sender");
						return;
					}
					senderError.setText("");
					
					QueueLinkedBased q = ListUtils.singleToQueue(receivers);
					while(!q.isEmpty()) {
						String receiver = (String) q.dequeue();
						User receiverUser = FolderManagerBIN.getUser(receiver);
						Email email = new Email(subjectField.getText(),
								textArea.getText(),
								user.getID(),
								sender,
								receiverUser.getID(),
								receiver,
								attachments,
								0 //place holder priority
								);
						email.saveEmail(user.getID(),(IFolder) new Folder("Draft"));
					
				}
				}
				});
			
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
			
			chooseContactsBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DoubleLinkedList contacts = new DoubleLinkedList();
					DoubleLinkedList contactEmails = user.getContacts();
					
					for (int i = 0;i < contactEmails.size();i++) 
						contacts.add(FolderManagerBIN.getUser(((String)contactEmails.get(i))));
					

					EmailChooser.Run(contacts, new EmailChooserListener() {
						public void emailsSelected(DoubleLinkedList emails) {
							receiversBox.DeleteAll();
							receivers = ListUtils.doubleToSingleList(emails);
							
							for (int i = 0;i < receivers.size();i++) {
								System.out.print(receivers.get(i) + " ");
								receiversBox.Add((String) receivers.get(i));
							}
							System.out.println();
						}
					});
					
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
					String sender = senderButton.getText();
					if ((senderButton.getText()).equals("Choose Sender")) {
						senderError.setText("Choose sender");
						return;
					}
					senderError.setText("");
					
					QueueLinkedBased q = ListUtils.singleToQueue(receivers);
					while(!q.isEmpty()) {
						String receiver = (String) q.dequeue();
						User receiverUser = FolderManagerBIN.getUser(receiver);
						Email email = new Email(subjectField.getText(),
								textArea.getText(),
								user.getID(),
								sender,
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
							sender,
							user.getID(),
							sender,
							attachments,
							0 //place holder priority
							);
					email.saveEmail(user.getID(),(IFolder) new Folder("sent"));
					
				}
			});
			
			senderButton.setPopupListener(new PopupMenuListener() {
	            @Override
	            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
	            	senderButton.popup.removeAll();
	            	DoubleLinkedList emails = user.getEmails();
	    			for (int i = 0;i < emails.size();i++) {
	    				//Make a menuItem for each email
	    				//each menuItem when pressed will change the text of the JMenu to its email
	    				
	    				JMenuItem email = new JMenuItem((String)emails.get(i));
	    				email.addActionListener(new ActionListener() {
	    					public void actionPerformed(ActionEvent e) {
	    						senderButton.setName(email.getText());
	    					}
	    				});
	    				senderButton.popup.add(email);
	    			}
	            }
	            @Override
	            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	            	senderButton.setSelected(false);
	            }
	            @Override
	            public void popupMenuCanceled(PopupMenuEvent e) {}
	        });
			
		
		}
	}
	
	public static void draftLoading(Email mail) {
		
		attachments=mail.getAttachments();
		for(int i=0;i<attachments.size();i++) {
		attachmentsBox.Add((String)attachments.get(i));
		}
		textArea.setText(mail.getBody());
		subjectField.setText(mail.getSubject());
	}
	

	
	

	
	
	public class BottomPanel extends JPanel{
		
		public BottomPanel(){
			setPreferredSize(new Dimension(200,200));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			

			receiversBox = new ElementsBox(receivers, "Receivers", receiverError);
			attachmentsBox = new ElementsBox(attachments, "Attachments", attachmentError);
			
			
			
			JScrollPane scroll1 = new JScrollPane(receiversBox);
			JScrollPane scroll2 = new JScrollPane(attachmentsBox);
			
			add(scroll1);
			add(scroll2);
		}
	}
	
	

	
	
	/**
	 * 
	 * @param user
	 * @param doubleLinkedList  
	 * If no receivers are ready, pass a new DoublyLinkedList()
	 */
	
	public static void Run(User user, SinglyLinked receivers) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new ComposeGUI(user, receivers);
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
