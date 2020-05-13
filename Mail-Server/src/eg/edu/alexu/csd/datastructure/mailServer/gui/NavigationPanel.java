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
	private JButton userSettings;
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
		
		userSettings=new JButton("User Settings");
		userSettings.setAlignmentX(CENTER_ALIGNMENT);
		Box ButtonBox=Box.createVerticalBox();
		Box FileBox=Box.createVerticalBox();
		
		ButtonBox.add(Contacts);
		ButtonBox.add(userSettings);
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
		
		
		
		String systemFolders[] = new String[] {"inbox", "sent", "trash", "draft"};
		
		for(int i = 0; i < 4;i++)
		{
			String systemFolder = systemFolders[i];
			fileButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					folderChangeListener.newFolder(new Folder(systemFolder));
				}
			});
		}
		
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

		
		userSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSettings.run(user);
			}
		});
		
		Contacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	}
	
	private void setGridCell(int x, int y) {
		GC.gridx = x;
		GC.gridy = y;
	}
	
	public void setListener(FolderChangeListener folderChangeListener) {
		this.folderChangeListener = folderChangeListener;
	}
}
