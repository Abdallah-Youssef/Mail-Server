package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Listeners.FilterSortChangeListener;
import eg.edu.alexu.csd.datastructure.mailServer.sortComparator;
import interfaces.IFilter;
import interfaces.ISort;



public class MenuBar extends JMenuBar{
	FilterSortChangeListener filterSortChangeListener;
	
	private JMenuItem m1,m2,m3,m4;
	private JMenuItem F1,F2;
	private JMenuItem S1,S2,S3,S4;

	private JMenu  SortMenu;
	private JMenu  SearchMenu;
	private JMenu  FilterMenu;
	private JButton FilterButton;
	
	String SortSelected="";
	String Filtered;
	String Searched;
	String SearchSelected="";
	String FilterSelected="";
	
	private JButton SearchButton;
	private JLabel SortLabel;
	public JTextField SearchTextField;
	public JTextField FilterTextField;
	
	public MenuBar() {
		//menu components
		SortMenu=new JMenu("Default");
		SearchMenu=new JMenu("Choose");
		FilterMenu =new JMenu("Choose");
		
		SearchButton=new JButton("Search :");
		SearchTextField=new JTextField(25);
		SortLabel=new JLabel("Sort By : ");
		FilterButton=new JButton("Filter");
		FilterTextField=new JTextField(15);
		
		m1 = new JMenuItem("Date");   
		m2 = new JMenuItem("Importance"); 
		m3 = new JMenuItem("Sender"); 
		m4 = new JMenuItem("Subject"); 
		S1 = new JMenuItem("Date");   
		S2 = new JMenuItem("Importance"); 
		S3 = new JMenuItem("Sender"); 
		S4 = new JMenuItem("Subject");
		F1 = new JMenuItem("Subject");   
		F2 = new JMenuItem("Sender"); 
		
		//menu addition
	    SortMenu.add(m1);
	    SortMenu.add(m2);
	    SortMenu.add(m3);
	    SortMenu.add(m4);
	    SearchMenu.add(S1);
	    SearchMenu.add(S2);
	    SearchMenu.add(S3);
	    SearchMenu.add(S4);
	    FilterMenu.add(F1);
	    FilterMenu.add(F2);
	    add(SearchButton);
	    add(SearchMenu);
	    add(SearchTextField);
	    add(FilterButton);
	    add(FilterMenu);
	    add(FilterTextField);
	    add(SortLabel);
	    add(SortMenu);
		
		
		//adding action listeners for sort menu
		m1.addActionListener(new SortActionListener());
		m2.addActionListener(new SortActionListener());
		m3.addActionListener(new SortActionListener());
		m4.addActionListener(new SortActionListener());
		S1.addActionListener(new SearchMenuActionListener());
		S2.addActionListener(new SearchMenuActionListener());
		S3.addActionListener(new SearchMenuActionListener());
		S4.addActionListener(new SearchMenuActionListener());
		F1.addActionListener(new FilterMenuActionListener());
		F2.addActionListener(new FilterMenuActionListener());
		
		
		
		//action for search button
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Searched=SearchTextField.getText();
				 String Type=SearchSelected;
				 if(!Searched.trim().equals("")&&!Type.trim().equals("")) {
					 
				 }
				/*
				 * call the searched then display it 
				 */
				 
			}
		});
		FilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Filtered=SearchTextField.getText();
				 String Type=FilterSelected;
				 if(!Searched.trim().equals("")&&!Type.trim().equals("")) {
					 
				 }
				/*
				 * call the searched then display it 
				 */
			}
		});
	}
	
	class SortActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    SortSelected = e.getActionCommand();

		    if (e.getSource() == m1) {
		    	filterSortChangeListener.sortChanged(new sortComparator(5));
		    }
		    
		    if (e.getSource() == m2) {
		    	filterSortChangeListener.sortChanged(new sortComparator(6));
		    }
		    
		    if (e.getSource() == m3) {
		    	filterSortChangeListener.sortChanged(new sortComparator(3));
		    }
	
	    	if (e.getSource() == m4) {
	    		filterSortChangeListener.sortChanged(new sortComparator(2));
	    	}
    	
		}
	
	}
	class SearchMenuActionListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
	    SearchSelected =e.getActionCommand();
	   /*
	    * sort fn and then display data 
	    */

	  }
	}
	class FilterMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FilterSelected =e.getActionCommand();
		   /*
		    * sort fn and then display data 
		    */

		}
	}

	public void setListener(FilterSortChangeListener filterSortChangeListener) {
		this.filterSortChangeListener = filterSortChangeListener;
	} 

}
