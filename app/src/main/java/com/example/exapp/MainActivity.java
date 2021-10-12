package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button switchToStudentLogin;
    Button switchToAdminLogin;
    Button switchToLecturerLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToStudentLogin = findViewById(R.id.home_student_button);
        switchToStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchStudent();
            }
        });
        switchToAdminLogin = findViewById(R.id.home_admin_button);
        switchToAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchAdmin();
            }
        });
        switchToLecturerLogin = findViewById(R.id.home_lecturer_button);
        switchToLecturerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLecturer();
            }
        });

    }

    //    test comment


    private void switchStudent() {
        Intent switchStudent = new Intent(this, StudentLogin.class);
        startActivity(switchStudent);
    }

    private void switchAdmin() {
        Intent switchAdmin = new Intent(this, AdminLogin.class);
        startActivity(switchAdmin);
    }

    private void switchLecturer() {
        Intent switchLecturer = new Intent(this, StudentRegistration.class);
        startActivity(switchLecturer);
    }


}