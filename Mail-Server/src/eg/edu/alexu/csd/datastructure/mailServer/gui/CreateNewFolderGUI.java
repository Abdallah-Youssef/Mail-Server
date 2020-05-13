package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import eg.edu.alexu.csd.datastructure.mailServer.Folder;

public class CreateNewFolderGUI extends JFrame{
	GridBagConstraints gc;
	JLabel label, error;
	JTextField folderName;
	int userID;
	
	JButton submit;
	
	public CreateNewFolderGUI(int id) {
		
		super("Create New Folder");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

		folderName = new JTextField(25);
		label = new JLabel("Enter new folder name: ");
		submit = new JButton("Create");
		error = new JLabel("");
		
		userID = id;
		//Border
		Border outsideBorder = BorderFactory.createEmptyBorder(40, 25, 50, 25);
		Border insideBorder = BorderFactory.createTitledBorder("Create a new Folder");
		getRootPane().setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		
		//Layout Manager
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		
		////// First Row
		setGridCell(0,0);
		gc.anchor = GridBagConstraints.LINE_END;
		add(label, gc);

		setGridCell(1,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(folderName, gc);
		
		///// second row
		setGridCell(0,1);
		gc.anchor = GridBagConstraints.LINE_END;
		add(error, gc);
		
		setGridCell(0,2);
		gc.anchor = GridBagConstraints.LINE_END;
		add(submit, gc);

		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] userFolders = Folder.listFolders(userID);
				String[] systemFolders = {"inbox", "draft", "sent", "trash"};
				boolean ok = true;
				String newFolder = folderName.getText();
				for(String f: userFolders)
				{
					if(f.equals(newFolder))
					{
						ok = false;
						break;
					}
				}
				for(String f: systemFolders)
				{
					if(f.equals(newFolder))
					{
						ok = false;
						break;
					}
				}
				
				if(ok)
				{
					try
					{
						String path = "./Users/" + id + "/";
						new File(path+ newFolder+"/").mkdirs();
						new File(path+newFolder+"/index.txt").createNewFile();
					}catch(IOException ex)
					{
						ex.printStackTrace();
					}
					setVisible(false);
				}else
				{
					error.setText("Folder already exists");
				}
			}
		});
	
	}
	
	public void setGridCell(int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
	}
	
	public static void run(int userID) {
	SwingUtilities.invokeLater(new Runnable () {
		public void run() {
			new CreateNewFolderGUI(userID);
		}
	});
	}

}
