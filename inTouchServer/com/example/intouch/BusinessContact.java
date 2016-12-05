package com.example.intouch;
import java.io.Serializable;
public class BusinessContact extends Contact implements Serializable {
    protected String linkedInURL; //in business contacts only
    private static final long serialVersionUID = 1L;
    public BusinessContact(String cat, String name, String number, String email, String url){
        super(cat, name, number, email);
        this.linkedInURL = url;
    }


    public String getCategory() {return category;}
    public String getName() {return name;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
    public String getLinkedInURL() {return linkedInURL;} //in business contact only}

    public void setLinkedInURL(String newURL) {
        linkedInURL = newURL;
        this.changed = true;
    }


   }
