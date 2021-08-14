package com.laura.saidiatoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class HospitalAdapter  extends RecyclerView.Adapter<HospitalAdapter.viewHolder>{

    Context context;
    ArrayList<EmergencyHospitalList> emergencyHospitalLists;

    public HospitalAdapter(Context context, ArrayList<EmergencyHospitalList> emergencyHospitalLists) {
        this.context = context;
        this.emergencyHospitalLists = emergencyHospitalLists;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hospital,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {

        EmergencyHospitalList emergencyHospitalList = emergencyHospitalLists.get(position);
        holder.hospitalName.setText(emergencyHospitalList.getEmergencyHospitalname());
        holder.hospitalLocation.setText(emergencyHospitalList.getEmergencyHospitalLocation());
        holder.hospitalNumber.setText(emergencyHospitalList.getGetEmergencyHospitalNumber());
    }

    @Override
    public int getItemCount() {
        return emergencyHospitalLists.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView hospitalName,hospitalLocation,hospitalNumber;


        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            hospitalName = itemView.findViewById(R.id.hName);
            hospitalLocation=itemView.findViewById(R.id.hLoc);
            hospitalNumber=itemView.findViewById(R.id.dialNumber);


        }
    }
    /*
    private ArrayList<EmergencyHospitalList> hospitalData;
    private Context context;

    public HospitalAdapter(@NonNull @NotNull FirebaseRecyclerOptions options, ArrayList<EmergencyHospitalList> hospitalData, Context context) {
        super(options);
        this.hospitalData = hospitalData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.hospital, parent, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        EmergencyHospitalList currentHospital = hospitalData.get(position);
        holder.bindTo(currentHospital);
    }

    @Override
    public int getItemCount() {

        return recipeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }*/

}
