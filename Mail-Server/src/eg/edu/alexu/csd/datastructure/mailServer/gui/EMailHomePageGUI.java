package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EMailHomePageGUI extends JFrame {
		JFrame frame = this;
		//declerations
		/*GridBagConstraints GC;
		private GridBagLayout gridBagLayout = new GridBagLayout();*/
		
		NavigationPanel navigationPanel;
		MenuBar menuBar;
		EMailsPanel emailsPanel;
		JScrollPane scroll;
		
		EmailsPanelListener emailsPanelListener;
		
		public EMailHomePageGUI(User user){
			super("Welcome user number : " + user.getID());
			setResizable(false);
			setSize(800,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setVisible(true);
			
			
			
			navigationPanel = new NavigationPanel(user);
			menuBar = new MenuBar();
			
			DoubleLinkedList initialMails = Email.readUserEmails(user.getID(), new Folder("inbox"));
			emailsPanel = new EMailsPanel(ListUtils.getPage(initialMails, 1),user);
			scroll = new JScrollPane(emailsPanel);
			//Layout
			Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			Border insideBorder = BorderFactory.createTitledBorder("Main Page : "  );
			getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

			setLayout(new BorderLayout());

			add(navigationPanel, BorderLayout.WEST);
			
			setJMenuBar(menuBar);
	
			add(scroll, BorderLayout.CENTER);
			
			
			//Listener for emailsPanel
			emailsPanelListener = new EmailsPanelListener() {
				public void newEmails(eg.edu.alexu.csd.datastructure.mailServer.Email[] emails) {
					frame.remove(scroll);
					emailsPanel = new EMailsPanel(emails, user);
					scroll = new JScrollPane(emailsPanel);
					add(scroll, BorderLayout.CENTER);
					revalidate();
				}
			};
			navigationPanel.setListener(emailsPanelListener);
			
			
			
			
			

		}
		
		
		public static void run(User user) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EMailHomePageGUI(user);
			}
		});
	}
}