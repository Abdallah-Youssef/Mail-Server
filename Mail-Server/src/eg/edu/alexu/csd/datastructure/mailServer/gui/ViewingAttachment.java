package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class ViewingAttachment {

	ViewingAttachment(String path) throws IOException{
	Desktop.getDesktop().open(new File(path));
		}
	}

