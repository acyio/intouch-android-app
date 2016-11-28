package com.example.ee461l_project;

public abstract class Contact implements Observer {
	private String category; //business or personal
	private String name;
	private String phoneNumber;
	private String email;
	//private String linkedInURL; //in business contacts only
	private boolean changed;

	//public abstract Contact(); //factory method is the constructor

	public String getCategory() {return category;}
	public String getName() {return name;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	//public String getLinkedInURL() {return linkedInURL; //in business contact only}

	public void setCategory(String newCat) {category = newCat;} //will need to create new object if the object is now a business contact}
	public void setName(String newName) {name = newName;}
	public void setPhoneNumber(String newNumber) {phoneNumber = newNumber;}
	public void setEmail(String newEmail) {email = newEmail;}
	//public void setLinkedInURL(String newURL) {linkedInURL = newURL; //in business contact only}

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