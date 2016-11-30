package com.example.ee461l_project;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public abstract class Contact implements Observer, Parcelable {
	protected String category; //business or personal
	protected String name;
	protected String phoneNumber;
	protected String email;
	//protected String linkedInURL; //in business contacts only
	protected boolean changed;
	protected ArrayList<Contact> localList;

	public Contact(String cat, String name, String number, String email){
		this.category = cat;
		this.name = name;
		this.phoneNumber = number;
		this.email = email;
		changed = false;
		this.localList = new ArrayList<Contact>();
	}

	public Contact(Parcel input) {
		this.category = input.readString();
		this.name = input.readString();
		this.email = input.readString();
		this.phoneNumber = input.readString();
	}

	public String getCategory() {return category;}
	public String getName() {return name;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	public ArrayList<Contact> getLocalList() {return localList;}
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
}