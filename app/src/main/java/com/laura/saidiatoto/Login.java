package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputLayout email, pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getEditText().getText().toString();
                String pas = pass.getEditText().getText().toString();
                    if(em.length()==0){
                        email.setError("Field must not be empty");
                    }
                    if(pas.length()==0){
                        pass.setError("Field must not be empty");
                    }else{
                        checkUser();
                    }


            }
        });
    }




    public void toreg(View view) {
        Intent toregP = new Intent(this,SignUp.class);
        startActivity(toregP);
    }

    public void recoverPassword(View view) {
    }



    private void checkUser() {
        String EnteredEmail = email.getEditText().getText().toString();
        String EnteredPass = Objects.requireNonNull(pass.getEditText()).getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query check = reference.orderByChild("username").equalTo(EnteredEmail);
        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    email.setError(null);
                    email.setErrorEnabled(false);

                    String passwordinDB = snapshot.child(EnteredEmail).child("password").getValue(String.class);

                    if (passwordinDB.equals(EnteredPass)){
                        pass.setError(null);
                        pass.setErrorEnabled(false);

                        String nameinDB = snapshot.child(EnteredEmail).child("fname").getValue(String.class);
                        String numberinDB = snapshot.child(EnteredEmail).child("number").getValue(String.class);
                        String emailinDB = snapshot.child(EnteredEmail).child("email").getValue(String.class);
                        String usernameinDB = snapshot.child(EnteredEmail).child("username").getValue(String.class);

                        Intent toProfile = new Intent(getApplicationContext(),Profile.class);

                        toProfile.putExtra("name",nameinDB);
                        toProfile.putExtra("number",numberinDB);
                        toProfile.putExtra("username",usernameinDB);
                        toProfile.putExtra("email",emailinDB);
                        toProfile.putExtra("passw",passwordinDB);

                        startActivity(toProfile);
                    }else {
                        pass.setError("Incorrect Password");
                        pass.requestFocus();
                    }
                }else {
                    email.setError("No such user exists");
                    email.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}