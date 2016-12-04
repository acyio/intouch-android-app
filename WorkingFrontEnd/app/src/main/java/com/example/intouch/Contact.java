package com.example.intouch;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Contact implements Observer, Parcelable, Serializable {
	protected String category; //business or personal
	protected String name;
	protected String phoneNumber;
	protected String email;
	//protected String linkedInURL; //in business contacts only
	protected boolean changed;
	protected ArrayList<Contact> localList;
	protected int signature;
	private static final long serialVersionUID = 1L;
	public Contact(String cat, String name, String number, String email){
		this.category = cat;
		this.name = name;
		this.phoneNumber = number;
		this.email = email;
		changed = false;
		this.localList = new ArrayList<Contact>();
		this.signature = (int)(Math.random()*10000);

		for (int i = 0; i < this.localList.size(); i++){ //iterate through contacts
			if (this.signature == this.localList.get(i).signature){ //check collision
				this.signature = (int)(Math.random()*10000); //regenerate if collision
				i = 0; //check list again
			}
		}
	}

	public Contact(Parcel input) {
		this.category = input.readString();
		this.name = input.readString();
		this.email = input.readString();
		this.phoneNumber = input.readString();
		this.signature = input.readInt();
	}

	public Contact(){}

	public String getCategory() {return category;}
	public String getName() {return name;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	public ArrayList<Contact> getLocalList() {return localList;}
	public int getSignature() { return signature;}
	//public String getLinkedInURL() {return linkedInURL; //in business contact only}


	public void setName(String newName) {
		name = newName;
		this.changed = true;
	}
	public void setPhoneNumber(String newNumber) {
		phoneNumber = newNumber;
		this.changed = true;
	}
	public void setEmail(String newEmail) {
		email = newEmail;
		this.changed = true;
	}

	public void newLocalList() {
		this.localList = new ArrayList<Contact>();
	}

	public void update() {
		this.changed = false;
	}

	public boolean contactMatch(String query) {
		String significantEmail = this.getEmail(); //will cut off any part of email after and including the @ symbol
		int index = significantEmail.indexOf('@');
		significantEmail = significantEmail.substring(0, index);
		if(this.getName().toLowerCase().contains(query.toLowerCase())) {
			return true;
		}
		else if(this.getPhoneNumber().toLowerCase().contains(query.toLowerCase())) {
			return true;
		}
		else if (significantEmail.toLowerCase().contains(query.toLowerCase())) {
			return true;
		}
		else {
			return false;
		}

	}

	public void generateUniqueSignature(Database db){
		ArrayList<Contact> theList = db.allContacts;
		for (int i = 0; i < theList.size(); i++){ //iterate through contacts
			if (this.signature == theList.get(i).signature){ //check collision
				this.signature = (int)(Math.random()*10000); //regenerate if collision
				i = 0; //check list again
			}
		}
	}
}