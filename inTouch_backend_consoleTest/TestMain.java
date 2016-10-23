package com.example.ee461l_project;

import java.util.Scanner;

public class TestMain extends Database {
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      boolean execute = true;
      Database db = new Database();
      while (execute){
         System.out.println("\nPlease choose an action." + 
               "\n\tA = add" + 
               "\n\tD = delete" + 
               "\n\tP = print" + 
               "\n\tE = exit");
         
         switch(sc.next()){
            case("A"):{
               boolean added = false;
               System.out.println("\npersonal or business?");
               String cat = sc.next();
               System.out.println("\nname?");
               String name = sc.next();
               System.out.println("\nnumber?");
               String num = sc.next();
               System.out.println("\nemail?");
               String email = sc.next();
               if (cat.equals("business")){
                  System.out.println("\nLinkedIn URL?");
                  String URL = sc.next();
                  added = db.addContact(new BusinessContact(cat, name, num, email, URL));
               } else {
                  added = db.addContact(new PersonalContact(cat, name, num, email));
               }
               if (added) System.out.println("\nsuccess");
               else System.out.println("\nfailure");
               break;
            }
            case ("D"):{
               boolean removed = false;
               System.out.println("\nname?");
               String name = sc.next();
               removed = db.deleteContact(name);
               if (removed) System.out.println("success");
               else System.out.println("not in database");
               break;
            }
            case ("P"):{
               System.out.println(db.toString());
               System.out.println("----------\nend of contacts\n----------");
               break;
            }
            case ("E"):{
               System.out.println("----------\ngoodbye\n----------");
               execute = false;
               break;
            }
         }
      }
      sc.close();
   }
}
