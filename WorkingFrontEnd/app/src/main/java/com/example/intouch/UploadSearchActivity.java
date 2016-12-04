package com.example.intouch;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UploadSearchActivity extends AppCompatActivity {
    public static boolean initialized = false;
    public static boolean done = false;
    public static boolean added = false;
    public static boolean update = false;
    static PersonalContact currentPContact;
    static BusinessContact currentBContact;
    static Database currentDatabase = new Database();
    public static boolean currentCat; //true = personal, false = business

    private void databaseFill() {
        PersonalContact Alice = new PersonalContact("personal", "Alice", "123-456-7890", "Alice@gmail.com");
        Alice.generateUniqueSignature(currentDatabase);
        PersonalContact Bob = new PersonalContact("personal", "Bob", "012-451-4143", "Bob@gmail.com");
        Bob.generateUniqueSignature(currentDatabase);
        PersonalContact Callie = new PersonalContact("personal", "Callie", "111-111-1111", "Callie@gmail.com");
        Callie.generateUniqueSignature(currentDatabase);
        PersonalContact Dan = new PersonalContact("personal", "Dan", "222-222-2222", "Dan@gmail.com");
        Dan.generateUniqueSignature(currentDatabase);
        currentDatabase.addContact(Alice);
        currentDatabase.addContact(Bob);
        currentDatabase.addContact(Callie);
        currentDatabase.addContact(Dan);

        try {
            Socket socketConnection = new Socket("128.83.192.235", 6666);
            ObjectOutputStream clientOutputStream = new
                    ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new
                    ObjectInputStream(socketConnection.getInputStream());

            clientOutputStream.writeObject(new InputObject(0, (Contact)Alice));
            currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();
            clientOutputStream.writeObject(new InputObject(0, (Contact)Bob));
            currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();
            clientOutputStream.writeObject(new InputObject(0, (Contact)Callie));
            currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();
            clientOutputStream.writeObject(new InputObject(0, (Contact)Dan));
            currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();

            clientOutputStream.close();
            clientInputStream.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!initialized && !done) {
            //databaseFill();
            Intent intent = new Intent(this, InitializationActivity.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        if(initialized && !done) {
            Intent uploadIntent = getIntent();
            Bundle contactInfo = uploadIntent.getExtras();
            Contact newPerson = contactInfo.getParcelable("NEW_PERSON");
            newPerson.generateUniqueSignature(currentDatabase);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Socket socketConnection = new Socket("128.83.192.235", 6666);
                ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
                ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());

                clientOutputStream.writeObject(new InputObject(0, (Contact)newPerson));
                currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();

                clientOutputStream.close();
                clientInputStream.close();

            } catch (Exception e) {
                System.out.println(e);
            }


            if (newPerson.getCategory().equals("personal")) {
                currentPContact = (PersonalContact)newPerson;
                currentPContact.newLocalList();
                currentCat = true;

            } else {
                currentBContact = (BusinessContact)newPerson;
                currentBContact.newLocalList();
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
        if(update) {
            Intent updateIntent = getIntent();
            Bundle updateInfo = updateIntent.getExtras();
            Contact updateContact = updateInfo.getParcelable("UPDATED_CONTACT");

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Socket socketConnection = new Socket("128.83.192.235", 6666);
                ObjectOutputStream clientOutputStream = new
                        ObjectOutputStream(socketConnection.getOutputStream());
                ObjectInputStream clientInputStream = new
                        ObjectInputStream(socketConnection.getInputStream());

                clientOutputStream.writeObject(new InputObject(1, updateContact));
                currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();

                clientOutputStream.close();
                clientInputStream.close();

            } catch (Exception e) {
                System.out.println(e);
            }


            if(currentCat) {
                currentPContact.setName(updateContact.getName());
                currentPContact.setPhoneNumber(updateContact.getPhoneNumber());
                currentPContact.setEmail(updateContact.getEmail());
            }
            else {
                currentBContact.setName(updateContact.getName());
                currentBContact.setPhoneNumber(updateContact.getPhoneNumber());
                currentBContact.setEmail(updateContact.getEmail());
                currentBContact.setLinkedInURL(((BusinessContact)updateContact).getLinkedInURL());
            }
            update = false;
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
        TextView greetingView = (TextView) findViewById(R.id.greeting_view);
        greetingView.setTextSize(20);
        if(currentCat) {
            if(currentPContact != null) {
                greetingView.setText("Welcome " + currentPContact.getName() + ", feel free to search our database, " +
                        "view your contacts, or edit your information");
            }
        }
        else {
            if(currentBContact != null) {
                greetingView.setText("Welcome " + currentBContact.getName() + ", feel free to search our database, " +
                        "view your contacts, or edit your information");
            }

        }

        handleIntent(getIntent());


    }

    public void uploadContact(View view) {
        Intent intent = new Intent(this, ViewInformationActivity.class);
        Bundle contactInfo = new Bundle();
        if(currentCat) {
            contactInfo.putParcelable("CURRENT_INFO", currentPContact);
        }
        else {
            contactInfo.putParcelable("CURRENT_INFO", currentBContact);
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