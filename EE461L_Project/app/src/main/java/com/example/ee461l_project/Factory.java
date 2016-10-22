import java.util.*;
package com.example.ee461l_project.EE461L_Project;

//since contact is abstract, only instantiates while returning, since contact cannot be instantiated
public class ContactFactory {
	public Contact createContact(String contactType) {
		if(contactType.equals("PersonalContact")) {
			return new PersonalContact();
		}
		else if (contactType.equals("BusinessContact")) {
			return new BusinessContact();
		}
	}
}