package com.laura.saidiatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HeadInjuryActivity extends AppCompatActivity {
    private TextView title,cc,sandm,moreinfo,steps;
    //private ImageView animationspace;
    private String k ="";
    private InstructionAdapter adapter;
    private ArrayList<InstructionModel> instructionList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_injury);

        ImageView back = findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTitleView = new Intent(getApplicationContext(),TitleViewer.class);
                startActivity(toTitleView);
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("first_aid_manual");

        instructionList = new ArrayList<>();
        adapter = new InstructionAdapter(instructionList, this);

        title = findViewById(R.id.hinst_title);
        cc = findViewById(R.id.hurncommonCauses);
        sandm = findViewById(R.id.hurnsandm);
        moreinfo = findViewById(R.id.hmoreInfo);
        steps = findViewById(R.id.hurnsteps);
        //animationspace = findViewById(R.id.hurninstructionImage);

        Query query = reference.child("-MiEGyQg079Gdo72l0AU");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String dtitle = snapshot.child("injury_name").getValue(String.class);
                String dcc = snapshot.child("common_causes").getValue(String.class);
                String dsm = snapshot.child("symptoms").getValue(String.class);
                String dmi = snapshot.child("more_info").getValue(String.class);
                String dsteps = snapshot.child("steps").getValue(String.class);

                title.setText(dtitle);
                cc.setText(dcc);
                sandm.setText(dsm);
                moreinfo.setText(dmi);
                steps.setText(dsteps);


            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}