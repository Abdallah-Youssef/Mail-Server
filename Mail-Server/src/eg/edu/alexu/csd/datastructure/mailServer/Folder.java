package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FilenameFilter;

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
	
	public static String[] listFolders(int userID)
	{
		String path = "./Users/" + userID + "/";
		File directory = new File(path);
		return directory.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});;
	}
}
