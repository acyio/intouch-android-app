package com.example.intouch;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Contact person = contactInfo.getParcelable("CONTACT");
        String category = person.getCategory();
        String name = "Name: " + person.getName();
        String phone = "Phone Number: " + person.getPhoneNumber();
        String email = "Email Address: " + person.getEmail();
        String URL = "";
        if(category.equals("business")) {
            URL = "LinkedIn URL: " + ((BusinessContact)person).getLinkedInURL();
        }

        ArrayList<String> profile = new ArrayList<String>();
        profile.add(name);
        profile.add(phone);
        profile.add(email);
        if(category.equals("business")) {
            profile.add(URL);
        }
        category = "Current Category: " + category;
        profile.add(category);


        ListView profileView = (ListView)findViewById(R.id.profile_view);
        ArrayAdapter<String> profileAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profile);
        profileView.setAdapter(profileAdapter);

    }
}
