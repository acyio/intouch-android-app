package com.example.ee461l_project;

//since contact is abstract, only instantiates while returning, since contact cannot be instantiated
public class Factory {
	public Contact createContact(String name, String num, String email) {
		return new PersonalContact("personal", name, num, email);
	}
	public Contact createBusinessContact(String name, String num, String email, String url){
	   return new BusinessContact("business", name, num, email, url);
	}
}