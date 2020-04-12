package Applications;

import Required.*;
import java.io.*;

public class MainApplication {

	public static void main(String[] args)throws IOException {
		AddressBook book;
		BufferedReader br = null;
		File f = null;
		try {
			f=new File("text.txt");
		}catch(Exception e) {
			System.out.println(e);
		}
		book = new AddressBook(f,br);
		book.displayEntries();
		AddressBook b1 = new AddressBook(book);
		b1.displayEntries();
	}

}
