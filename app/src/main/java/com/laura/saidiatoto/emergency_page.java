package com.laura.saidiatoto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class emergency_page extends Fragment{


    //private List<String> hospitalList = new ArrayList<>();
    private HospitalAdapter hospitalAdapter;
    private DatabaseReference hospitalRef;
    private RecyclerView hospitals;
    private ArrayList<EmergencyHospitalList> list;
    private EmergencyHospitalList model;
    private LinearLayoutManager layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emergency_page, container, false);

        /*
        hospitals = rooterView.findViewById(R.id.hospitals);
        hospitalRef = FirebaseDatabase.getInstance().getReference("hospitals");
        hospitals.setHasFixedSize(true);
        hospitals.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        hospitalAdapter = new HospitalAdapter(getContext(),list);
        hospitals.setAdapter(hospitalAdapter);

        hospitalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    EmergencyHospitalList emergencyHospitalList = snapshot1.getValue(EmergencyHospitalList.class);
                    list.add(emergencyHospitalList);
                }
                hospitalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("hospitals");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addChildEventListener(childEventListener);


        /*
        FirebaseListOptions<EmergencyHospitalList> options = new FirebaseListOptions.Builder<EmergencyHospitalList>()
                .setQuery(query, EmergencyHospitalList.class)
                .build();

        FirebaseListAdapter<EmergencyHospitalList> adapter = new FirebaseListAdapter<EmergencyHospitalList>(options) {
            @Override
            protected void populateView(View v, EmergencyHospitalList model, int position) {

                // Bind the Chat to the view
                // ...
            }
        }; */



        //return rooterView;
    }
}