package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Filter;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import eg.edu.alexu.csd.datastructure.mailServer.sortComparator;
import interfaces.IFilter;
import interfaces.IFolder;
import interfaces.IMail;
import listeners.EmailPanelUtilsListener;
import listeners.EmailsPanelListener;
import listeners.FilterSortChangeListener;
import listeners.FolderChangeListener;
import listeners.NewEmailListListener;

public class EMailHomePageGUI extends JFrame {
		JFrame frame = this;
		//declerations
		/*GridBagConstraints GC;
		private GridBagLayout gridBagLayout = new GridBagLayout();*/
		
		NavigationPanel navigationPanel;
		MenuBar menuBar;
		EMailsPanel emailsPanel;
		EmailPanelUtil emailPanelUtil;
		
		JScrollPane scroll;
		App app;
		User user;
		FolderChangeListener folderChangeListener;
		
		public EMailHomePageGUI(App app){
			super("Welcome  " + app.loggedInUser.firstName);
			user = app.loggedInUser;
			
			
			setResizable(false);
			setSize(800,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setVisible(true);
			this.app = app;
			
			
			navigationPanel = new NavigationPanel(app);
			menuBar = new MenuBar();
			
			
			//TODO get folders from user
			DoubleLinkedList folders = new DoubleLinkedList();
			folders.add("inbox");
			folders.add("sent");
			folders.add("trash");
			emailPanelUtil = new EmailPanelUtil(folders);
			
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
			add(emailPanelUtil, BorderLayout.SOUTH);
			
			navigationPanel.setListener(new FolderChangeListener() {
				public void newFolder(IFolder folder) {
					app.setViewingOptions(folder, app.filter, app.sort);
					
					refreshEmailsPanel(app.listEmails(0));
				}
			});
			
			menuBar.setListener(new FilterSortChangeListener() {
				public void filterChanged(Filter filter) {
					app.setViewingOptions(app.folder,(IFilter) filter, app.sort);
					refreshEmailsPanel(app.listEmails(0));
				}

				public void sortChanged(sortComparator sort) {
					app.setViewingOptions(app.folder, app.filter, sort);
					refreshEmailsPanel(app.listEmails(0));
				}
			});
			
			emailPanelUtil.setListener(new EmailPanelUtilsListener(){
				public void moveEmails(Folder folder) {
					// TODO move emails
				}

				@Override
				public void deleteEmails() {
					// TODO delete selected emails
					//        ------>  emailsPanel.checkedEmails ;
				}

				@Override
				public boolean newPage(int page) {
					//decide if the new page is allowed and return true if yes and refresh the emails panel
					//return false if not allowed
					return false;
				}
				
			});

		}
		
		public void refreshEmailsPanel(IMail[] emails) {
			frame.remove(scroll);
			emailsPanel = new EMailsPanel((Email[])emails, user);
			scroll = new JScrollPane(emailsPanel);
			add(scroll, BorderLayout.CENTER);
			revalidate();
		}
		
		
		public static void run(App app) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EMailHomePageGUI(app);
			}
		});
	}
}