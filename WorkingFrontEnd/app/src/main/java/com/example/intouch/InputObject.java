package com.example.intouch;

import java.io.Serializable;

/**
 * Created by Shan on 12/4/2016.
 */

public class InputObject implements Serializable{
    private static final long serialVersionUID = 1L;
    protected int opcode;
    protected Contact c;

    /*
    Opcodes:
        0 = add contact
        1 = update contact
        2 = request database
     */


    public InputObject(int op, Contact c){
        this.opcode = op;
        this.c = c;
    }
}
