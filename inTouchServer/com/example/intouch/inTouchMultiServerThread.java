package com.example.intouch;
import java.net.*;
import java.io.*;

public class inTouchMultiServerThread extends Thread{
    private Socket socket = null;
    protected static Database db; 
    public inTouchMultiServerThread(Socket socket, Database db) {
        super("inTouchMultiServerThread");
        this.socket = socket;
        this.db = db;
    }
    
    public void run() {

        try (
                ObjectInputStream serverInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
         ) {
            InputObject input;
            OutputObject output;
            inTouchProtocol itp = new inTouchProtocol();
            

            while ((input = (InputObject)serverInputStream.readObject()) != null) {
                output = itp.processInput(input, db);
                db = output.db;
                serverOutputStream.writeObject(output);
            }
            socket.close();
            serverInputStream.close();
            serverOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Database getDB(){ return db;}
}
