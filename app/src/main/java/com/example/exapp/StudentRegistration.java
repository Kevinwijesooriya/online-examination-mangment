package com.example.exapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentRegistration extends AppCompatActivity {
    EditText new_s_username, new_s_password, new_s_confirm;
    Button s_signIn_button, s_signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        new_s_username = findViewById(R.id.new_s_username);
        new_s_password = findViewById(R.id.new_s_password);
        new_s_confirm = findViewById(R.id.new_s_confirm);

        s_signup_button = findViewById(R.id.s_signup_button);
        s_signIn_button = findViewById(R.id.s_signIn_button);



        s_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(StudentRegistration.this);
                String user = new_s_username.getText().toString();
                String pass = new_s_password.getText().toString();
                String repass = new_s_confirm.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(StudentRegistration.this, "fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean usercheckResult = myDB.checkStudentUsername(user);
                        if (usercheckResult == false) {
                            Boolean regR = myDB.insertStudentDate(user, pass);
                            if (regR == true) {
                                Toast.makeText(StudentRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),StudentLogin.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(StudentRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(StudentRegistration.this, "Username already exists.\n please Sign In or use another name", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(StudentRegistration.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        s_signIn_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentLogin.class);
                startActivity(intent);
            }
        });
    }
}