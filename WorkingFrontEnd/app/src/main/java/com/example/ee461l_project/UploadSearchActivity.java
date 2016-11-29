package com.example.ee461l_project;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;

public class UploadSearchActivity extends AppCompatActivity {
    public static boolean initialized = false;
    public static boolean done = false;
    PersonalContact currentPContact;
    BusinessContact currentBContact;
    public static boolean currentCat; //true = public, false = business

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!initialized && !done) {
            Intent intent = new Intent(this, InitializationActivity.class);
            startActivity(intent);
            finish();
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
                currentCat = true;
            } else {
                currentBContact = new BusinessContact(cat, name, phone, email, linked);
                currentCat = false;
            }
            done = true;
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

    /*public void viewContacts(View view) {
        Intent intent = new Intent(this, ViewContactActivity.class);
        startActivity(intent);
    }*/


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
    }

    private void searchData(String query) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("SEARCH_QUERY", query);
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