package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

//import org.jetbrains.annotations.NotNull;

public class Landing_Page extends AppCompatActivity {
    private BottomNavigationView bnav;
    private NavigationView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        bnav = findViewById(R.id.botomNav);

        //getSupportFragmentManager().beginTransaction().replace(R.id.act,new Landing_Page()).commit();

        bottomMenu();
    }

    private void bottomMenu() {
        bnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.profile:
                        fragment = new profile_page();

                        break;
                    case R.id.info:
                        fragment = new info_page();
                        break;
                    case R.id.emergency:
                        fragment = new emergency_page();
                        break;
                    case R.id.settings:
                        fragment = new settings_page();
                        break;
                    case R.id.hospitals:
                        fragment = new hospitals_page();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return false;
            }
        });

        bnav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });

    }
}