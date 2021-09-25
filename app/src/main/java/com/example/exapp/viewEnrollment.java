package com.example.exapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class viewEnrollment extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView e_empty_imageview;
    TextView e_no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> e_id, eYear, eSemester, eModule;
    CustomEnrollAdapter customEnrollAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enrollment);

        recyclerView = findViewById(R.id.eRecyclerView);
        add_button = findViewById(R.id.e_add_button);
        e_empty_imageview = findViewById(R.id.e_empty_imageview);
        e_no_data = findViewById(R.id.e_no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewEnrollment.this, AddEnrollment.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(viewEnrollment.this);
        e_id = new ArrayList<>();
        eYear = new ArrayList<>();
        eSemester = new ArrayList<>();
        eModule = new ArrayList<>();

        storeDataInArrays();

        customEnrollAdapter = new CustomEnrollAdapter(viewEnrollment.this,this, e_id, eYear, eSemester,
                eModule);
        recyclerView.setAdapter(customEnrollAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewEnrollment.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllEnrollmentData();
        if(cursor.getCount() == 0){
            e_empty_imageview.setVisibility(View.VISIBLE);
            e_no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                e_id.add(cursor.getString(0));
                eYear.add(cursor.getString(1));
                eSemester.add(cursor.getString(2));
                eModule.add(cursor.getString(3));
            }
            e_empty_imageview.setVisibility(View.GONE);
            e_no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.e_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.e_delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all your Enrollments?");
        builder.setMessage("Are you sure you want to delete all your Enrollments?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(viewEnrollment.this);
                myDB.deleteAllEnrollmentData();
                //Refresh Activity
                Intent intent = new Intent(viewEnrollment.this, viewEnrollment.class);
                startActivity(intent);
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