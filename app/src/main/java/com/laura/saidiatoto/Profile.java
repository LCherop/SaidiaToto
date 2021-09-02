package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Profile extends AppCompatActivity {
    private TextInputLayout email;
    private TextInputLayout fname,pnumber;
    private ImageButton profPic;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageReference;

    String usernumber="";
    String userName="";
    private static final String TAG = "EmailPassword";
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        email = findViewById(R.id.email_Profile);
        fname = findViewById(R.id.fname_prof);
        pnumber = findViewById(R.id.pNumberProf);
        profPic = findViewById(R.id.profile_image);
        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.profile);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("users");
        storageReference = FirebaseStorage.getInstance().getReference().child("user_profile");

        profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        //Show user data
        showData();
        //set bottomnav buttons
        bottom();
    }



    private void uploadImage() {
        Intent intent = new Intent();
        intent.setType("user_profile/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profPic.setImageURI(imageUri);
            uploadtoDB();
        }
    }
    private void uploadtoDB(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();

        StorageReference mountainImagesRef = storageReference.child("user_profile/"+randomKey);

        mountainImagesRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Profile.this, "Image Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(Profile.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Progress: "+ (int) progressPercent+"%");
            }
        });
    }

    private void bottom() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        return true;

                    case R.id.emergency:
                        startActivity(new Intent(getApplicationContext(),TitleViewer.class));


                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),activity_settings_page.class));

                        return true;
                    case R.id.hospitals:
                        startActivity(new Intent(getApplicationContext(),NearbyHospitals.class));
                        return true;
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return false;
            }
        });

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent profile = new Intent(Profile.this,Profile.class);
                        startActivity(profile);
                        break;

                    case R.id.emergency:
                        Intent emergency = new Intent(Profile.this,TitleViewer.class);
                        startActivity(emergency);

                        break;
                    case R.id.settings:
                        Intent settings = new Intent(Profile.this,activity_settings_page.class);
                        startActivity(settings);

                        break;
                    case R.id.hospitals:
                        Intent hospitals = new Intent(Profile.this,NearbyHospitals.class);
                        startActivity(hospitals);
                        break;
                }
            }
        });

    }

    private void showData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String Temail = user.getEmail();

            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();


            databaseReference = FirebaseDatabase.getInstance().getReference();
            Query query = databaseReference.child("users").child("users");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    usernumber = snapshot.child(uid).child("number").getValue(String.class);
                    userName = snapshot.child(uid).child("fname").getValue(String.class);

                    fname.getEditText().setText(userName);
                    pnumber.getEditText().setText(usernumber);
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
            email.getEditText().setText(Temail);


        } else {
            // No user is signed in
            Toast.makeText(Profile.this,"Please sign in to view details",Toast.LENGTH_SHORT).show();
        }




    }

    public void updateUser(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        user.updateEmail("user@example.com")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User email address updated.");
                        }
                    }
                });

    }

}