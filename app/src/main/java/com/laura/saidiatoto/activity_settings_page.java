package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

import static android.graphics.ColorSpace.Model.XYZ;

public class activity_settings_page extends AppCompatActivity {


    private String k ="";
    BottomNavigationView bottomNavigationView;
    TextView lang,acc_set,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);


        bottomNavigationView = findViewById(R.id.bottomn);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        bottomn();


    }

    private void bottomn() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        return true;
                    case R.id.emergency:
                        Intent emergency = new Intent(activity_settings_page.this,TitleViewer.class);
                        startActivity(emergency);

                        break;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),activity_settings_page.class));
                        return true;
                    case R.id.hospitals:
                        startActivity(new Intent(getApplicationContext(),NearbyHospitals.class));
                        return true;
                }
                return false;
            }
        });

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent profile = new Intent(activity_settings_page.this,Profile.class);
                        startActivity(profile);
                        break;
                    case R.id.emergency:
                        //Intent emergency = new Intent(Profile.this,Emergency_Page.class);
                        //startActivity(emergency);

                        break;
                    case R.id.settings:
                        Intent settings = new Intent(activity_settings_page.this,activity_settings_page.class);
                        startActivity(settings);
                        break;
                    case R.id.hospitals:
                        Intent hospitals = new Intent(activity_settings_page.this,NearbyHospitals.class);
                        startActivity(hospitals);
                        break;
            }
        }
        });
    }

    public void changelanguage(View view) {

        DialogClass dialogClass = new DialogClass();
        dialogClass.show(getSupportFragmentManager(),"example dialog");

        /*
        LocaleHelper.setLocale(this,"sw-rKE"); //for french;
         Context context =this;
        String languageToLoad  = "fr";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());


        Intent intent = new Intent(activity_settings_page.this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        //LocaleHelper.setLocale(this,"en"); //for english;

    }


    public void logout(View view) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        mauth.signOut();
        Intent closer = new Intent(activity_settings_page.this,Login.class);
        startActivity(closer);
    }

    public void acc_set(View view) {

    }
}