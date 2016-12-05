package com.example.intouch;
import java.io.Serializable;
public class PersonalContact extends Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    public PersonalContact(String cat, String name, String number, String email) {
        super(cat, name, number, email);
    }

}
