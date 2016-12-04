package com.example.ee461l_project;

//since contact is abstract, only instantiates while returning, since contact cannot be instantiated
public class Factory {
	public static Contact createContact(String name, String num, String email) {
		return new PersonalContact("personal", name, num, email);
	}
	public static Contact createBusinessContact(String name, String num, String email, String url){
		return new BusinessContact("business", name, num, email, url);
	}
}