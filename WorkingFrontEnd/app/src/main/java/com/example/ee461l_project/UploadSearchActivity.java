package com.example.ee461l_project;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

public class UploadSearchActivity extends AppCompatActivity {
    public static boolean initialized = false;
    public static boolean done = false;
    public static boolean added = false;
    static PersonalContact currentPContact;
    static BusinessContact currentBContact;
    static Database currentDatabase = new Database();
    public static boolean currentCat; //true = personal, false = business

    private void databaseFill() {
        PersonalContact Alice = new PersonalContact("personal", "Alice", "123-456-7890", "Alice@gmail.com");
        PersonalContact Bob = new PersonalContact("personal", "Bob", "012-451-4143", "Bob@gmail.com");
        PersonalContact Callie = new PersonalContact("personal", "Callie", "111-111-1111", "Callie@gmail.com");
        PersonalContact Dan = new PersonalContact("personal", "Dan", "222-222-2222", "Dan@gmail.com");
        currentDatabase.addContact(Alice);
        currentDatabase.addContact(Bob);
        currentDatabase.addContact(Callie);
        currentDatabase.addContact(Dan);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!initialized && !done) {
            databaseFill();
            Intent intent = new Intent(this, InitializationActivity.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        if(initialized && !done) {
            Intent uploadIntent = getIntent();
            Bundle contactInfo = uploadIntent.getExtras();
            String name = contactInfo.getString("NEW_NAME");
            String phone = contactInfo.getString("NEW_PHONE");
            String email = contactInfo.getString("NEW_EMAIL");
            String cat = contactInfo.getString("NEW_CAT");
            String linked = "";
            if (cat.equals("business")) {
                linked = contactInfo.getString("NEW_LINKED_IN");
            }
            if (cat.equals("personal")) {
                currentPContact = new PersonalContact(cat, name, phone, email);
                //currentDatabase.addContact(currentPContact);
                currentCat = true;
            } else {
                currentBContact = new BusinessContact(cat, name, phone, email, linked);
                //currentDatabase.addContact(currentBContact);
                currentCat = false;
            }
            done = true;
        }
        if(added) {
            Intent addedIntent = getIntent();
            if(currentCat) {
                currentPContact.localList = addedIntent.getParcelableArrayListExtra("UPDATED_LOCAL_LIST");
            }
            else {
                currentBContact.localList = addedIntent.getParcelableArrayListExtra("UPDATED_LOCAL_LIST");
            }
            added = false;
        }
        Intent loadIntent = getIntent();
        if(loadIntent.getParcelableExtra("CURRENT_CONTACT") != null) {
            Contact current = loadIntent.getParcelableExtra("CURRENT_CONTACT");
            if(current.category.equals("personal")) {
                currentCat = true;
                currentPContact = loadIntent.getParcelableExtra("CURRENT_CONTACT");
            }
            else {
                currentCat = false;
                currentBContact = loadIntent.getParcelableExtra("CURRENT_CONTACT");
            }
        }
        setContentView(R.layout.activity_upload_search);
        handleIntent(getIntent());


    }

    public void uploadContact(View view) {
        Intent intent = new Intent(this, ViewInformationActivity.class);
        Bundle contactInfo = new Bundle();
        if(currentCat) {
            contactInfo.putString("CURRENT_NAME", currentPContact.getName());
            contactInfo.putString("CURRENT_PHONE", currentPContact.getPhoneNumber());
            contactInfo.putString("CURRENT_EMAIL", currentPContact.getEmail());
            contactInfo.putString("CURRENT_CAT", currentPContact.getCategory());
        }
        else {
            contactInfo.putString("CURRENT_NAME", currentBContact.getName());
            contactInfo.putString("CURRENT_PHONE", currentBContact.getPhoneNumber());
            contactInfo.putString("CURRENT_EMAIL", currentBContact.getEmail());
            contactInfo.putString("CURRENT_CAT", currentBContact.getCategory());
            contactInfo.putString("CURRENT_URL", currentBContact.getLinkedInURL());
        }

        intent.putExtras(contactInfo);
        UploadSearchActivity.initialized = true;
        startActivity(intent);
    }

    public void viewContacts(View view) {
        Intent intent = new Intent(this, ViewYourContactsActivity.class);
        if(currentCat) {
            intent.putParcelableArrayListExtra("YOUR_LIST", currentPContact.localList);
        }
        else {
            intent.putParcelableArrayListExtra("YOUR_LIST", currentBContact.localList);
        }
        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH != null && intent.getAction() != null) {
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                String query = intent.getStringExtra(SearchManager.QUERY);
                searchData(query);
            }
        }
        /*
        Intent restartIntent = new Intent(this, UploadSearchActivity.class);
        if(currentCat) {
            restartIntent.putExtra("CURRENT_CONTACT", currentPContact);
        }
        else {
            restartIntent.putExtra("CURRENT_CONTACT", currentBContact);
        }
        startActivity(restartIntent);
        */

    }

    private void searchData(String query) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        Bundle searchBundle = new Bundle();
        searchBundle.putString("SEARCH_QUERY", query);
        if(currentCat) {
            searchBundle.putParcelableArrayList("LOCAL_LIST", currentPContact.localList);
        }
        else {
            searchBundle.putParcelableArrayList("LOCAL_LIST", currentBContact.localList);
        }
        searchBundle.putParcelableArrayList("DATABASE", currentDatabase.allContacts);
        intent.putExtras(searchBundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}