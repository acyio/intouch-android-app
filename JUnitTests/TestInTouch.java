package com.example.ee461l_project;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestInTouch {

   private Database db;
   
   @Before
   public void setUp() throws Exception {
      db = new Database();
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testAddPersonal() {
       db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
       assertEquals(db.numInDatabase(), 1);
       PersonalContact tmp = (PersonalContact) db.getContact("shan");
       assertEquals(tmp.category, "personal");
       assertEquals(tmp.name, "shan");
       assertEquals(tmp.phoneNumber, "4407086253");
       assertEquals(tmp.email, "smkanvinde@utexas.edu");
   }

   @Test
   public void testAddBusiness() {
      db.addContact(Factory.createBusinessContact("shan", "4407086253", "smkanvinde@utexas.edu", "smkanvinde"));
      assertEquals(db.numInDatabase(), 1);
      BusinessContact tmp = (BusinessContact) db.getContact("shan");
      assertEquals(tmp.category, "business");
      assertEquals(tmp.name, "shan");
      assertEquals(tmp.phoneNumber, "4407086253");
      assertEquals(tmp.email, "smkanvinde@utexas.edu");
      assertEquals(tmp.linkedInURL, "smkanvinde");
   }
   
   @Test
   public void testAddBoth() {
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.addContact(Factory.createBusinessContact("mukund", "1234567890", "mukund@utexas.edu", "kuthalam"));
      assertEquals(db.numInDatabase(), 2);
      PersonalContact tmp = (PersonalContact) db.getContact("shan");
      assertEquals(tmp.category, "personal");
      assertEquals(tmp.name, "shan");
      assertEquals(tmp.phoneNumber, "4407086253");
      assertEquals(tmp.email, "smkanvinde@utexas.edu");
      BusinessContact tmp2 = (BusinessContact) db.getContact("mukund");
      assertEquals(tmp2.category, "business");
      assertEquals(tmp2.name, "mukund");
      assertEquals(tmp2.phoneNumber, "1234567890");
      assertEquals(tmp2.email, "mukund@utexas.edu");
      assertEquals(tmp2.linkedInURL, "kuthalam");
   }
   
   @Test
   public void testDelete() {
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.addContact(Factory.createBusinessContact("mukund", "1234567890", "mukund@utexas.edu", "kuthalam"));
      assertEquals(db.numInDatabase(), 2);
      db.deleteContact("shan");
      assertEquals(db.numInDatabase(), 1);
   }
   
   @Test
   public void testIsInDatabase() {
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.addContact(Factory.createBusinessContact("mukund", "1234567890", "mukund@utexas.edu", "kuthalam"));
      assertTrue(db.isInDatabase("shan"));
   }
   
   @Test
   public void testChangeBusinessToPersonal() { //be sure to assert if updated
      db.addContact(Factory.createBusinessContact("mukund", "1234567890", "mukund@utexas.edu", "kuthalam"));
      db.changeBusinessToPersonal("mukund");
      PersonalContact tmp = (PersonalContact) db.getContact("mukund");
      assertEquals(tmp.category, "personal");
      assertEquals(tmp.name, "mukund");
      assertEquals(tmp.phoneNumber, "1234567890");
      assertEquals(tmp.email, "mukund@utexas.edu");
      assertTrue(tmp.changed);
   }
   
   @Test
   public void testChangePersonalToBusiness() { //be sure to assert if updated
      db.addContact(Factory.createContact("mukund", "1234567890", "mukund@utexas.edu"));
      db.changePersonalToBusiness("mukund", "kuthalam");
      BusinessContact tmp = (BusinessContact) db.getContact("mukund");
      assertEquals(tmp.category, "business");
      assertEquals(tmp.name, "mukund");
      assertEquals(tmp.phoneNumber, "1234567890");
      assertEquals(tmp.email, "mukund@utexas.edu");
      assertEquals(tmp.linkedInURL, "kuthalam");
      assertTrue(tmp.changed);
   }

   @Test
   public void testSetName() { //be sure to assert if updated
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.getContact("shan").setName("shantanu");
      assertNotNull(db.getContact("shantanu"));
      assertTrue(db.getContact("shantanu").changed);
   }
   
   @Test
   public void testSetPhoneNumber() { //be sure to assert if updated
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.getContact("shan").setPhoneNumber("1234567890");
      Contact tmp = db.getContact("shan");
      assertEquals(tmp.phoneNumber, "1234567890");
      assertTrue(tmp.changed);
   }
   
   @Test
   public void testSetEmail() { //be sure to assert if updated
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      db.getContact("shan").setEmail("smkanvinde@gmail.com");
      Contact tmp = db.getContact("shan");
      assertEquals(tmp.email, "smkanvinde@gmail.com");
      assertTrue(tmp.changed);
   }
   
   @Test
   public void testSetLinkedInURL() { //be sure to assert if updated
      db.addContact(Factory.createBusinessContact("mukund", "1234567890", "mukund@utexas.edu", "kuthalam"));
      ((BusinessContact) db.getContact("mukund")).setLinkedInURL("mukundankuthalam");
      Contact tmp = db.getContact("mukund");
      assertEquals(((BusinessContact)tmp).linkedInURL, "mukundankuthalam");
      assertTrue(tmp.changed);
   }
   
   @Test
   public void testGetCategory() { 
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      String cat = db.getContact("shan").getCategory();
      assertEquals(cat, "personal");
   }
   
   @Test
   public void testGetName() { 
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      String name = db.getContact("shan").getName();
      assertEquals(name, "shan");
   }
   
   @Test
   public void testGetPhoneNumber() { 
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      String num = db.getContact("shan").getPhoneNumber();
      assertEquals(num, "4407086253");
   }
   
   @Test
   public void testGetEmail() { 
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      String email = db.getContact("shan").getEmail();
      assertEquals(email, "smkanvinde@utexas.edu");
   }
   
   @Test
   public void testGetContact() {
      db.addContact(Factory.createContact("shan", "4407086253", "smkanvinde@utexas.edu"));
      Contact tmp = db.getContact("shan");
      assertEquals(tmp.category, "personal");
      assertEquals(tmp.name, "shan");
      assertEquals(tmp.phoneNumber, "4407086253");
      assertEquals(tmp.email, "smkanvinde@utexas.edu");
   }

}
