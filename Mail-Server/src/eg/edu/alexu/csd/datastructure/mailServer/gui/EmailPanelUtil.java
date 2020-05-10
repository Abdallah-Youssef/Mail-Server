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
	
	public EmailPanelUtil(DoubleLinkedList folders) {
		setLayout(new FlowLayout());
		
		nextBtn = new JButton("Next");
		previousBtn = new JButton("Previous");
		pageLabel = new JLabel("0");
		
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
				if (listener.newPage(page+1)) {
					page++;
					pageLabel.setText(page + "");
				}
			}
		});
		
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener.newPage(page-1)) {
					page--;
					pageLabel.setText(page + "");
				}
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
    			for (int i = 0;i < folders.size();i++) {
    				JMenuItem folder = new JMenuItem((String)folders.get(i));
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
