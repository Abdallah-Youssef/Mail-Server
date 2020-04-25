package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class EMailHomePageGUI extends JFrame {
		//declerations
		GridBagConstraints GC;
		String SortSelected;
		String Searched;
		private JMenuItem m1,m2,m3,m4;
		private JMenuBar MenuB;
		private JMenu  SortMenu;
		private JButton []fileButtons;
		private JButton Contacts;
		private JButton Compose;
		private JButton EMailModification;
		private JButton SearchButton;
		private JLabel SortLabel;
		public JTextField SearchTextField;
		private GridBagLayout gridBagLayout = new GridBagLayout();
		private String []FileNames;
		
		public EMailHomePageGUI(){
			//user is an object that we will pass it to the constructor but i didn't pass it untill we start to deal with data
			//super("Welcome"+user.get("firstName"));
			setSize(800,600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			//componenets
			FileNames=new String [] {"Inbox","Sent","Trash","Defined"};
			fileButtons=new JButton[4];
			Contacts=new JButton("Contacts");
			Compose=new JButton("Compose");
			SearchButton=new JButton("Search :");
			SearchTextField=new JTextField(25);
			EMailModification=new JButton("EMailModification");
			SortLabel=new JLabel("Sort By : ");
			
			//BOXES COMPONENTS
			Box FileBox=Box.createVerticalBox();
			Box ButtonBox=Box.createVerticalBox();
			Box SearchBox=Box.createHorizontalBox();
			
			//menu components
			MenuB=new JMenuBar();
			SortMenu=new JMenu("Default");
			 m1 = new JMenuItem("Date");   
			 m2 = new JMenuItem("Importance"); 
		     m3 = new JMenuItem("Sender"); 
		     m4 = new JMenuItem("Subject"); 
		    
		     //BOXES Addition
			SearchBox.add(SearchButton);
			SearchBox.add(SearchTextField);
			ButtonBox.add(Contacts);
			ButtonBox.add(EMailModification);
			//END of Boxes
			
			//menu addition
		     SortMenu.add(m1);
		     SortMenu.add(m2);
		     SortMenu.add(m3);
		     SortMenu.add(m4);
		     MenuB.add(SearchBox);
		     MenuB.add(SortLabel);
	         MenuB.add(SortMenu);
			setJMenuBar(MenuB);
			
			//adding action listeners for sort menu
			m1.addActionListener(new MenuActionListener());
			m2.addActionListener(new MenuActionListener());
			m3.addActionListener(new MenuActionListener());
			m4.addActionListener(new MenuActionListener());
			//menu action listener classe handle th sorting
			
			for(int i=0;i<4;i++) {
				fileButtons[i]=new JButton(FileNames[i]);
			}
			for(int i=0;i<4;i++) {
				FileBox.add(fileButtons[i]);
			}
			
			Border outsideBorder = BorderFactory.createEmptyBorder(4, 2, 5, 2);
			Border insideBorder = BorderFactory.createTitledBorder("Main Page : "  );
			getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
			
			
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
					/*
					 * call the defined panel to the mails area
					 */
				}
			});
			//action for search button
			SearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 Searched=SearchTextField.getText();
					/*
					 * call the searched then display it 
					 */
				}
			});
		
		}
		class MenuActionListener implements ActionListener {
			  public void actionPerformed(ActionEvent e) {
			    SortSelected =e.getActionCommand();
			   /*
			    * sort fn and then display data 
			    */

			  }
			}
		private void setGridCell(int x, int y) {
		GC.gridx = x;
		GC.gridy = y;
	}
	public static void run() {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EMailHomePageGUI();
			}
		});
	}
}