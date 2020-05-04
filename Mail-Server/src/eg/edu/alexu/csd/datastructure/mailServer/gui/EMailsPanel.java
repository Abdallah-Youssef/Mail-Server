package eg.edu.alexu.csd.datastructure.mailServer.gui;

import javax.swing.JPanel;

import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.Email;

public class EMailsPanel extends JPanel {
	public EMailsPanel(DoubleLinkedList emails) {
		//new EmailsBox
		//for each email in mails
		//	new EMailPanel
		//	EMailsBox.Add(EmailPanel)
		
	}
	
	public class EMailPanel extends JPanel{
		EMailPanel(Email email){
			//add check box
			//add Button ("subject sender date")
		}
	}
	
	public class EMailsBox {
		/*Add(EMailPanel)
		Delete(EMailPanel)*/
	}
}
