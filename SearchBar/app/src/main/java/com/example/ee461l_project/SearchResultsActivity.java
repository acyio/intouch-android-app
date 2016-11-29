package com.example.ee461l_project;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
	ListView resultView;

	/*
	 * private void databaseFill() { PersonalContact Alice = new
	 * PersonalContact(); Alice.setName("Alice");
	 * Alice.setEmail("Alice@gmail.com"); Alice.setPhoneNumber("123-456-7890");
	 * PersonalContact Bob = new PersonalContact(); Bob.setName("Bob");
	 * Bob.setEmail("Bob@gmail.com"); Bob.setPhoneNumber("012-451-4143");
	 * PersonalContact Callie = new PersonalContact(); Callie.setName("Callie");
	 * Callie.setEmail("Callie@gmail.com");
	 * Callie.setPhoneNumber("111-111-1111"); PersonalContact Dan = new
	 * PersonalContact(); Dan.setName("Dan"); Dan.setEmail("Dan@gmail.com");
	 * Dan.setPhoneNumber("222-222-2222"); currentDatabase.addContact(Alice);
	 * currentDatabase.addContact(Bob); currentDatabase.addContact(Callie);
	 * currentDatabase.addContact(Dan); }
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);

		resultView = (ListView) findViewById(R.id.searchList);
		registerForContextMenu(resultView);

		// databaseFill();
		Intent intent = getIntent();
		String query = intent.getStringExtra(UploadSearchActivity.EXTRA_MESSAGE);

		foundContacts = currentDatabase.searchContacts(query);
		ArrayList<String> contactNames = new ArrayList<String>();
		for (Contact c : foundContacts) {
			contactNames.add(c.getName());
		}
		if (contactNames.isEmpty()) {
			contactNames.add("Sorry, we couldn't find anyone with that information");
		}

		ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				contactNames);
		resultView.setAdapter(searchAdapter);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.view_contact:
			int index = info.position;
			Contact currentContact = foundContacts.get(index);
			Intent intent = new Intent(this, ProfileViewActivity.class);
			Bundle contactInfo = new Bundle();
			contactInfo.putString("NAME", currentContact.getName());
			contactInfo.putString("PHONE", currentContact.getPhoneNumber());
			contactInfo.putString("EMAIL", currentContact.getEmail());
			intent.putExtras(contactInfo);
			startActivity(intent);
			return true;
		case R.id.add_contact:
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
