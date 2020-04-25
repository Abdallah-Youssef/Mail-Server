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
import eg.edu.alexu.csd.datastructure.mailServer.FolderManagerBIN;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EmailViewGUI extends JFrame {
	JLabel senderEmailLabel;
	JButton addReceiverBtn;
	JButton addAttachmentBtn;
	JButton sendBtn;
	JTextArea textArea;
	JTextField addReceiverField;
	JTextField addAttachmentField;
	JTextArea receiversTextArea;
	
	
	ElementsBox receiversPanel;
	ElementsBox attachmentsPanel;
	BottomPanel bottomPanel;
	

	
	OptionsPanel optionsPanel;
	
	User sender;
	String senderEmail;
	DoublyLinkedList receivers;
	DoublyLinkedList attachments;
	String testReceiver = "jk";
	
	public EmailViewGUI(String senderEmail, DoublyLinkedList receivers) {
		super("Compose E-mail");
		this.senderEmail = senderEmail;
		this.sender = FolderManagerBIN.getUser(senderEmail);
		this.receivers = receivers;
		attachments = new DoublyLinkedList();
		
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		optionsPanel = new OptionsPanel();
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		receiversTextArea = new JTextArea();
		receiversTextArea.setPreferredSize(new Dimension(200,200));
		receiversTextArea.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
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
			addReceiverBtn = new JButton("Add receiver");
			addAttachmentBtn = new JButton("Browse Attachment");
			
			sendBtn = new JButton("Send");
			receiversTextArea = new JTextArea();
			addReceiverField = new JTextField("koskos@zobzob.com");
			addReceiverField.setMinimumSize(new Dimension(200,25));
			
			addAttachmentField = new JTextField("skakalansshikoshiko");
			addAttachmentField.setMinimumSize(new Dimension(200,25));
			
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
			
			//Third Row
			setGC(gc,0,2,1,1);
			gc.anchor = GridBagConstraints.LINE_END;
			add(new JLabel("Attachments : "), gc);
			
			setGC(gc,1,2,1,1);
			gc.anchor = GridBagConstraints.LINE_START;
			add(addAttachmentField, gc);
			
			setGC(gc,2,2,1,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(addAttachmentBtn, gc);
			
			//FourthRow
			setGC(gc,0,3,3,1);
			gc.anchor = GridBagConstraints.CENTER;
			add(sendBtn, gc);
			
			
			
			addReceiverBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * Maybe open a pop up that returns an email?
					 */
					String receiverEmail = addReceiverField.getText();
					if (FolderManagerBIN.getUser(receiverEmail) != null) {
						receiversPanel.Add(receiverEmail);
					}
					
				}
			});
			
			addAttachmentBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * Maybe open a pop up that returns an email?
					 */
					String path = addAttachmentField.getText();
					//TODO check path
					attachmentsPanel.Add(path);
					
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
					parent.Delete(me);
				}
			});
		}
	}
	

	public class ElementsBox extends JPanel{
		DoublyLinkedList elements;
		JLabel emptyLabel;
		/**
		 * 
		 * @param elements, the list of elements it will manage
		 * 
		 */
		public ElementsBox(DoublyLinkedList elements, String Label) {
			setMinimumSize(new Dimension(200,200));
			this.elements = elements;
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(BorderFactory.createLineBorder(Color.black));
			
			JLabel label = new JLabel(Label);
			label.setAlignmentX(CENTER_ALIGNMENT);
			add(label);
		}
		
		public void Add(String string) {
			elements.add(string);
			add(new Element(string, this));
			revalidate();
			
			
			elements.print();
		}
		
		public void Delete(Element element) {
			//Remove component
			System.out.println("nooooooooooooo");
			element.setVisible(false);
			remove(element);
			revalidate();
			
			//REmove from list
			for(int i = 0;i < elements.size();i++)
				if ((element.label.getText()).equals((String)elements.get(i))) {
					elements.remove(i);
					break;
				}
			
			elements.print();
		}
		
	}
	

	
	
	public class BottomPanel extends JPanel{
		public BottomPanel(){
			setPreferredSize(new Dimension(200,200));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			
			
			
			/*JLabel attachmentsLabel = new JLabel(":Attachments:");
			attachmentsLabel.setAlignmentX(CENTER_ALIGNMENT);
			
			JLabel receiversLabel = new JLabel(":Receivers:");
			receiversLabel.setAlignmentX(CENTER_ALIGNMENT);*/
			

			
			
			//Bottom
			receiversPanel = new ElementsBox(receivers, "Receivers");
			attachmentsPanel = new ElementsBox(attachments, "Attachments");
			

			
			JScrollPane scroll1 = new JScrollPane(receiversPanel);
			JScrollPane scroll2 = new JScrollPane(attachmentsPanel);
			
			add(scroll1, BorderLayout.WEST);
			add(scroll2, BorderLayout.EAST);
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
