package com.example.intouch;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewInformationActivity extends AppCompatActivity {
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);

        Intent intent = getIntent();

        //Bundle contactInfo = intent.getExtras();
        Bundle contactInfo = intent.getExtras();
        currentContact = contactInfo.getParcelable("CURRENT_INFO");
        String view_name = "Name: " + currentContact.getName();
        String view_phone = "Phone Number: " + currentContact.getPhoneNumber();
        String view_email = "Email Address: " + currentContact.getEmail();
        String view_URL = "";
        if(currentContact.getCategory().equals("business")) {
            view_URL = "LinkedIn URL: " + ((BusinessContact)currentContact).getLinkedInURL();
        }
        String view_cat = "Current Category: " + currentContact.getCategory();
        ArrayList<String> profile = new ArrayList<String>();
        profile.add(view_name);
        profile.add(view_phone);
        profile.add(view_email);
        if(currentContact.getCategory().equals("business")) {
            profile.add(view_URL);
        }
        profile.add(view_cat);

        ListView myProfileView = (ListView)findViewById(R.id.my_profile_view);
        ArrayAdapter<String> profileAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profile);
        myProfileView.setAdapter(profileAdapter);

    }

    public void updateProfile(View view) {
        if(currentContact.getCategory().equals("personal")) {
            Intent intent = new Intent(this, UpdatePersonalProfileActivity.class);
            Bundle currentInfo = new Bundle();
            currentInfo.putParcelable("OLD_P_CONTACT", currentContact);
            intent.putExtras(currentInfo);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, UpdateBusinessProfileActivity.class);
            Bundle currentInfo = new Bundle();
            currentInfo.putParcelable("OLD_B_CONTACT", currentContact);
            intent.putExtras(currentInfo);
            startActivity(intent);
        }
    }




}
