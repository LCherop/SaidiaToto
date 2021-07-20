package com.laura.saidiatoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextInputLayout fName,Email,pno,passw,cpassw;
    Button register,toLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fName = findViewById(R.id.fName);
        Email = findViewById(R.id.email);
        pno = findViewById(R.id.pno);
        passw = findViewById(R.id.passw);
        cpassw = findViewById(R.id.cpassw);
        register = findViewById(R.id.reg);
        toLogin =findViewById(R.id.toLogin);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  private void validate()
            {
                    boolean temp=true;
                    String checkemail = Email.getEditText().getText().toString();
                    String pass=passw.getEditText().getText().toString();
                    String cpass=cpassw.getEditText().getText().toString();
                    if(!EMAIL_ADDRESS_PATTERN.matcher(checkemail).matches()){
                        Toast.makeText(SignUp.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                        temp=false;
                    }
                    else if(!pass.equals(cpass)){
                        Toast.makeText(SignUp.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                        temp=false;
                    }

            }*/

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");


                String name = fName.getEditText().getText().toString();
                String email = Email.getEditText().getText().toString();
                String number = pno.getEditText().getText().toString();
                String pass = passw.getEditText().getText().toString();

                UserHelper helper = new UserHelper(name,email,number,pass);

                reference.child(number).setValue(helper);


            }
        });
    }


}