package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import eg.edu.alexu.csd.datastructure.mailServer.FilterComp;
import eg.edu.alexu.csd.datastructure.mailServer.sortComparator;
import listeners.FilterSortChangeListener;



public class MenuBar extends JMenuBar{
	FilterSortChangeListener filterSortChangeListener;
	
	private JMenuItem F1,F2,F3;
	private JMenuItem S1,S2,S3,S4;

	private JMenu  SortMenu;
	private JMenu  FilterMenu;
	private JButton FilterButton;
	
	String SortSelected="";
	String Filtered;
	String FilterSelected="";
	
	private JLabel SortLabel;
	public JTextField FilterTextField;
	
	public MenuBar() {
		//menu components
		SortMenu=new JMenu("Default");
		SortMenu.setBackground(new Color(186, 228, 248));
		SortMenu.setOpaque(true);
		FilterMenu =new JMenu("Choose");
		FilterMenu.setBackground(new Color(186, 228, 248));
		FilterMenu.setOpaque(true);
		
		SortLabel=new JLabel("Sort By : ");
		FilterButton=new JButton("Filter/Search");
		FilterTextField=new JTextField(15);
		

		S1 = new JMenuItem("Date");   
		S2 = new JMenuItem("Importance"); 
		S3 = new JMenuItem("Sender"); 
		S4 = new JMenuItem("Subject");
		
		F1 = new JMenuItem("No Filter");
		F2 = new JMenuItem("Subject");   
		F3 = new JMenuItem("Sender"); 
		
		//menu addition
	    SortMenu.add(S1);
	    SortMenu.add(S2);
	    SortMenu.add(S3);
	    SortMenu.add(S4);
	    FilterMenu.add(F1);
	    FilterMenu.add(F2);
	    FilterMenu.add(F3);

	    
	    add(FilterButton);
	    add(FilterMenu);
	    add(FilterTextField);
	    add(SortLabel);
	    add(SortMenu);
		
		
		//adding action listeners for sort menu
		S1.addActionListener(new SortActionListener());
		S2.addActionListener(new SortActionListener());
		S3.addActionListener(new SortActionListener());
		S4.addActionListener(new SortActionListener());
		
		F1.addActionListener(new FilterMenuActionListener());
		F2.addActionListener(new FilterMenuActionListener());
		F3.addActionListener(new FilterMenuActionListener());
		
		//action for search button
		FilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Filtered=FilterTextField.getText();
				 String Type=FilterSelected;
				 if(!Filtered.trim().equals("")&&!Type.trim().equals("")) {
					 
				 }
			}
		});
	}
	
	class SortActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    SortSelected = e.getActionCommand();
		    
		    if (e.getSource() == S1) {
		    	filterSortChangeListener.sortChanged(new sortComparator(5));
		    }
		    
		    //importance
		    else if (e.getSource() == S2) {
		    	System.out.println("importance pressed Sort by importance");
		    	filterSortChangeListener.sortChanged(new sortComparator(6));
		    }
		    
		    else if (e.getSource() == S3) {
		    	filterSortChangeListener.sortChanged(new sortComparator(3));
		    }
	
		    else if (e.getSource() == S4) {
	    		filterSortChangeListener.sortChanged(new sortComparator(2));
	    	}
	    	
		    
    	
		}
	
	}

	class FilterMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FilterSelected =e.getActionCommand();
		   
			if(e.getSource() == F1)
				filterSortChangeListener.filterChanged(new FilterComp(0, FilterTextField.getText()));
			else if(e.getSource() == F2)
				filterSortChangeListener.filterChanged(new FilterComp(1, FilterTextField.getText()));
			else
				filterSortChangeListener.filterChanged(new FilterComp(2, FilterTextField.getText()));
		}
	}

	public void setListener(FilterSortChangeListener filterSortChangeListener) {
		this.filterSortChangeListener = filterSortChangeListener;
	} 

}
