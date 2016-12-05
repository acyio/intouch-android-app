package com.example.intouch;
import java.io.Serializable;
public class OutputObject implements Serializable {
    private static final long serialVersionUID = 1L;
    protected boolean success;
    protected Database db;

    public OutputObject(boolean success, Database db){
        this.success = success;
        this.db = db;
    }
}
