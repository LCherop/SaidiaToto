package com.laura.saidiatoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OTP_number extends AppCompatActivity {
    EditText pnumber,pin;
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_number);

        pnumber = findViewById(R.id.pnumber);
        pin = findViewById(R.id.pin);

        showPhoneNumber();

        verify = (Button) findViewById(R.id.verifyButton);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pnumber.getText();

            }
        });

    }

    private void showPhoneNumber() {
        Intent toProfile = getIntent();
        String user_number = toProfile.getStringExtra("number");
        pnumber.setText(user_number);
    }
}