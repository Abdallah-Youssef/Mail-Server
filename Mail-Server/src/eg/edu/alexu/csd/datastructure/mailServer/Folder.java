package eg.edu.alexu.csd.datastructure.mailServer;

public class Folder implements IFolder 
{
	String type;
	
	public Folder(int f) {
		switch (f) {
		case 0: 
			type = "inbox";
			break;
		case 1:
			type = "sent";
			break;
		case 2:
			type = "drafts";
			break;
		}
	}
	
}
