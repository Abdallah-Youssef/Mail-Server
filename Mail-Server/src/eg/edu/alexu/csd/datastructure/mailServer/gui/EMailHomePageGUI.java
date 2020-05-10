package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.linkedList.cs.Interfaces.ILinkedList;
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
		
		NavigationPanel navigationPanel;
		MenuBar menuBar;
		EMailsPanel emailsPanel;
		EmailPanelUtil emailPanelUtil;
		
		boolean[] checkedEmails;
		
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
			emailsPanel = new EMailsPanel(ListUtils.getPage(initialMails, 1),user, checkedEmails, emailPanelUtil.page);
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
					int size = app.currentlyLoadedEmails.size();
					if(size != 0)
						checkedEmails = new boolean[size];
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
					int n = 0;
					DoubleLinkedList emailsToBeMoved = new DoubleLinkedList();
					for(int i = 0; i < checkedEmails.length;i++)
					{
						if(checkedEmails[i])
						{
							emailsToBeMoved.add((Email)app.currentlyLoadedEmails.get(n));
							app.currentlyLoadedEmails.remove(n);
							continue;
						}
						n++;
					}
					app.moveEmails((ILinkedList)emailsToBeMoved, folder);
					Email.saveBulkEmails(app.currentlyLoadedEmails, app.loggedInUser.getID(), app.folder);
				}

				@Override
				public void deleteEmails() {
					int n = 0;
					for(int i = 0; i < checkedEmails.length;i++)
					{
						if(checkedEmails[i])
						{
							app.currentlyLoadedEmails.remove(n);
							continue;
						}
						n++;
					}
					Email.saveBulkEmails(app.currentlyLoadedEmails, app.loggedInUser.getID(), app.folder);
				}

				@Override
				public boolean newPage(int page) {
					int size = app.currentlyLoadedEmails.size();
					if(page*10 >= 0 && page*10 < size)
					{
						refreshEmailsPanel(app.listEmails(page));
						return true;
					}
					return false;
				}
				
			});

		}
		
		public void refreshEmailsPanel(IMail[] emails) {
			frame.remove(scroll);
			emailsPanel = new EMailsPanel((Email[])emails, user, checkedEmails, emailPanelUtil.page);
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