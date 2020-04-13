package Required;

import java.io.*;
import java.util.*;

public class AddressBook {
	private int top;
	private Entries[] entries;
	
	//index field is the unique primary key
	
	//Constructors
	public AddressBook() {
		top=0;
		entries=new Entries[top];
	}
	@SuppressWarnings("resource")
	public AddressBook(File f,BufferedReader br)throws IOException {
		br = new BufferedReader(new FileReader(f));
		top=0;
		String s;
		while((s=br.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(s,"|");
			if(top==0) {
				entries=new Entries[1];
				entries[0]=new Entries(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
				top++;
				continue;
			}
			entries=Arrays.copyOf(entries,top+1);
			entries[top]=new Entries(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
			top++;
		}
	}
	public AddressBook(AddressBook a) {
		top=1;
		entries=new Entries[1];
		entries[0]=new Entries(a.getIthEntries(0));
		for(int i=1;i<a.getTop();i++) {
			entries=Arrays.copyOf(entries,top+1);
			entries[top]=new Entries(a.getIthEntries(i));
			top++;
		}
	}
	
	//Helper Methods
	public int getTop() {
		return top;
	}
	
	public Entries getIthEntries(int i) {
		if(i<top) {
			return entries[i];
		}
		return null;
	}
	
	//Methods Useful
	public void addEntries(Entries a) {
		if(top==0) {
			entries=new Entries[1];
			entries[0]=new Entries(a);
		}
		entries = Arrays.copyOf(entries,top+1);
		entries[top]=new Entries(a);
		top++;
	}
	
	public void addEntries(String s) {
		StringTokenizer st = new StringTokenizer(s,"|");
		if(top==0) {
			entries=new Entries[1];
			entries[0]=new Entries(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
			top++;
			return;
		}
		entries = Arrays.copyOf(entries,top+1);
		entries[top]=new Entries(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
		top++;
	}
	
	public boolean deleteEntries(int id) {
		if(id<top) {
			for(int i=id;i<top-1;i++) {
				entries[i]=entries[i+1];
			}
			entries[top-1]=null;
			top--;
			return true;
		}
		return false;
	}
	
	public void writeFile(String z,BufferedWriter bw)throws IOException{
		String s = "";
		for(int i=0;i<top;i++) {
			s=entries[i].getFirstName()+"|"+entries[i].getLastName()+"|"+entries[i].getAddress()+"|"+entries[i].getZip()+"|"+entries[i].getPhone()+"|"+entries[i].getEmail();
			s+="\n";
			bw.write(s);
		}
	}
	
	//Sorting Methods
	public void getSortedByName() {
		Arrays.sort(entries);
	}
	
	public void getSortedByZip() {
		Arrays.sort(entries,Entries.EntriesZipComparator);
	}
	
	//Printing the entries
	public void displayEntries() {
		for(int i=0;i<top;i++) {
			System.out.print(i+1);
			System.out.println(" "+entries[i]);
		}
	}
}
