package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import dataStructures.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.App;
import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import interfaces.IFolder;
import listeners.EmailsPanelListener;
import listeners.FolderChangeListener;
import listeners.NewEmailListListener;

public class NavigationPanel extends JPanel {
	GridBagConstraints GC;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JButton[] fileButtons;
	private JButton Contacts;
	private JButton NewFolderButton;
	private JButton Compose;
	private JButton EMailModification;
	private String[] FileNames;
	private DropDownMenuButton Defined;
	private JPopupMenu foldersMenu;
	
	FolderChangeListener folderChangeListener;
	App app;
	User user;
	
	public NavigationPanel(App app){
		this.app = app;
		user = app.loggedInUser;
		
		FileNames=new String [] {"Inbox","Sent","Trash","Drafts"};
		fileButtons=new JButton[4];
		
		Contacts=new JButton("Contacts");
		Contacts.setAlignmentX(CENTER_ALIGNMENT);
		
		NewFolderButton = new JButton("Create Folder");
		NewFolderButton.setAlignmentX(CENTER_ALIGNMENT);
		
		Compose=new JButton("Compose");
		
		EMailModification=new JButton("EMailModification");
		EMailModification.setAlignmentX(CENTER_ALIGNMENT);
		Box ButtonBox=Box.createVerticalBox();
		Box FileBox=Box.createVerticalBox();
		
		ButtonBox.add(Contacts);
		ButtonBox.add(EMailModification);
		ButtonBox.add(NewFolderButton);
		
		
		for(int i=0;i<4;i++) {
			fileButtons[i]=new JButton(FileNames[i]);
			fileButtons[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		
		foldersMenu = new JPopupMenu();
		Defined = new DropDownMenuButton("Defined", foldersMenu);
		Defined.setAlignmentX(CENTER_ALIGNMENT);
		
		
		for(int i=0;i<4;i++) {
			FileBox.add(fileButtons[i]);
		}
		FileBox.add(Defined);
		
		//Layout and gridding
		Border outsideBorder = BorderFactory.createEmptyBorder(4, 2, 5, 2);
		Border insideBorder = BorderFactory.createTitledBorder("Navigation : "  );
		setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		
		setLayout(gridBagLayout);
		GC=new GridBagConstraints();
		GC.weightx =1 ;
		GC.weighty =1;
		GC.fill = GridBagConstraints.NONE;	
		
		setGridCell(0,0);	
		GC.anchor = GridBagConstraints.CENTER;
		add(FileBox,GC);
		
		
		
		
		setGridCell(0,1);	
		GC.anchor = GridBagConstraints.CENTER;
		add(ButtonBox,GC);
		
		setGridCell(0,2);	
		GC.anchor = GridBagConstraints.CENTER;
		add(Compose,GC);
		
		
		
		
		//actions for file buttons start:
		fileButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the inbox panel to the mails area
				 */
				folderChangeListener.newFolder(new Folder("inbox"));
			}
		});
		fileButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the sent panel to the mails area
				 */
				folderChangeListener.newFolder(new Folder("sent"));
			}
		});
		fileButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the trash panel to the mails area
				 */
				folderChangeListener.newFolder(new Folder("trash"));
			}
		});
		
		fileButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the trash panel to the mails area
				 */
				folderChangeListener.newFolder(new Folder("draft"));
			}
		});

		
		Defined.setPopupListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				foldersMenu.removeAll();
				String[] userFolders = Folder.listFolders(app.loggedInUser.getID());
    			for (int i = 0;i < userFolders.length;i++) {
    				JMenuItem folder = new JMenuItem(userFolders[i]);
    				folder.addActionListener(new ActionListener() {
    					public void actionPerformed(ActionEvent e) {
    						folderChangeListener.newFolder(new Folder(folder.getText()));
    					}
    				});
    				foldersMenu.add(folder);
    			}
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				Defined.setSelected(false);
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
		});

		
		
		//actions for file buttons end
		//action for email modification
		EMailModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailModificationGUI.run(user);
			}
		});
		//action for CONTACTS 
		Contacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the defined panel to the mails area
				 */
				ContactsGUI.run(app);
			}
		});
		
		NewFolderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateNewFolderGUI.run(app.loggedInUser.getID());
			}
		});
		
		
		//action for Compose
		Compose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoubleLinkedList recevires=new DoubleLinkedList();
				
				//TODO add the correct email
				ComposeGUI.Run(user, ListUtils.doubleToSingleList(recevires));
			}
		});
		
		
		
		/*
		 Testing stuff
		 TODO remove
		 */
		//EmailModificationGUI.run(user);
		
	}
	
	private void setGridCell(int x, int y) {
		GC.gridx = x;
		GC.gridy = y;
	}
	
	public void setListener(FolderChangeListener folderChangeListener) {
		this.folderChangeListener = folderChangeListener;
	}
}
