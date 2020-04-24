package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import eg.edu.alexu.csd.datastructure.mailServer.User;

public class ComposeGUI extends JFrame {
	JLabel senderEmail;
	JButton addReceiver;
	JTextArea textArea;
	
	

	JScrollPane listScroller;
	
	OptionsPanel optionsPanel;
	ReceiversPanel receiversPanel;
	User sender;
	
	public ComposeGUI(User sender) {
		super("Compose E-mail");
		this.sender = sender;
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		optionsPanel = new OptionsPanel();
		textArea = new JTextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		
		setLayout(new BorderLayout());
		add(textArea, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.WEST);
		
	}
	
	
	
	
	private class OptionsPanel extends JPanel{
		public OptionsPanel(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			addReceiver = new JButton("Add receiver");
			receiversPanel = new ReceiversPanel();
			
			add(addReceiver);
			add(receiversPanel);
			
			
			addReceiver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listScroller.add(new Receiver("hello"));
					listScroller.add(new JLabel("hello"));
					revalidate();
				}
			});
			
		}
	}
	
	private class Receiver extends JPanel{
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
					receiversPanel.revalidate();
				}
			});
		}
	}
	
	
	private class ReceiversPanel extends JPanel{
		public ReceiversPanel() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(Box.createRigidArea(new Dimension(0,5)));
			
			listScroller = new JScrollPane();
			add(listScroller);
			setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		}
		
	}
	public static void run(User user) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new ComposeGUI(user);
			}
		});
	}
	
	
}
