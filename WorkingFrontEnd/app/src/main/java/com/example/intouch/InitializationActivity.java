package com.example.intouch;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            Contact newPerson;
            if(personalBusiness.equals("personal")) {
                newPerson = new PersonalContact(personalBusiness, nameString, phoneString, emailString);
            }
            else {
                newPerson = new BusinessContact(personalBusiness, nameString, phoneString, emailString, linkedString);
            }
            Intent intent = new Intent(this, UploadSearchActivity.class);
            Bundle contactInfo = new Bundle();
            contactInfo.putParcelable("NEW_PERSON", newPerson);
            intent.putExtras(contactInfo);
            UploadSearchActivity.initialized = true;
            startActivity(intent);
        }
        else {
            requiredText.setTextColor(Color.RED);
            String errorString = "Not all required fields were filled\n(";
            if(nameString.equals("")) {
                errorString += "Name";
            }
            if(phoneString.equals("")) {
                if(nameString.equals("")) {
                    errorString += ", ";
                }
                errorString += "Phone Number";
            }
            if(emailString.equals("")) {
                if(nameString.equals("") || phoneString.equals("")) {
                    errorString += ", ";
                }
                errorString += "Email Address";
            }
            errorString += ")";
            requiredText.setText(errorString);
        }


    }
}
