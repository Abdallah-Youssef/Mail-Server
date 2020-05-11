package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import interfaces.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Filter;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import eg.edu.alexu.csd.datastructure.mailServer.sortComparator;
import eg.edu.alexu.csd.datastructure.mailServer.FilterComp;
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
			emailPanelUtil = new EmailPanelUtil(app.loggedInUser.getID());
			
			app.setViewingOptions(new Folder("inbox"), new FilterComp(0, ""), new sortComparator(5));
			if(app.filteredIndices.size() > 0)
				checkedEmails = new boolean[app.filteredIndices.size()];
			emailsPanel = new EMailsPanel((Email[])app.listEmails(0),user, checkedEmails, 0);
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
					emailPanelUtil.page = 0;
					emailPanelUtil.pageLabel.setText("1");
					int size = app.filteredIndices.size();
					if(size != 0)
						checkedEmails = new boolean[size];

					//emailsPanel = new EMailsPanel((Email[])app.listEmails(0),user, checkedEmails, 0);
					//refreshEmailsPanel(app.listEmails(0));
					
					frame.remove(scroll);
					emailsPanel = new EMailsPanel((Email[])app.listEmails(0), user, checkedEmails, 0);
					scroll = new JScrollPane(emailsPanel);
					add(scroll, BorderLayout.CENTER);
					revalidate();
					

				}
			});
			
			menuBar.setListener(new FilterSortChangeListener() {
				public void filterChanged(FilterComp filter) {
					app.setViewingOptions(app.folder,filter, app.sort);
					refreshEmailsPanel(app.listEmails(0), 0);
					emailPanelUtil.page = 0;
					emailPanelUtil.pageLabel.setText("1");

					//emailsPanel = new EMailsPanel((Email[])app.listEmails(0),user, checkedEmails, emailPanelUtil.page);				
				}

				public void sortChanged(sortComparator sort) {
					app.setViewingOptions(app.folder, app.filter, sort);
					refreshEmailsPanel(app.listEmails(0), 0);
					emailPanelUtil.page = 0;
					emailPanelUtil.pageLabel.setText("1");

					//emailsPanel = new EMailsPanel((Email[])app.listEmails(0),user, checkedEmails, emailPanelUtil.page);				
				}
			});
			
			emailPanelUtil.setListener(new EmailPanelUtilsListener(){
				public void moveEmails(Folder folder) {
					if(app.currentlyLoadedEmails == null)
						return;
					int n = 0;
					DoubleLinkedList emailsToBeMoved = new DoubleLinkedList();
					for(int i = 0; i < checkedEmails.length;i++)
					{
						if(checkedEmails[i])
						{
							emailsToBeMoved.add((Email)app.currentlyLoadedEmails.get((int)app.filteredIndices.get(i)-n));
							app.currentlyLoadedEmails.remove((int)app.filteredIndices.get(i)-n);
							n++;
						}
					}
					Email.saveBulkEmails(app.currentlyLoadedEmails, app.loggedInUser.getID(), app.folder);

					if(emailsToBeMoved.size() > 0)
						app.moveEmails(emailsToBeMoved, folder);
					
					app.setViewingOptions(app.folder, app.filter, app.sort);
					
					if(app.filteredIndices.size() > 0)
						checkedEmails = new boolean[app.filteredIndices.size()];
					else
						checkedEmails = null;

					
					refreshEmailsPanel(app.listEmails(0), 0);
					emailPanelUtil.page = 0;
					emailPanelUtil.pageLabel.setText("1");

				}

				@Override
				public void deleteEmails() {
				DoubleLinkedList checkBoxes = new DoubleLinkedList();
					for(boolean x: checkedEmails)
						checkBoxes.add(x);
					app.deleteEmails(checkBoxes);
					if(checkBoxes.size() == 0)
						checkedEmails = null;
					else
						checkedEmails = new boolean[(int)checkBoxes.get(0)];
					refreshEmailsPanel(app.listEmails(0), 0);
					emailPanelUtil.page = 0;
					emailPanelUtil.pageLabel.setText("1");

				}

				@Override
				public boolean newPage(int page) {
					if(app.currentlyLoadedEmails == null)
						return false;
					int size = app.filteredIndices.size();
					if(page*10 >= 0 && page*10 < size)
					{
						refreshEmailsPanel(app.listEmails(page), page);
						return true;
					}
					return false;
				}
				
			});

		}
		
		public void refreshEmailsPanel(IMail[] emails, int page) {
			frame.remove(scroll);
			emailsPanel = new EMailsPanel((Email[])emails, user, checkedEmails, page);
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