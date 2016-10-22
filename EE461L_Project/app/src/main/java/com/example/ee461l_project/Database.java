import java.util.*;
package com.example.ee461l_project;

public class Database {
	private ArrayList<Contact> allContacts;

	public Database(){
		allContacts = new ArrayList<Contact>;
	}


	public boolean isInDatabase(Contact c){
		for (int i = 0; i < allContacts.size(); i++){
			if (c.getName().equals(allContacts.get(i).getName()))
				return true;
		}
		return false;
	}

	public boolean addContact(Contact c){
		if (isInDatabase) return false;
		allContacts.add(c);
		return true;
	}

	public boolean deleteContact(Contact c){
		if (!isInDatabase) return false;
		int i = 0;
		while (!c.getName().equals(allContacts.get(i).getName()))
			i++;
		allContacts.remove(i);
		return true;
	}

	public boolean push(){
		//automatically update each contact in each node's list upon change
		//this is the notifyObservers method
		for(Contact c : allContacts) {
			c.update();
			notify();
		}
	}

	public boolean pull(){
		//poll each node for updated info; update master list
	}

}