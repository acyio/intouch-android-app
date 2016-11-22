package com.example.ee461l_project;

import java.util.ArrayList;

public class Database {
	protected ArrayList<Contact> allContacts;

	public Database(){
		allContacts = new ArrayList<Contact>();
	}


	public boolean isInDatabase(String name){
		for (int i = 0; i < allContacts.size(); i++){
			if (name.equals(allContacts.get(i).getName()))
				return true;
		}
		return false;
	}

	public boolean addContact(Contact c){
		if (isInDatabase(c.getName())) return false;
		allContacts.add(c);
		return true;
	}
	
	public int findInDatabase(String name){
	   if (!isInDatabase(name)) return -1;
	   int i = 0;
      while (!name.equals(allContacts.get(i).getName()))
         i++;
      return i;
	}

	public boolean deleteContact(String name){
		int position = findInDatabase(name);
		if (position == -1) return false;
		allContacts.remove(position);
		return true;
	}
	
	public int numInDatabase(){
	   return allContacts.size();
	}
	
	public boolean changePersonalToBusiness(String name, String URL){
	   PersonalContact temp = (PersonalContact) getContact(name);
      if (temp == null) return false;
      BusinessContact newContact = (BusinessContact) Factory.createBusinessContact(temp.name, temp.phoneNumber, temp.email, URL);
      newContact.changed = true;
      deleteContact(name);
      addContact(newContact);
      
      return true;
	   
	}
	
	public boolean changeBusinessToPersonal(String name){
	   BusinessContact temp = (BusinessContact) getContact(name);
	   if (temp == null) return false;
	   PersonalContact newContact = (PersonalContact) Factory.createContact(temp.name, temp.phoneNumber, temp.email);
	   newContact.changed = true;
	   deleteContact(name);
	   addContact(newContact);
	   return true;
	}
	
	public Contact getContact(String name){
	   int position = findInDatabase(name);
	   if (position == -1) return null;
	   return allContacts.get(position);
	}

	public boolean push(){
		//automatically update each contact in each node's list upon change
		//this is the notifyObservers method
		try {
			for(Contact c : allContacts) {
				c.update();
				notify();
			}
			return true;
		}
		catch (Exception e) {
			return false; //Something went wrong
		}
	}

	public boolean pull(){
		//poll each node for updated info; update master list
		try {
			return true;
		}
		catch (Exception e) {
			return false; //Something went wrong
		}
	}
	
	@Override
	public String toString(){
	   String result = "";
	   
	   for (Contact c : allContacts){
	      result += "\nCategory: " + c.getCategory() +
	            "\nName: " + c.getName() +
	            "\nPhone #: " + c.getPhoneNumber() + 
	            "\nEmail: " + c.getEmail();
	      if (c.getCategory().equals("business"))
	         result += "\nLinkedIn URL: " + ((BusinessContact) c).getLinkedInURL();
	      result += "\n----------\n";
	   }
	   
	   return result;
	}

}