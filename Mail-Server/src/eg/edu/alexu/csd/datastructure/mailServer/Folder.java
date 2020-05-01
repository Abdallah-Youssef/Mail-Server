package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FilenameFilter;

import interfaces.IFolder;

public class Folder implements IFolder 
{
	String type;
	
	public Folder(String type) {
		this.type = type;
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
			});
	}
}
