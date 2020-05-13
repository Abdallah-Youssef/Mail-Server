package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dataStructures.DoubleLinkedList;
import dataStructures.SinglyLinked;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import listeners.PathListener;

public class EmailView extends JFrame {
	PathListener listener;
	JLabel senderLabel;
	JLabel receiverLabel;
	JLabel dateLabel;
	JLabel subjectLabel;
	JTextArea body;
	GridBagConstraints gc;
	
	Box details;
	Box main;
	Box attachmentsBox;
	
	
	public EmailView (Email email, PathListener listener) {
		this.listener = listener;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		senderLabel = new JLabel("Sender : " + email.getSender());
		senderLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		senderLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		receiverLabel = new JLabel("Receiver : " + email.getReceiver());
		receiverLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		receiverLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		dateLabel = new JLabel("Date : " + email.getDate());
		dateLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		dateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		
		subjectLabel = new JLabel(email.getSubject());
		subjectLabel.setFont(new Font("Serif", Font.BOLD, 23));
		subjectLabel.setAlignmentX(CENTER_ALIGNMENT);
		subjectLabel.setForeground(Color.BLUE);
		
		
		body = new JTextArea(email.getBody());
		body.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		body.setEditable(false);
		
		details = new Box(BoxLayout.Y_AXIS);
		main = new Box(BoxLayout.Y_AXIS);
		attachmentsBox = new Box(BoxLayout.Y_AXIS);
		
		
		details.add(senderLabel);
		details.add(receiverLabel);
		details.add(dateLabel);
		
		main.add(subjectLabel);
		main.add(body);
		
		SinglyLinked attachments = email.getAttachments();
		for (int i = 0;i < attachments.size();i++) {
			JButton btn = new JButton((String)attachments.get(i));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.pathChosen(btn.getText());
				}
			});
			attachmentsBox.add(btn);
		}
		
		
		add(details);
		add(main);
		add(body);
		add(attachmentsBox);

	}


	
	public static void run(Email email, PathListener listener) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EmailView(email, listener);
			}
		});
	}
	
}
