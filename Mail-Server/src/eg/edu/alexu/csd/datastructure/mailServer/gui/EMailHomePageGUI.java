package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.mailServer.User;

public class EMailHomePageGUI extends JFrame {
		//declerations
		GridBagConstraints GC;
		private GridBagLayout gridBagLayout = new GridBagLayout();
		
		NavigationPanel navigationPanel;
		MenuBar menuBar;
		
		
		public EMailHomePageGUI(User user,String Email){
			//user is an object that we will pass it to the constructor but i didn't pass it untill we start to deal with data
			//super("Welcome"+user.get("firstName"));
			setSize(800,600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			
			
			navigationPanel = new NavigationPanel();
			menuBar = new MenuBar();
			
			//Layout
			
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
			navigationPanel.setMinimumSize(new Dimension(1000,1000));
			add(navigationPanel, GC);
			
			setJMenuBar(menuBar);

		}
		
		private void setGridCell(int x, int y) {
			GC.gridx = x;
			GC.gridy = y;
		}
		
		public static void run(User user,String Email) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new EMailHomePageGUI(user ,Email);
			}
		});
	}
}