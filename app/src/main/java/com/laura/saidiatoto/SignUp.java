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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private TextInputLayout fName,Email,pno,passw,Uname;
    private Button register,toLogin;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
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
        setContentView(R.layout.activity_sign_up);

        fName = findViewById(R.id.fName);
        Uname = findViewById(R.id.uName);
        Email = findViewById(R.id.email);
        pno = findViewById(R.id.pno);
        passw = findViewById(R.id.passw);
        register = findViewById(R.id.reg);
        toLogin =findViewById(R.id.toLogin);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = fName.getEditText().getText().toString();
                String username = Uname.getEditText().getText().toString();
                String email = Email.getEditText().getText().toString();
                String number = pno.getEditText().getText().toString();
                String pass = passw.getEditText().getText().toString();

                //Text Validation
                    if(name.length()==0){
                        fName.setError("Field must not be empty");
                    }
                    if(username.length()==0){
                    Uname.setError("Field must not be empty");
                }
                    if(email.length()==0){
                        Email.setError("Field must not be empty");
                    }
                    if (number.length()==0){
                        pno.setError("Field must not be empty");
                    }
                    if (pass.length()==0){
                        passw.setError("Field must not be empty");
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Email.setError("Invalid Email Address");
                    }
                    if (!Patterns.PHONE.matcher(number).matches()){
                        pno.setError("Invalid phone number");
                    }else{
                        /*rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");
                        UserHelper helper = new UserHelper(name,username,email,number,pass);
                        reference.child(email).setValue(helper);*/
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            sendEmailVerification();
                                            updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(SignUp.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                });



                }




            }
        });
    }
    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {
        Intent toLanding = new Intent( SignUp.this, Landing_Page.class);
        startActivity(toLanding);

    }

    public void toLogin(View view) {
        Intent tologin = new Intent(this, Login.class);
        startActivity(tologin);
    }
}