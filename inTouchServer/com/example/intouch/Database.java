package com.example.intouch;
import java.util.ArrayList;
import java.io.Serializable;
public class Database implements Serializable {
    protected ArrayList<Contact> allContacts;
    private static final long serialVersionUID = 1L;
    public Database(){
        allContacts = new ArrayList<Contact>();
    }


    public boolean isInDatabase(int signature){
        for (int i = 0; i < allContacts.size(); i++){
            if (signature == (allContacts.get(i).getSignature()))
                return true;
        }
        return false;
    }

    public boolean addContact(Contact c){
        if (isInDatabase(c.getSignature())) return false;
        allContacts.add(c);
        return true;
    }

    public int findInDatabase(int signature){
        if (!isInDatabase(signature)) return -1;
        int i = 0;
        while (signature != (allContacts.get(i).getSignature()))
            i++;
        return i;
    }

    public boolean deleteContact(int signature){
        int position = findInDatabase(signature);
        if (position == -1) return false;
        allContacts.remove(position);
        return true;
    }

    public int numInDatabase(){
        return allContacts.size();
    }

    public boolean changePersonalToBusiness(int signature, String URL){
        PersonalContact temp = (PersonalContact) getContact(signature);
        if (temp == null) return false;
        BusinessContact newContact = (BusinessContact) Factory.createBusinessContact(temp.name, temp.phoneNumber, temp.email, URL);
        newContact.changed = true;
        deleteContact(signature);
        addContact(newContact);

        return true;

    }

    public boolean changeBusinessToPersonal(int signature){
        BusinessContact temp = (BusinessContact) getContact(signature);
        if (temp == null) return false;
        PersonalContact newContact = (PersonalContact) Factory.createContact(temp.name, temp.phoneNumber, temp.email);
        newContact.changed = true;
        deleteContact(signature);
        addContact(newContact);
        return true;
    }

    public Contact getContact(int signature){
        int position = findInDatabase(signature);
        if (position == -1) return null;
        return allContacts.get(position);
    }

    public boolean push(){
        try {
            for(Contact c : allContacts) {
                c.update();
                notify();
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean pull(){
        try {
            return true;
        }
        catch (Exception e) {
            return false;
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

    public ArrayList<Contact> searchContacts(String query) {
        ArrayList<Contact> foundContacts = new ArrayList<Contact>();
        for(Contact c : allContacts) {
            if(c.contactMatch(query)) {
                foundContacts.add(c);
            }
        }
        return foundContacts;
    }

}
