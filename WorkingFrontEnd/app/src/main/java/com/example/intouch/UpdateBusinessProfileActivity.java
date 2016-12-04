package com.example.intouch;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class UpdateBusinessProfileActivity extends AppCompatActivity {

    BusinessContact oldBContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent profileIntent = getIntent();
        Bundle oldInfo = profileIntent.getExtras();
        oldBContact = oldInfo.getParcelable("OLD_B_CONTACT");
        setContentView(R.layout.activity_update_business_profile);
    }

    public void submitBusinessProfile(View view) {
        EditText newName = (EditText) findViewById(R.id.b_name_edit);
        EditText newPhone = (EditText) findViewById(R.id.b_phone_edit);
        EditText newEmail = (EditText) findViewById(R.id.b_email_edit);
        EditText newURL = (EditText) findViewById(R.id.b_URL_edit);
        String new_name = newName.getText().toString();
        String new_phone = newPhone.getText().toString();
        String new_email = newEmail.getText().toString();
        String new_URL = newURL.getText().toString();
        if(!new_name.equals("")) {
            oldBContact.setName(new_name);
        }
        if(!new_phone.equals("")) {
            oldBContact.setName(new_phone);
        }
        if(!new_email.equals("")) {
            oldBContact.setEmail(new_email);
        }
        if(!new_URL.equals("")) {
            oldBContact.setLinkedInURL(new_URL);
        }

        Intent intent = new Intent(this, UploadSearchActivity.class);
        Bundle updateInfo = new Bundle();
        UploadSearchActivity.update = true;
        updateInfo.putParcelable("UPDATED_CONTACT", oldBContact);
        intent.putExtras(updateInfo);
        startActivity(intent);

    }
}
