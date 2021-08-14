package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.lang.String.*;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputLayout email, pass;
    Button login;
    private static final String TAG = "EmailPassword";


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = valueOf(email.getEditText().getText());
                String pas = valueOf(pass.getEditText().getText());

                mAuth.signInWithEmailAndPassword(em, pas).addOnCompleteListener(Login.this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent toProfile = new Intent(Login.this,Profile.class);
                        startActivity(toProfile);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }).addOnFailureListener(e -> Toast.makeText(Login.this, e.getLocalizedMessage(),Toast.LENGTH_LONG).show());

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
        String em = Objects.requireNonNull(email.getEditText()).getText().toString();
        String pas = Objects.requireNonNull(pass.getEditText()).getText().toString();
        mAuth.signInWithEmailAndPassword(em, pas).addOnCompleteListener(Login.this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");

                FirebaseUser user = mAuth.getCurrentUser();
                Intent toLanding = new Intent(Login.this,Landing_Page.class);
                startActivity(toLanding);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(Login.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        }).addOnFailureListener(e -> Toast.makeText(Login.this, e.getLocalizedMessage(),Toast.LENGTH_LONG).show());

    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }


}