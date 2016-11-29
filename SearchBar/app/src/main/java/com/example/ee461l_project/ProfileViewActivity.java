package com.example.ee461l_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfileViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view);

		Intent intent = getIntent();
		Bundle contactInfo = intent.getExtras();
		String name = "Name: " + contactInfo.getString("NAME");
		String phone = "Phone Number: " + contactInfo.getString("PHONE");
		String email = "Email Address: " + contactInfo.getString("EMAIL");
		ArrayList<String> profile = new ArrayList<String>();
		profile.add(name);
		profile.add(phone);
		profile.add(email);

		ListView profileView = (ListView) findViewById(R.id.profile_view);
		ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				profile);
		profileView.setAdapter(profileAdapter);
	}
	
}
