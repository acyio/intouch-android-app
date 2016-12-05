package com.example.intouch;
import java.net.*;
import java.io.*;

public class inTouchMultiServer {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        int portNumber = 6666;
        boolean listening = true;
        try(ServerSocket serverSocket = new ServerSocket(portNumber)) 
         {
          while(listening){
            new inTouchMultiServerThread(serverSocket.accept(), db).start();


         }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

