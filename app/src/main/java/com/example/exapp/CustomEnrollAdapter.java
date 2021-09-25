package com.example.exapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomEnrollAdapter extends RecyclerView.Adapter<CustomEnrollAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList e_id, eYear, eSemester, eModule;

    CustomEnrollAdapter(Activity activity, Context context, ArrayList e_id, ArrayList eYear, ArrayList eSemester,
                  ArrayList eModule){
        this.activity = activity;
        this.context = context;
        this.e_id = e_id;
        this.eYear = eYear;
        this.eSemester = eSemester;
        this.eModule = eModule;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.e_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.e_id_txt.setText(String.valueOf(e_id.get(position)));
        holder.e_year_txt.setText(String.valueOf(eYear.get(position)));
        holder.e_semester_txt.setText(String.valueOf(eSemester.get(position)));
        holder.e_module_txt.setText(String.valueOf(eModule.get(position)));
        //Recyclerview onClickListener
        holder.e_mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateEnrollment.class);
                intent.putExtra("id", String.valueOf(e_id.get(position)));
                intent.putExtra("eYear", String.valueOf(eYear.get(position)));
                intent.putExtra("eSemester", String.valueOf(eSemester.get(position)));
                intent.putExtra("eModule", String.valueOf(eModule.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return e_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView e_id_txt, e_year_txt, e_semester_txt, e_module_txt;
        LinearLayout e_mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            e_id_txt = itemView.findViewById(R.id.e_id_txt);
            e_year_txt = itemView.findViewById(R.id.e_year_txt);
            e_semester_txt = itemView.findViewById(R.id.e_semester_txt);
            e_module_txt = itemView.findViewById(R.id.e_module_txt);
            e_mainLayout = itemView.findViewById(R.id.e_mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }

    }


}
