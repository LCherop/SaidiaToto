package com.laura.saidiatoto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {
    //private String k ="";
    private ArrayList<TitleModel> instructionTitleList;
    Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;


    public TitleAdapter(ArrayList<TitleModel> instructionList, Context context) {
        this.instructionTitleList = instructionList;
        this.context = context;
    }



    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.injury_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TitleModel titleModel = instructionTitleList.get(position);
        holder.maintitle.setText(titleModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return instructionTitleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maintitle;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            maintitle = itemView.findViewById(R.id.instructionTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int titlePosition = getAdapterPosition();
                    TitleModel currentTitle = instructionTitleList.get(titlePosition);

                    if (titlePosition == 0){
                        //Fracture
                        Intent tofracture = new Intent(context,FractureActivity.class);

                        context.startActivity(tofracture);

                    }else if (titlePosition == 1){
                        //Burn
                        Intent toburn = new Intent(context,BurnActivity.class);
                        context.startActivity(toburn);
                    }
                    else if (titlePosition == 2){
                        //Choking baby
                        Intent tocb = new Intent(context,ChokingBabyActivity.class);
                        context.startActivity(tocb);

                    }else if (titlePosition == 3){
                        //Choking child
                        Intent tocc = new Intent(context,ChokingChildActivity.class);
                        context.startActivity(tocc);

                    }else if (titlePosition == 4){
                        //Croup
                        Intent toc = new Intent(context,CroupActivity.class);
                        context.startActivity(toc);

                    }else if (titlePosition == 5){
                        //Harmful sub
                        Intent tosub = new Intent(context,SubstanceActivity.class);
                        context.startActivity(tosub);

                    }else if (titlePosition == 6){
                        //Head Injury
                        Intent tohi = new Intent(context,HeadInjuryActivity.class);
                        context.startActivity(tohi);

                    }else if (titlePosition == 7){
                        //Nosebleed
                        Intent tonb = new Intent(context,NosebleedActivity.class);
                        context.startActivity(tonb);

                    }else if (titlePosition == 8){
                        //Vomiting
                        Intent tovd = new Intent(context,VomitActivity.class);
                        context.startActivity(tovd);

                    }else if (titlePosition == 9){
                        //Vomiting
                        Intent tofever = new Intent(context,FeverActivity.class);
                        context.startActivity(tofever);

                    }
                }
            });
        }
    }
}
