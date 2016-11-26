package com.example.ee461l_project;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Issac on 11/19/2016.
 */

public class SearchResultsActivity extends AppCompatActivity {
    Database currentDatabase = new Database();
    ArrayList<Contact> foundContacts = new ArrayList<Contact>();

    private void databaseFill() {
        PersonalContact Alice = new PersonalContact();
        Alice.setName("Alice");
        Alice.setEmail("Alice@gmail.com");
        Alice.setPhoneNumber("123-456-7890");
        PersonalContact Bob = new PersonalContact();
        Bob.setName("Bob");
        Bob.setEmail("Bob@gmail.com");
        Bob.setPhoneNumber("012-451-4143");
        PersonalContact Callie = new PersonalContact();
        Callie.setName("Callie");
        Callie.setEmail("Callie@gmail.com");
        Callie.setPhoneNumber("111-111-1111");
        PersonalContact Dan = new PersonalContact();
        Dan.setName("Dan");
        Dan.setEmail("Dan@gmail.com");
        Dan.setPhoneNumber("222-222-2222");
        currentDatabase.addContact(Alice);
        currentDatabase.addContact(Bob);
        currentDatabase.addContact(Callie);
        currentDatabase.addContact(Dan);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        databaseFill();
        /*
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        databaseFill();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            foundContacts = currentDatabase.searchContacts(query);
            ArrayList<String> foundNames = new ArrayList<String>();
            for(Contact c : foundContacts) {
                foundNames.add(c.getName());
            }
            ArrayAdapter<String> searchAdapter =
                    new ArrayAdapter<String>(this, R.layout.search_results, foundNames);
            ListView contactList = (ListView) findViewById(R.id.searchList);
            contactList.setAdapter(searchAdapter);

        }
        */
        Intent intent = getIntent();
        String query = intent.getStringExtra(UploadSearchActivity.EXTRA_MESSAGE);
        //String query = "Dan";

        //String[] dummyList = {"Alice", "Bob", "Callie", "Dan"};
        foundContacts = currentDatabase.searchContacts(query);
        ArrayList<String> contactNames = new ArrayList<String>();
        for(Contact c : foundContacts) {
            contactNames.add(c.getName());
        }
        if(contactNames.isEmpty()) {
            contactNames.add("Sorry, we couldn't find anyone with that information");
        }
        ArrayAdapter<String> searchAdapter =
                //new ArrayAdapter<String>(this, R.layout.activity_search_results, dummyList);
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        ListView contactList = (ListView) findViewById(R.id.searchList);
        contactList.setAdapter(searchAdapter);
        /*
        ArrayAdapter<String> myAdapter=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                query);
        ListView myList=(ListView)
                findViewById(R.id.listView);
        myList.setAdapter(myAdapter);
        */

    }
}
