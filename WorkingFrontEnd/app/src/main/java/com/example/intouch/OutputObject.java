package com.example.intouch;

import java.io.Serializable;

/**
 * Created by Shan on 12/4/2016.
 */

public class OutputObject implements Serializable{
    protected boolean success;
    protected Database db;
    private static final long serialVersionUID = 1L;
    public OutputObject(boolean success, Database db){
        this.success = success;
        this.db = db;
    }
    public Database getDB() {return db;}
}
