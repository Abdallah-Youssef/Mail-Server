package listeners;

import eg.edu.alexu.csd.datastructure.mailServer.Folder;

public interface EmailPanelUtilsListener {
	public void moveEmails(Folder folder);
	public void deleteEmails();
	public boolean newPage(int page);
}
