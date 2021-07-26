package com.laura.saidiatoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Profile extends AppCompatActivity {
    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.fname_Profile);
        fullNameLabel = findViewById(R.id.fullNameLabel);
        email = findViewById(R.id.email_Profile);
        phoneNo = findViewById(R.id.pNo_Profile);
        password = findViewById(R.id.passw_Profile);

        //Show user data
        showData();
    }

    private void showData() {
        Intent toProfile = getIntent();
        String user_name = toProfile.getStringExtra("name");
        String user_email = toProfile.getStringExtra("email");
        String user_number = toProfile.getStringExtra("number");
        String user_password = toProfile.getStringExtra("passw");

        fullNameLabel.setText(user_name);

        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_number);
        password.getEditText().setText(user_password);


    }

    public void updateUser(View view) {

    }
}