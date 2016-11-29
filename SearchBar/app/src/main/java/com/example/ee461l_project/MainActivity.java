package com.example.ee461l_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // sign_up
    /**
     * private EditText firstname;
     * private EditText lastname;
     * private EditText companyname;
     * private EditText phonenumber;
     * private EditText email;
     * private EditText pass;
     * private EditText birth;
     * private EditText user;
     */

    // sign_in
    private EditText username;
    private EditText phone;
    private EditText password;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        // sign_up
        /**
         firstname = (EditText) findViewById(R.id.editText);
         lastname = (EditText) findViewById(R.id.editText2);
         companyname = (EditText) findViewById(R.id.editText5);
         phonenumber = (EditText) findViewById(R.id.editText6);
         email = (EditText) findViewById(R.id.editText3);
         pass = (EditText) findViewById(R.id.editText4);
         birth = (EditText) findViewById(R.id.editText7);
         user = (EditText) findViewById(R.id.editText8);
         */

        // sign_in
        username = (EditText) findViewById(R.id.editText9);
        phone = (EditText) findViewById(R.id.editText10);
        password = (EditText) findViewById(R.id.editText11);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Shan,4407086253,smkanvinde@gmail.com"));
    }

    // sign_in
    public void buttonOnClick(View v) {
        Button button = (Button) v;
        String u = username.getText().toString();
        String ph = phone.getText().toString();
        String pa = password.getText().toString();
        i = new Intent(this, UploadSearchActivity.class);
        startActivity(i);
    }

    // sign_up
/**
 public void buttonOnClick(View v) {
 Button button2 = (Button) v;
 String n = firstname.getText().toString();
 String l = lastname.getText().toString();
 String c = companyname.getText().toString();
 String ph = phonenumber.getText().toString();
 String e = email.getText().toString();
 String pa = pass.getText().toString();
 String b = birth.getText().toString();
 String u = user.getText().toString();
 i = new Intent(this, UploadSearchActivity.class);
 startActivity(i);
 }
 */
}
