package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class EMailHomePageGUI extends JFrame {
	GridBagConstraints GC;
	JButton []fileButtons;
	GridBagLayout gridBagLayout = new GridBagLayout();
	String []Names;
	public EMailHomePageGUI(){
		//super("Welcome"+user.get("firstName"));
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Names=new String [] {"Inbox","Sent","Trash","Defined"};
		fileButtons=new JButton[4];
		for(int i=0;i<4;i++) {
		fileButtons[i]=new JButton(Names[i]);
		}
		Border outsideBorder = BorderFactory.createEmptyBorder(4, 2, 5, 2);
		Border insideBorder = BorderFactory.createTitledBorder("L : ");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		Box FileBox=Box.createVerticalBox();
		for(int i=0;i<4;i++) {
			FileBox.add(fileButtons[i]);
		}
		
		setLayout(gridBagLayout);
		GC=new GridBagConstraints();
		GC.weightx =2 ;
		GC.weighty = 2;
		GC.fill = GridBagConstraints.NONE;		
		
		setGridCell(0,0);	
		GC.anchor = GridBagConstraints.LINE_START;
		add(FileBox,GC);
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
		//action for file buttons end
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