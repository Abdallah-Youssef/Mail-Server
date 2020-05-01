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

import eg.edu.alexu.csd.datastructure.linkedList.cs.Classes.DoublyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EmailViewGUI extends JFrame {
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
	DoubleLinkedList receivers; //String
	DoubleLinkedList attachments; //String
	
	public EmailViewGUI(String senderEmail, DoubleLinkedList doubleLinkedList) {
		super("Compose E-mail");
		this.senderEmail = senderEmail;
		this.user = FolderManagerBIN.getUser(senderEmail);
		
		this.receivers = doubleLinkedList;
		attachments = new DoubleLinkedList();
		
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
			addReceiverField = new JTextField("koskos@zobzob.com");
			addReceiverField.setMinimumSize(new Dimension(200,25));
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
					if (FolderManagerBIN.getUser(receiverEmail) != null) {
						if (receiversBox.Add(receiverEmail)) { //returns true if Adding is successful
							receiverError.setText("");
					}
					
					}else {
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
					
					//TODO create Email object
					//Email email = new Email(name, name, flags, name, flags, name, flags, flags);
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
	
	public class Element extends JPanel{
		Element me = this;
		ElementsBox parent;
		JLabel label;
		public Element(String text, ElementsBox parent) {
			this.parent = parent;
			label = new JLabel(text);
			label.setFont(new Font("Serif", Font.PLAIN, 20));
			JButton delete = new JButton("X");
			setLayout(new FlowLayout());
			add(label);
			add(delete);
			
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.errorLabel.setText("");
					parent.Delete(me);
				}
			});
		}
	}
	

	public class ElementsBox extends JPanel{
		DoubleLinkedList elements;
		JLabel errorLabel;
		
		public ElementsBox(DoubleLinkedList attachments, String Label, JLabel errorLabel) {
			this.elements = attachments;
			this.errorLabel = errorLabel;
			
			setMinimumSize(new Dimension(200,200));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(BorderFactory.createLineBorder(Color.black));
			
			JLabel label = new JLabel(Label);
			label.setAlignmentX(CENTER_ALIGNMENT);
			add(label);
		}
		
		public boolean Add(String string) {
			//Check if it's not already exisiting in the list
			for (int i = 0;i < elements.size();i++) {
				if ( ((String)elements.get(i)) .equals(string)){
					errorLabel.setText("Element already included");
					return false;
				}
			}
			
			elements.add(string);
			errorLabel.setText("");
			add(new Element(string, this));
			revalidate();
			return true;
		}
		
		public void Delete(Element element) {
			//Remove component
			element.setVisible(false);
			remove(element);
			revalidate();
			
			for(int i = 0;i < elements.size();i++)
				if ((element.label.getText()).equals((String)elements.get(i))) {
					elements.remove(i);
					break;
				}
			
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
	
	public static void Run(String senderEmail, DoubleLinkedList doubleLinkedList) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EmailViewGUI(senderEmail, doubleLinkedList);
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
