package Required;

import java.util.*;

public class Entries implements Comparable<Entries>{
	private String firstName,lastName,address,zip,phone,email;
	
	//Constructors
	public Entries() {
		firstName=null;
		lastName=null;
		address=null;
		zip=null;
		phone=null;
		email=null;
	}
	
	public Entries(String f,String l,String a,String z,String p,String e) {
		firstName=f;
		lastName=l;
		address=a;
		zip=z;
		phone = p;
		email=e;
	}
	
	public Entries(String f,String l,String a,String z) {
		firstName=f;
		lastName=l;
		address=a;
		zip=z;
		phone=null;
		email=null;
	}
	
	public Entries(Entries a) {
		this.firstName=a.firstName;
		this.lastName=a.lastName;
		this.address=a.address;
		this.zip=a.zip;
		this.phone=a.phone;
		this.email=a.email;
	}
	
	//Helper Methods
	public void setFirstName(String f) {
		firstName=f;
	}
	public void setLastName(String l) {
		lastName=l;
	}
	public void setAddress(String a) {
		address=a;
	}
	public void setZip(String z) {
		zip=z;
	}
	public void setPhone(String p) {
		phone = p;
	}
	public void setEmail(String e) {
		email=e;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getZip() {
		return zip;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return "Name: "+firstName+" "+lastName+"  Address: "+address+"  Zip: "+zip+"  Phone: "+phone+"  Email: "+email; 
	}
	
	//Comparing methods
	@Override
	public int compareTo(Entries o) {
		if(this.lastName.compareTo(o.lastName)==0) {
			return this.firstName.compareTo(o.firstName);
		}
		return this.lastName.compareTo(o.lastName);
	}
	
	public static Comparator<Entries> EntriesZipComparator = new Comparator<Entries>() {
		public int compare(Entries a,Entries b) {
			if(a.zip.compareTo(b.zip)==0) {
				return a.compareTo(b);
			}
			return a.zip.compareTo(b.zip);
		}
	};
}
