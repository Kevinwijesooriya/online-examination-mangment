package com.example.exapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateEnrollment extends AppCompatActivity {

    EditText e_year_input, e_semester_input, e_module_input;
    Button e_update_button,e_delete_button;

    String id;
    String year;
    String semester;
    String module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_enrollment);

        e_year_input = findViewById(R.id.year_input2);
        e_semester_input = findViewById(R.id.semester_input2);
        e_module_input = findViewById(R.id.module_input2);
        e_update_button = findViewById(R.id.e_update_button);
        e_delete_button = findViewById(R.id.e_delete_button);

        //First we call this
        getAndSetIntentData();


        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(module);
        }

        e_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateEnrollment.this);
                year = e_year_input.getText().toString().trim();
                semester = e_semester_input.getText().toString().trim();
                module = e_module_input.getText().toString().trim();
                myDB.updateEnrollmentData(id, Integer.parseInt(year), Integer.parseInt(semester), module);
            }
        });

        e_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("eYear") &&
                getIntent().hasExtra("eSemester") && getIntent().hasExtra("eModule")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            year = getIntent().getStringExtra("eYear");
            semester = getIntent().getStringExtra("eSemester");
            module = getIntent().getStringExtra("eModule");

            //Setting Intent Data
            e_year_input.setText(year);
            e_semester_input.setText(semester);
            e_module_input.setText(module);
            Log.d("stev", year+" "+semester+" "+module);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete module " + module + " ?");
        builder.setMessage("Are you sure you want to delete " + module + " module from your enrollments  ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateEnrollment.this);
                myDB.deleteOneEnrollmentRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}