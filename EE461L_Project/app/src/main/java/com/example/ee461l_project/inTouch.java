package com.example.ee461l_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inTouch extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "EmailPassword";

    /* Sign in Fields */
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mUserNameField;
    private EditText mPhoneNumField;
    private EditText mPasswordField;

    /* Sign up Fields */
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mOrgNameField;
    private EditText mEmailField;
    private EditText mPassField;
    private EditText mBirthField;
    private EditText mUserField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_touch);
        //mStatusTextView = (TextView) findViewById(R.id.status);
        //mDetailTextView = (TextView) findViewById(R.id.detail);
        /* Sign in fields initialization */
        mUserNameField = (EditText) findViewById(R.id.UserName);
        mPhoneNumField = (EditText) findViewById(R.id.PhoneNum);
        mPasswordField = (EditText) findViewById(R.id.Password);

        /*Sign up fields initialization */
        mFirstNameField = (EditText) findViewById(R.id.FirstName);
        mLastNameField = (EditText) findViewById(R.id.LastName);
        mOrgNameField = (EditText) findViewById(R.id.OrgName);
        mEmailField = (EditText) findViewById(R.id.Email);
        mPassField = (EditText) findViewById(R.id.Pass);
        mBirthField = (EditText) findViewById(R.id.Birth);
        mUserField = (EditText) findViewById(R.id.User);

        // Buttons
        findViewById(R.id.signInButton).setOnClickListener(this);
        findViewById(R.id.signUpButton).setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
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
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

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
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE] */
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

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
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
        if (user != null) { //If the user has logged in
           // mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()));
            //mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            /* findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE); */
        } else { //If the user has now logged out
          //  mStatusTextView.setText(R.string.signed_out);
            //mDetailTextView.setText(null);

            findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.signUpButton).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signUpButton) { //If the user wants to sign up
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signInButton) { //If the user wants to sign in
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } /*else if (i == R.id.sign_out_button) { //If the user wants to sign out
            signOut();
        } */
    }
}
