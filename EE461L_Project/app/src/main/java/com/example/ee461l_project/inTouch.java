package com.example.ee461l_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inTouch extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth; //Object for handling FireBase authentication
    private FirebaseAuth.AuthStateListener mAuthListener; //Object for tracking authentication changes
    private static final String TAG = "EmailPassword";

    /* The sign in fields */
    private EditText mUserNameField;
    private EditText mPhoneNumberField;
    private EditText mPasswordField;

    /* The sign up fields */
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mOrgNameField;
    private EditText mPhoneField;
    private EditText mEmailField;
    private EditText mPassField;
    private EditText mBirthField;
    private EditText mUserField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_touch);
        // Sign in Views setup
        mUserNameField = (EditText) findViewById(R.id.editText9);
        mPhoneNumberField = (EditText) findViewById(R.id.editText9);
        mPasswordField = (EditText) findViewById(R.id.editText11);

        // Sign out Views setup
        mFirstNameField = (EditText) findViewById(R.id.FirstName);
        mLastNameField = (EditText) findViewById(R.id.LastName);
        mOrgNameField = (EditText) findViewById(R.id.OrgName);
        mPhoneField = (EditText) findViewById(R.id.Phone);
        mEmailField = (EditText) findViewById(R.id.Email);
        mPassField = (EditText) findViewById(R.id.Pass);
        mBirthField = (EditText) findViewById(R.id.Birth);
        mUserField = (EditText) findViewById(R.id.User);

        // Buttons
        findViewById(R.id.button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance(); //Initialize
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        /* if (!task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        } */
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        /* if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE] */
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mUserNameField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mUserNameField.setError("Required.");
            valid = false;
        } else {
            mUserNameField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
           // mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()));
           // mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.button).setVisibility(View.GONE); //Hide the sign-in button
           // findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        } else {
           // mStatusTextView.setText(R.string.signed_out);
           // mDetailTextView.setText(null);

            findViewById(R.id.button).setVisibility(View.VISIBLE);
          //  findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button) {
            createAccount(mUserField.getText().toString(), mPassField.getText().toString());
        } else if (i == R.id.button) { //If the user clicks the signin button
            signIn(mUserNameField.getText().toString(), mPasswordField.getText().toString());
        } /*else if (i == R.id.sign_out_button) {
            signOut();
        } */
    }
}
