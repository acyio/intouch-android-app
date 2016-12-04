package com.example.ee461l_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InitializationActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {} //back button technically breaks our system here, I've disabled
    //it by making it do nothing

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
        TextView requiredText = (TextView) findViewById(R.id.required_text);
        String nameString = name.getText().toString();
        String phoneString = phonenumber.getText().toString();
        String emailString = email.getText().toString();
        if (!(nameString.equals("") || phoneString.equals("") || emailString.equals(""))) {
            //if all required strings were filled
            String linkedString = linkedIn.getText().toString();
            String personalBusiness;
            if (linkedString.equals("")) {
                personalBusiness = "personal";
            } else {
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
        else {
            requiredText.setTextColor(Color.RED);
            requiredText.setText("Not all required fields were filled\n(Name, Phone Number, Email Address)");
        }


    }
}
