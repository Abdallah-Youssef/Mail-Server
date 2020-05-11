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
	
	private JButton Contacts;
	private JButton Compose;
	private JButton EMailModification;
	DropDownMenuButton foldersButton;
	JPopupMenu foldersMenu;
	
	
	FolderChangeListener folderChangeListener;
	App app;
	User user;
	
	public NavigationPanel(App app){
		this.app = app;
		user = app.loggedInUser;
		
		foldersMenu = new JPopupMenu();
		foldersButton = new DropDownMenuButton("Folders", foldersMenu);
		
		Contacts=new JButton("Contacts");
		Contacts.setAlignmentX(CENTER_ALIGNMENT);
		
		Compose=new JButton("Compose");
		
		EMailModification=new JButton("User Settings");
		EMailModification.setAlignmentX(CENTER_ALIGNMENT);
		Box ButtonBox=Box.createVerticalBox();
		
		ButtonBox.add(Contacts);
		ButtonBox.add(EMailModification);
		

		
		
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
		add(foldersButton,GC);
		
		
		
		
		setGridCell(0,1);	
		GC.anchor = GridBagConstraints.CENTER;
		add(ButtonBox,GC);
		
		setGridCell(0,2);	
		GC.anchor = GridBagConstraints.CENTER;
		add(Compose,GC);
		
		
		
		
		
		
		
		
		
		
		foldersButton.setPopupListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            	foldersButton.popup.removeAll();
            	DoubleLinkedList folders = user.getFolders();
    			for (int i = 0;i < folders.size();i++) {
    				//Make a menuItem for each email
    				//each menuItem when pressed will change the text of the JMenu to its email
    				
    				JMenuItem folder = new JMenuItem((String)folders.get(i));
    				folder.addActionListener(new ActionListener() {
    					public void actionPerformed(ActionEvent e) {
    						foldersButton.setName(folder.getText());
    						folderChangeListener.newFolder(new Folder(folder.getText()));
    					}
    				});
    				foldersButton.popup.add(folder);
    			}
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            	foldersButton.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
		
		//actions for file buttons end
		//action for email modification
		EMailModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSettings.run(user);
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
