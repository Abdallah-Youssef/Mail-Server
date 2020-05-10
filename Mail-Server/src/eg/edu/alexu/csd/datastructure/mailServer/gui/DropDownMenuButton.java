package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

public class DropDownMenuButton extends JToggleButton{
	public JPopupMenu popup;

    public DropDownMenuButton (String name, JPopupMenu menu) {
        super(name);
        this.popup = menu;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = DropDownMenuButton.this;
                if (b.isSelected()) {
                    popup.show(b, 0, b.getBounds().height);
                } else {
                    popup.setVisible(false);
                }
            }
        });
        
        
        /*popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            	menu.removeAll();
            	DoubleLinkedList emails = user.getEmails();
    			for (int i = 0;i < emails.size();i++) {
    				//Make a menuItem for each email
    				//each menuItem when pressed will change the text of the JMenu to its email
    				
    				JMenuItem email = new JMenuItem((String)emails.get(i));
    				email.addActionListener(new ActionListener() {
    					public void actionPerformed(ActionEvent e) {
    						setName(email.getText());
    					}
    				});
    				menu.add(email);
    			}
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            	DropDownMenuButton.this.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });*/
    }
    
    public void setName(String name) {
    	super.setText(name);
    }
    
    public void setPopupListener (PopupMenuListener listener) {
    	popup.addPopupMenuListener(listener);
    }
}
