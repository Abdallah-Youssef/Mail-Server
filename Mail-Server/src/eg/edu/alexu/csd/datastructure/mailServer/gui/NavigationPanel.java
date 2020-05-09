package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ListUtils;

public class NavigationPanel extends JPanel {
	GridBagConstraints GC;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JButton[] fileButtons;
	private JButton Contacts;
	private JButton Compose;
	private JButton EMailModification;
	private String[] FileNames;
	
	
	public NavigationPanel(){
		FileNames=new String [] {"Inbox","Sent","Trash","Defined"};
		fileButtons=new JButton[4];
		Contacts=new JButton("Contacts");
		Compose=new JButton("Compose");
		EMailModification=new JButton("EMailModification");
		
		Box ButtonBox=Box.createVerticalBox();
		Box FileBox=Box.createVerticalBox();
		
		ButtonBox.add(Contacts);
		ButtonBox.add(EMailModification);
		
		for(int i=0;i<4;i++) {
			fileButtons[i]=new JButton(FileNames[i]);
			fileButtons[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		for(int i=0;i<4;i++) {
			FileBox.add(fileButtons[i]);
		}
		
		
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
		GC.anchor = GridBagConstraints.LINE_START;
		add(FileBox,GC);
		
		
		
		
		setGridCell(0,1);	
		GC.anchor = GridBagConstraints.LINE_START;
		add(ButtonBox,GC);
		
		setGridCell(0,2);	
		GC.anchor = GridBagConstraints.LINE_START;
		add(Compose,GC);
		
		
		
		
		
		
		
		
		
		
		
		//actions for file buttons start:
		fileButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the inbox panel to the mails area
				 */
			}
		});
		fileButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the sent panel to the mails area
				 */
			}
		});
		fileButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the trash panel to the mails area
				 */
			}
		});
		fileButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the defined panel to the mails area
				 */
			}
		});
		//actions for file buttons end
		//action for email modification
		EMailModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the EMAILMODIFICATION panel to the mails area
				 */
			}
		});
		//action for CONTACTS 
		Contacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * call the defined panel to the mails area
				 */
			}
		});
		
		//action for Compose
		Compose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoubleLinkedList recevires=new DoubleLinkedList();
				ComposeGUI.Run("koskoszobzob@gmail.com", ListUtils.doubleToSingleList(recevires));
			}
		});
	}
	
	private void setGridCell(int x, int y) {
		GC.gridx = x;
		GC.gridy = y;
	}
}