package com.laura.saidiatoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.ViewHolder>{

    private ArrayList<InstructionModel> instructionList;
    Context context;

    public InstructionAdapter(ArrayList<InstructionModel> instructionList, Context context) {
        this.instructionList = instructionList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public InstructionAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.instructions_page,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InstructionAdapter.ViewHolder holder, int position) {
        InstructionModel model = instructionList.get(position);
        holder.common_causes.setText(model.getCommon_causes());
        holder.title.setText(model.getTitle());
        holder.info.setText(model.getMoreInfo());
        holder.steps.setText(model.getSteps());
        holder.signs.setText(model.getSymptoms());


    }

    @Override
    public int getItemCount() {
        return instructionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,common_causes,steps,signs,info;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.inst_title);
            common_causes = itemView.findViewById(R.id.commonCauses);
            steps = itemView.findViewById(R.id.steps);
            signs = itemView.findViewById(R.id.sandm);
            info = itemView.findViewById(R.id.moreInfo);


        }
    }
}


