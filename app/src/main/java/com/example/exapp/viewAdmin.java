package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class viewAdmin extends AppCompatActivity {
 FloatingActionButton add_timetable_Button;
 RecyclerView admin_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin);

        add_timetable_Button=findViewById(R.id.add_timetable_Button);
        admin_recyclerview=findViewById(R.id.admin_recyclerview);




        add_timetable_Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),AddTimetable.class);
                startActivity(intent);
            }
        });

    }
}