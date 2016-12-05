package com.example.intouch;
public class Factory {
    public static Contact createContact(String name, String num, String email) {
        return new PersonalContact("personal", name, num, email);
    }
    public static Contact createBusinessContact(String name, String num, String email, String url){
        return new BusinessContact("business", name, num, email, url);
    }
}
