package com.example.ee461l_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewInformationActivity extends AppCompatActivity {
    private String name;
    private String phone;
    private String email;
    private String URL;
    private String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);

        Intent intent = getIntent();
        Bundle contactInfo = intent.getExtras();
        name = contactInfo.getString("CURRENT_NAME");
        String view_name = "Name: " + name;
        phone = contactInfo.getString("CURRENT_PHONE");
        String view_phone = "Phone Number: " + phone;
        email = contactInfo.getString("CURRENT_EMAIL");
        String view_email = "Email Address: " + email;
        cat = contactInfo.getString("CURRENT_CAT");
        URL = "";
        String view_URL = "";
        if(cat.equals("business")) {
            URL = contactInfo.getString("CURRENT_URL");
            view_URL = "LinkedIn URL: " + URL;
        }
        String view_cat = "Current Category: " + cat;
        ArrayList<String> profile = new ArrayList<String>();
        profile.add(view_name);
        profile.add(view_phone);
        profile.add(view_email);
        if(cat.equals("business")) {
            profile.add(view_URL);
        }
        profile.add(view_cat);

        ListView myProfileView = (ListView)findViewById(R.id.my_profile_view);
        ArrayAdapter<String> profileAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profile);
        myProfileView.setAdapter(profileAdapter);

    }

    public void updateProfile(View view) {
        if(cat.equals("personal")) {
            Intent intent = new Intent(this, UpdatePersonalProfileActivity.class);
            Bundle currentInfo = new Bundle();
            currentInfo.putString("OLD_P_NAME", name);
            currentInfo.putString("OLD_P_PHONE", phone);
            currentInfo.putString("OLD_P_EMAIL", email);
            intent.putExtras(currentInfo);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, UpdateBusinessProfileActivity.class);
            Bundle currentInfo = new Bundle();
            currentInfo.putString("OLD_B_NAME", name);
            currentInfo.putString("OLD_B_PHONE", phone);
            currentInfo.putString("OLD_B_EMAIL", email);
            currentInfo.putString("OLD_B_URL", URL);
            intent.putExtras(currentInfo);
            startActivity(intent);
        }
    }




}
