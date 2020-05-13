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
        
        
    }
    
    public void setName(String name) {
    	super.setText(name);
    }
    
    public void setPopupListener (PopupMenuListener listener) {
    	popup.addPopupMenuListener(listener);
    }
}
