package com.example.ee461l_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewYourContactsActivity extends AppCompatActivity {

    ListView yourContactsView;
    static ArrayList<Contact> yourList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_contacts);

        yourContactsView = (ListView) findViewById(R.id.your_contacts);
        registerForContextMenu(yourContactsView);

        Intent intent = getIntent();
        yourList = intent.getParcelableArrayListExtra("YOUR_LIST");
        ArrayList<String> yourListNames = new ArrayList<String>();
        for(Contact c : yourList) {
            yourListNames.add(c.getName());
        }
        ArrayAdapter<String> viewAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, yourListNames);
        yourContactsView.setAdapter(viewAdapter);




    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.your_contact_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
            case R.id.view_contact_yours:
                int index = info.position;
                Contact currentContact = yourList.get(index);
                Intent intent = new Intent(this, ProfileViewActivity.class);
                Bundle contactInfo = new Bundle();
                contactInfo.putString("NAME", currentContact.getName());
                contactInfo.putString("PHONE", currentContact.getPhoneNumber());
                contactInfo.putString("EMAIL", currentContact.getEmail());
                if(currentContact.category.equals("business")) {
                    contactInfo.putString("URL", ((BusinessContact)currentContact).getLinkedInURL());
                }
                contactInfo.putString("CAT", currentContact.category);
                intent.putExtras(contactInfo);
                startActivity(intent);
                return true;
            case R.id.delete_contact:
                index = info.position;
                intent = new Intent(this, UploadSearchActivity.class);
                yourList.remove(index);
                intent.putParcelableArrayListExtra("UPDATED_LOCAL_LIST", yourList);
                UploadSearchActivity.added = true;
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteAll(View view) {
        yourList.clear();
        Intent intent = new Intent(this, UploadSearchActivity.class);
        intent.putParcelableArrayListExtra("UPDATED_LOCAL_LIST", yourList);
        UploadSearchActivity.added = true;
        startActivity(intent);
    }
}
