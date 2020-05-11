package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Folder;
import listeners.EmailPanelUtilsListener;

public class EmailPanelUtil extends JPanel{
	JButton nextBtn;
	JButton previousBtn;
	JLabel pageLabel;
	int page = 0;
	
	JButton delete;
	DropDownMenuButton move;
	JPopupMenu foldersMenu;
	
	EmailPanelUtilsListener listener;
	
	public EmailPanelUtil(int userID) {
		setLayout(new FlowLayout());
		
		nextBtn = new JButton("Next");
		previousBtn = new JButton("Previous");
		pageLabel = new JLabel("1");
		
		delete = new JButton("Delete");
		
		foldersMenu = new JPopupMenu();
		move = new DropDownMenuButton("Move", foldersMenu);
		
		add(move);
		add(previousBtn);
		add(pageLabel);
		add(nextBtn);
		add(delete);
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				if (listener.newPage(page)) {
					pageLabel.setText((page+ 1) + "");
				}else
				page--;
			}
		});
		
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CURRENTLY PAGE: " + page);
				page--;
				if (listener.newPage(page)) {
					pageLabel.setText((page+1) + "");
				}else
				page++;
			}
		});
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.deleteEmails();
			}
		});
		
		move.setPopupListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            	foldersMenu.removeAll();
            	String[] userFolders = Folder.listFolders(userID);
    			for (int i = 0;i < userFolders.length;i++) {
    				JMenuItem folder = new JMenuItem(userFolders[i]);
    				folder.addActionListener(new ActionListener() {
    					public void actionPerformed(ActionEvent e) {
    						listener.moveEmails(new Folder(folder.getText()));
    					}
    				});
    				foldersMenu.add(folder);
    			}
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            	move.setSelected(false);
            }
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
				
		});
		
		
	}
	
	public void setListener(EmailPanelUtilsListener listener) {
		this.listener = listener;
	}
}
