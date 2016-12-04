package com.example.intouch;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Issac on 11/19/2016.
 */

public class SearchResultsActivity extends AppCompatActivity {

    Database currentDatabase = new Database();
    static ArrayList<Contact> foundContacts = new ArrayList<Contact>();
    ArrayList<Contact> localList = new ArrayList<Contact>();
    ListView resultView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        resultView = (ListView)findViewById(R.id.searchList);
        registerForContextMenu(resultView);


        Intent intent = getIntent();
        Bundle searchBundle = intent.getExtras();
        String query = searchBundle.getString("SEARCH_QUERY");
        localList = searchBundle.getParcelableArrayList("LOCAL_LIST");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Socket socketConnection = new Socket("128.83.192.235", 6666);
            ObjectOutputStream clientOutputStream = new
                    ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new
                    ObjectInputStream(socketConnection.getInputStream());

            clientOutputStream.writeObject(new InputObject(2, null));
            currentDatabase = ((OutputObject)clientInputStream.readObject()).getDB();

            clientOutputStream.close();
            clientInputStream.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        //currentDatabase.allContacts = searchBundle.getParcelableArrayList("DATABASE");
        
        foundContacts = currentDatabase.searchContacts(query);
        Iterator<Contact> remover = foundContacts.iterator();
        while(remover.hasNext()) {
            Contact comp =  remover.next();
            for(Contact c : localList) {
                if(c.getSignature() == comp.getSignature()) {
                    remover.remove();
                }
            }
        }
        ArrayList<String> contactNames = new ArrayList<String>();
        for(Contact c : foundContacts) {
            contactNames.add(c.getName());
        }
        if(contactNames.isEmpty()) {
            contactNames.add("Sorry, we couldn't find anyone with that information");
        }

        ArrayAdapter<String> searchAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        resultView.setAdapter(searchAdapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
            case R.id.view_contact:
                int index = info.position;
                Contact currentContact = foundContacts.get(index);
                Intent intent = new Intent(this, ProfileViewActivity.class);
                Bundle contactInfo = new Bundle();
                contactInfo.putParcelable("CONTACT", currentContact);
                intent.putExtras(contactInfo);
                startActivity(intent);
                return true;
            case R.id.add_contact:
                index = info.position;
                intent = new Intent(this, UploadSearchActivity.class);
                localList.add(foundContacts.get(index));
                intent.putParcelableArrayListExtra("UPDATED_LOCAL_LIST", localList);
                UploadSearchActivity.added = true;
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void pullAll(View view) {
        for(Contact c : foundContacts) {
            localList.add(c);
        }
        Intent intent = new Intent(this, UploadSearchActivity.class);
        intent.putParcelableArrayListExtra("UPDATED_LOCAL_LIST", localList);
        UploadSearchActivity.added = true;
        startActivity(intent);
    }
}
