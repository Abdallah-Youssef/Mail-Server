package eg.edu.alexu.csd.datastructure.mailServer.gui;

import java.io.File;
import java.io.IOException;



public class MainGUI {
	public static void main(String[] args) throws IOException {
		new File("./Users").mkdirs();
		MainPageGUI.Run();
	}
}
 