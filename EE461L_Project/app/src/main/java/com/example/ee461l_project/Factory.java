package com.example.ee461l_project;

//since contact is abstract, only instantiates while returning, since contact cannot be instantiated
public class Factory {
	public Contact createContact(String contactType) {
		Contact contact = null; //A variable to hold the contact instantiated here
		if(contactType.equals("PersonalContact")) {
			contact = new PersonalContact();
			return contact;
		}
		else if (contactType.equals("BusinessContact")) {
			contact = new BusinessContact();
			return contact;
		}
		return contact;
	}
}