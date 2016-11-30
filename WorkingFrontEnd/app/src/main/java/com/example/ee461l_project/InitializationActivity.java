package com.example.ee461l_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InitializationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization);


    }

    public void submitInfo(View view) {
        EditText name = (EditText) findViewById(R.id.name_edit);
        EditText phonenumber = (EditText) findViewById(R.id.phone_edit);
        EditText email = (EditText) findViewById(R.id.email_edit);
        EditText linkedIn = (EditText) findViewById(R.id.linked_in_edit);
        String nameString = name.getText().toString();
        String phoneString = phonenumber.getText().toString();
        String emailString = email.getText().toString();
        String linkedString = linkedIn.getText().toString();
        String personalBusiness;
        if(linkedString.equals("")) {
            personalBusiness = "personal";
        }
        else {
            personalBusiness = "business";
        }

        Intent intent = new Intent(this, UploadSearchActivity.class);
        Bundle contactInfo = new Bundle();
        contactInfo.putString("NEW_NAME", nameString);
        contactInfo.putString("NEW_PHONE", phoneString);
        contactInfo.putString("NEW_EMAIL", emailString);
        if(personalBusiness.equals("business")) {
            contactInfo.putString("NEW_LINKED_IN", linkedString);
        }
        contactInfo.putString("NEW_CAT", personalBusiness);
        intent.putExtras(contactInfo);
        UploadSearchActivity.initialized = true;
        startActivity(intent);
    }
}
