package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import eg.edu.alexu.csd.datastructure.mailServer.Email;
import eg.edu.alexu.csd.datastructure.mailServer.User;
import listeners.PathListener;


class DataModel extends DefaultTableModel{

	int currentPage;
	boolean newCheckedEmails[];
    public DataModel(Object[][] data, Object[] columnNames, int currentPage, boolean[] newCheckedEmails) {
        super(data, columnNames);
        this.currentPage = currentPage;
        this.newCheckedEmails = newCheckedEmails;
        this.addTableModelListener(new TableModelListener() {
			
			@Override
		    public void tableChanged(TableModelEvent e)
		    {
		    	int row = e.getFirstRow();
		    	TableModel m = (TableModel)e.getSource();
		    	Boolean check = (Boolean)m.getValueAt(row, 0);
		    	newCheckedEmails[10*currentPage + row] = check;
		    }
		});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return getValueAt(0, 0).getClass();
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 0;
    }
    
}



public class EMailsPanel{
	JTable table;
	public boolean[] newCheckedEmails;
	int currentPage;
	
	
	public EMailsPanel(Email[] emails, User user, boolean[] checkedEmailsBoxes, int page) {
		
		newCheckedEmails = checkedEmailsBoxes;
		currentPage = page;

		
		DataModel dataModel = new DataModel(null, new Object[] {"Selected","Subject", "Sender", "Date"}, page, newCheckedEmails);
		table = new JTable(dataModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSize(1000, 1000);
		table.setFont(new Font("Arial", Font.PLAIN, 20));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setRowHeight(30);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent me)
			 {
				 if(me.getClickCount() == 2)
				 {
					 int row = ((JTable)me.getSource()).getSelectedRow();
					 EmailView.run(emails[row], new PathListener() {
							public void pathChosen(String path) {
								//TODO for youssef : open the path
								try {
									ViewingAttachment.ViewingAttachment(path);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				 }
			 }
		});
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 0;i < emails.length && emails[i] != null;i++)
			dataModel.addRow(new Object[] {newCheckedEmails[10*currentPage + i], emails[i].getSubject(), emails[i].getSender(), emails[i].getDate().format(DateTimeFormatter.ISO_DATE)});
	}
}
