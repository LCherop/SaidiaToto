package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TitleViewer extends AppCompatActivity {
    private Button calling;
    private TextView one;
    private TitleAdapter titleAdapter;
    private ArrayList<TitleModel> titleModelArrayList;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    //private String k ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_viewer);

        calling = findViewById(R.id.dialer);
        one = findViewById(R.id.text_store);
        recyclerView = findViewById(R.id.recycler_title);
        bottomNavigationView = findViewById(R.id.bottomnn);
        bottomNavigationView.setSelectedItemId(R.id.emergency);
        bottomnn();

        titleModelArrayList = new ArrayList<>();
        titleAdapter = new TitleAdapter(titleModelArrayList,this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(titleAdapter);

        InitializeData();


    }

    private void InitializeData() {
        String[] titles = getResources().getStringArray(R.array.manual_titles);
        titleModelArrayList.clear();
        for (int i = 0; i < titles.length; i++){
            titleModelArrayList.add(new TitleModel(titles[i]));
        }
        titleAdapter.notifyDataSetChanged();
    }

    private void bottomnn() {
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
                        Intent profile = new Intent(getApplicationContext(),Profile.class);
                        startActivity(profile);
                        break;
                    case R.id.emergency:
                        Intent emergency = new Intent(getApplicationContext(),TitleViewer.class);
                        startActivity(emergency);

                        break;
                    case R.id.settings:
                        Intent settings = new Intent(getApplicationContext(),activity_settings_page.class);
                        startActivity(settings);

                        break;
                    case R.id.hospitals:
                        Intent hospitals = new Intent(getApplicationContext(),NearbyHospitals.class);
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