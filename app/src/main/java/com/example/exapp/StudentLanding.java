package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentLanding extends AppCompatActivity {
    Button switchToEnrollment,switchToExams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_landing);

        switchToEnrollment = findViewById(R.id.s_enroll_button);
        switchToEnrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEnrollment();
            }
        });
        switchToExams = findViewById(R.id.s_exam_button);
        switchToExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchExams();
            }
        });
    }
    private void switchEnrollment() {
        Intent switchEnrollmentIntent = new Intent(this, viewEnrollment.class);
        startActivity(switchEnrollmentIntent);
    }
    private void switchExams() {
        Intent switchExamsIntent = new Intent(this, qMainActivity.class);
        startActivity(switchExamsIntent);
    }
}