package com.example.ee461l_project;


public abstract class Contact implements Observer {
	protected String category; //business or personal
	protected String name;
	protected String phoneNumber;
	protected String email;
	//protected String linkedInURL; //in business contacts only
	protected boolean changed;

	public Contact(String cat, String name, String number, String email){
	   this.category = cat;
	   this.name = name;
	   this.phoneNumber = number;
	   this.email = email;
	   changed = false;
	}

	public String getCategory() {return category;}
	public String getName() {return name;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
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
}