package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//import org.jetbrains.annotations.NotNull;

public class Landing_Page extends AppCompatActivity {
    private BottomNavigationView bnav;
    private NavigationView v;
    private RecyclerView recyclerView;
    private Button call;

    private InstructionAdapter adapter;
    //private TitleAdapter titleAdapter;
    private ArrayList<InstructionModel> instructionList;
    //private ArrayList<TitleModel> titleModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    private String[] PERMISSIONS;
    private String key;

    protected void onStart() {

        super.onStart();

        if (!hasPermissions(this,PERMISSIONS)){
            ActivityCompat.requestPermissions(this,PERMISSIONS,1);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        PERMISSIONS = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION

        };

        firebaseDatabase = FirebaseDatabase.getInstance();
        //String key = firebaseDatabase.getReference().child("first_aid_manual").push().getKey();
        reference = firebaseDatabase.getReference().child("first_aid_manual");
        //titleModelArrayList = new ArrayList<>();
        //titleAdapter = new TitleAdapter(titleModelArrayList,this);
        instructionList = new ArrayList<>();
        adapter = new InstructionAdapter(instructionList, this);
        call = findViewById(R.id.call999);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:999"));
                startActivity(callIntent);
            }
        });
        bnav = findViewById(R.id.botomNav);
        bnav.setSelectedItemId(R.id.emergency);
        //getSupportFragmentManager().beginTransaction().replace(R.id.act,new Landing_Page()).commit();

        bottomMenu();

        recyclerView = findViewById(R.id.recyclerInstructionList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    InstructionModel model = dataSnapshot.getValue(InstructionModel.class);
                    instructionList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
    private boolean hasPermissions(Context context, String... PERMISSIONS){
        if (context != null && PERMISSIONS != null){
            for (String permission: PERMISSIONS){
                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }

        }return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Dialing Permissions Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Dialing Permissions not Granted", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Storage access granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Storage access not granted", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Location access granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Location access not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void bottomMenu() {
        bnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {


                switch (item.getItemId()){
                    case R.id.profile:
                        Intent profile = new Intent(Landing_Page.this,Profile.class);
                        startActivity(profile);
                        break;
                    case R.id.info:
                        //Intent info = new Intent(Landing_Page.this,Info_page.class);
                        //startActivity(info);
                        break;
                    case R.id.emergency:
                        //Intent emergency = new Intent(Landing_Page.this,Emergency_Page.class);
                        //startActivity(emergency);

                        break;
                    case R.id.settings:
                        Intent settings = new Intent(Landing_Page.this,activity_settings_page.class);
                        startActivity(settings);

                        break;
                    case R.id.hospitals:
                        Intent hospitals = new Intent(Landing_Page.this,NearbyHospitals.class);
                        startActivity(hospitals);
                        break;
                }
            return true;
            }
        });

        bnav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent profile = new Intent(Landing_Page.this,Profile.class);
                        startActivity(profile);
                        break;
                    case R.id.info:
                        //Intent info = new Intent(Landing_Page.this,Info_page.class);
                        //startActivity(info);
                        break;
                    case R.id.emergency:
                        //Intent emergency = new Intent(Landing_Page.this,Emergency_Page.class);
                        //startActivity(emergency);

                        break;
                    case R.id.settings:
                        Intent settings = new Intent(Landing_Page.this,activity_settings_page.class);
                        startActivity(settings);

                        break;
                    case R.id.hospitals:
                        Intent hospitals = new Intent(Landing_Page.this,NearbyHospitals.class);
                        startActivity(hospitals);
                        break;
                }
            }
        });

    }

    public void call999(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:999"));
        startActivity(callIntent);
    }
}