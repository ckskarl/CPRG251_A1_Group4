package sait.bms.application;
import sait.bms.managers.BookManager;
import java.io.*;

/**
 * This class stores the main method and is used to call all other methods .
 * 
 * @author Scott Normore, Gao Liu, Christian Lay, Kin Shing Chong
 * 
 */
public class AppDriver {
	public static void main (String [] args) throws IOException {
		BookManager manager = new BookManager();
		manager.run();
	}
}
