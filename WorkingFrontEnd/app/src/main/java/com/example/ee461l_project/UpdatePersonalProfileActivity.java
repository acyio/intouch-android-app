package com.example.ee461l_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdatePersonalProfileActivity extends AppCompatActivity {
    String name;
    String phone;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent profileIntent = getIntent();
        Bundle oldInfo = profileIntent.getExtras();
        name = oldInfo.getString("OLD_P_NAME");
        phone = oldInfo.getString("OLD_P_PHONE");
        email =  oldInfo.getString("OLD_P_EMAIL");

        setContentView(R.layout.activity_update_personal_profile);
    }

    public void submitPersonalProfile(View view) {
        EditText newName = (EditText) findViewById(R.id.p_name_edit);
        EditText newPhone = (EditText) findViewById(R.id.p_phone_edit);
        EditText newEmail = (EditText) findViewById(R.id.p_email_edit);
        String new_name = newName.getText().toString();
        String new_phone = newPhone.getText().toString();
        String new_email = newEmail.getText().toString();
        if(!new_name.equals("")) {
            name = new_name;
        }
        if(!new_phone.equals("")) {
            phone = new_phone;
        }
        if(!new_email.equals("")) {
            email = new_email;
        }

        Intent intent = new Intent(this, UploadSearchActivity.class);
        Bundle updateInfo = new Bundle();
        updateInfo.putString("NEW_NAME", name);
        updateInfo.putString("NEW_PHONE", phone);
        updateInfo.putString("NEW_EMAIL", email);
        updateInfo.putString("NEW_CAT", "personal");
        UploadSearchActivity.done = false;
        intent.putExtras(updateInfo);
        startActivity(intent);

    }




}
