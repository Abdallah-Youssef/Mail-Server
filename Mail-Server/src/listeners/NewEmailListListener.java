package listeners;

import eg.edu.alexu.csd.datastructure.mailServer.DoubleLinkedList;

public interface NewEmailListListener {
	public void newEmailList(DoubleLinkedList emails);
}
