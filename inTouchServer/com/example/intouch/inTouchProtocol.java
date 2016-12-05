package com.example.intouch;
import java.net.*;
import java.io.*;

public class inTouchProtocol {
    private static final int NEWCONTACT = 0;
    private static final int UPDATEPERSONAL = 1;
    private static final int REQUESTDATABASE = 2;

    public synchronized OutputObject processInput(InputObject input, Database db){
        int request = input.opcode;
        Contact c = input.c;
        OutputObject output;

        if (request == NEWCONTACT){
            db.addContact(c);
            output = new OutputObject(true, db);
        } 
        else if (request == UPDATEPERSONAL){
            int index = db.findInDatabase(c.getSignature());
            db.allContacts.set(index, c);
            output = new OutputObject(true, db);
        }
        else if (request == REQUESTDATABASE){
            output = new OutputObject(true, db);
        }   
        else{
            output = new OutputObject(false, null);
        }
        return output;
    }

}
