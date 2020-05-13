package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class ViewingAttachment {

	public static void ViewingAttachment(String path) throws IOException{
	Desktop.getDesktop().open(new File(path));
		}
}

