package com.example.intouch;
import java.util.ArrayList;
import java.io.Serializable;
public abstract class Contact implements Observer, Serializable {
    protected String category; //business or personal
    protected String name;
    protected String phoneNumber;
    protected String email;

    protected boolean changed;
    protected ArrayList<Contact> localList;
    protected int signature;
    private static final long serialVersionUID = 1L;

    public Contact(String cat, String name, String number, String email){
        this.category = cat;
        this.name = name;
        this.phoneNumber = number;
        this.email = email;
        changed = false;
        this.localList = new ArrayList<Contact>();
        this.signature = (int)(Math.random()*10000);

    }
   
    public String getCategory() {return category;}
    public String getName() {return name;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
    public ArrayList<Contact> getLocalList() {return localList;}
    public int getSignature() { return signature;}

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
        if (index != -1) significantEmail = significantEmail.substring(0, index);
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

    public void generateUniqueSignature(Database db){
        ArrayList<Contact> theList = db.allContacts;
        for (int i = 0; i < theList.size(); i++){ //iterate through contacts
            if (this.signature == theList.get(i).signature){ //check collision
                this.signature = (int)(Math.random()*10000); //regenerate if collision
                i = 0; //check list again
            }
        }
    }
}
     
