package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEnrollment extends AppCompatActivity {
    EditText year_input, semester_input, module_input;
    Button e_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enrollment);
        year_input = findViewById(R.id.year_input);
        semester_input = findViewById(R.id.semester_input);
        module_input = findViewById(R.id.module_input);
        e_add_button = findViewById(R.id.e_add_button);
        e_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddEnrollment.this);
                myDB.addEnrollment(Integer.valueOf(year_input.getText().toString().trim()),
                        Integer.valueOf(semester_input.getText().toString().trim()),
                        module_input.getText().toString().trim());
            }
        });
    }
}