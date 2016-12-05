package com.example.intouch;
import java.io.Serializable;
public class InputObject implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int opcode;
    protected Contact c;
    public InputObject(int op, Contact c){
        this.opcode = op;
        this.c = c;
    }
}
        
