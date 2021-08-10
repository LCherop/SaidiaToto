package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forgot_Password extends AppCompatActivity {
    EditText email;
    Button resetButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

    email=findViewById(R.id.email_passwordReset);
    resetButton=findViewById(R.id.reset);

    mAuth=FirebaseAuth.getInstance();
    //On Click listener for the send button
    resetButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email_pass=email.getText().toString().trim();

            if(TextUtils.isEmpty(email_pass)){
                Toast.makeText(Forgot_Password.this, "Enter your Email", Toast.LENGTH_SHORT).show();
            }
            mAuth.sendPasswordResetEmail(email_pass)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                          if(task.isSuccessful())  {
                              Toast.makeText(Forgot_Password.this, "Check Email to Reset Password", Toast.LENGTH_SHORT).show();
                          }else {
                              Toast.makeText(Forgot_Password.this,"Email Unsuccessful",Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
            }
    });

    }



}