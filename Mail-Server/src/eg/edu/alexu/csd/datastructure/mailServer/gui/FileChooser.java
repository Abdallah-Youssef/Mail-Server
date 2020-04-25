package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class FileChooser extends JPanel implements ActionListener {
	static private final String newline = "\n";
	PathListener listener;
    public String chosenPath;
	JButton openButton;
    JTextArea log;
    JFileChooser fc;
    public FileChooser(PathListener listener) {
    	super(new BorderLayout());
    	this.listener = listener;
    	
    	//create log
    	log=new JTextArea(5,20);
    	log.setMargin(new Insets(5,5,5,5));
    	
    	log.setEditable(false);
    	JScrollPane logScrollPane=new JScrollPane(log);
    	//create a file chooser
    	fc=new JFileChooser();
    	openButton=new JButton("Open a file");
    	openButton.addActionListener(this);
    	 
    	JPanel buttonPanel=new JPanel();
    	buttonPanel.add(openButton);
    	
    	add(buttonPanel,BorderLayout.PAGE_START);
    	add(logScrollPane,BorderLayout.CENTER);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==openButton) {
			int returnVal=fc.showOpenDialog(FileChooser.this);
			
			if(returnVal==JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				chosenPath=file.getAbsolutePath();
				listener.pathChosen(chosenPath);
				log.append(chosenPath);
				/*
				 * [think how to return it 
				 */
			}
			else {
				log.append("Open command cancelled by user." + newline);
			} 
		}
	}
    
private static void createAndShowGUI(PathListener listener) {
    //Create and set up the window.
    JFrame frame = new JFrame("FileChooserDemo");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //Add content to the window.
    frame.add(new FileChooser(listener));

    //Display the window.
    frame.pack();
    frame.setVisible(true);
}

public static void Run(PathListener listener) {
	SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI(listener);
        }
    });
}

/*public static void main(String[] args) {
    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.
    /*SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            //Turn off metal's use of bold fonts
           // UIManager.put("swing.boldMetal", Boolean.FALSE); 
            createAndShowGUI(new PathListener());
        }
    });
}*/
}
